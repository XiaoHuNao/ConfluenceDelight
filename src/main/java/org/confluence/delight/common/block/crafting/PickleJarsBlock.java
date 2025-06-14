package org.confluence.delight.common.block.crafting;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.fluids.FluidActionResult;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.confluence.delight.common.init.ModBlocks;
import org.confluence.lib.util.LibUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PickleJarsBlock extends BaseEntityBlock {
    private static final MapCodec<PickleJarsBlock> CODEC = simpleCodec(PickleJarsBlock::new);
    public static final BooleanProperty COVER = BooleanProperty.create("cover");
    private static final VoxelShape COVER_TRUE = Shapes.or(
            box(3, 0, 3, 13, 12, 13),
            box(2, 2, 2, 14, 11, 14),
            box(2, 12, 2, 14, 13, 14),
            box(2, 13, 3, 3, 15, 13),
            box(13, 13, 3, 14, 15, 13),
            box(2, 13, 2, 14, 15, 3),
            box(2, 13, 13, 14, 15, 14),
            box(4, 12, 4, 12, 17, 12)
    );
    private static final VoxelShape COVER_FALSE = Shapes.or(
            box(3, 0, 3, 13, 12, 13),
            box(2, 2, 2, 14, 11, 14),
            box(2, 12, 2, 14, 13, 14),
            box(2, 13, 3, 3, 15, 13),
            box(13, 13, 3, 14, 15, 13),
            box(2, 13, 2, 14, 15, 3),
            box(2, 13, 13, 14, 15, 14),
            box(5, 13, 5, 11, 15, 11)
    );

    public PickleJarsBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(COVER, false));
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pState.getBlock() == pNewState.getBlock()) return;
        if (pLevel.getBlockEntity(pPos) instanceof PickleJarsBlockEntity entity) {
            Containers.dropContents(pLevel, pPos, entity.itemHandler.getItems());
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof PickleJarsBlockEntity entity)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        ItemStack handItem = player.getItemInHand(hand);
        if (handItem.isEmpty()) {
            if (player.isShiftKeyDown()) {
                if (state.hasProperty(COVER)) {
                    if (!level.isClientSide) {
                        boolean currentCover = state.getValue(COVER);
                        level.setBlockAndUpdate(pos, state.setValue(COVER, !currentCover));
                    }
                    return success(level);
                }
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            } else {
                return handleItemInteraction(entity, player, hand, handItem);
            }
        }
        if (!(handItem.getItem() instanceof BucketItem)) {
            return handleItemInteraction(entity, player, hand, handItem);
        }
        return handleBucketInteraction(entity, player, hand, handItem, level);
    }

    private ItemInteractionResult handleItemInteraction(PickleJarsBlockEntity entity, Player player, InteractionHand hand, ItemStack handItem) {
        Level level = player.level();
        ItemStack taken = entity.takeItem(-1);
        if (!taken.isEmpty()) {
            if (!level.isClientSide) {
                player.addItem(taken);
            }
            return success(level);
        }
        ItemStack remaining = entity.addItem(handItem);
        if (remaining.getCount() != handItem.getCount()) {
            if (!level.isClientSide) {
                player.setItemInHand(hand, remaining);
            }
            return success(level);
        }
        return ItemInteractionResult.FAIL;
    }

    private ItemInteractionResult handleBucketInteraction(PickleJarsBlockEntity entity, Player player, InteractionHand hand, ItemStack bucket, Level level) {
        Optional<FluidStack> fluidOptional = FluidUtil.getFluidContained(bucket);
        if (fluidOptional.isPresent() && !fluidOptional.get().isEmpty()) {
            return handleFluidFilling(entity, player, hand, bucket, fluidOptional.get(), level);
        }
        return handleFluidDraining(entity, player, hand, bucket, level);
    }

    private ItemInteractionResult handleFluidFilling(PickleJarsBlockEntity entity, Player player, InteractionHand hand, ItemStack bucket, FluidStack fluid, Level level) {
        int filled = entity.fluidTank.fill(fluid, IFluidHandler.FluidAction.SIMULATE);
        if (filled <= 0) return ItemInteractionResult.FAIL;
        Fluid fluidType = fluid.getFluid();
        if (player.isCreative()) {
            entity.fluidTank.fill(fluid, IFluidHandler.FluidAction.EXECUTE);
            if (fluidType == Fluids.WATER) {
                player.playSound(SoundEvents.BUCKET_EMPTY, 1.0f, 1.0f);
            } else if (fluidType == Fluids.LAVA) {
                player.playSound(SoundEvents.BUCKET_EMPTY_LAVA, 1.0f, 1.0f);
            } else {
                player.playSound(SoundEvents.BUCKET_EMPTY, 1.0f, 1.0f);
            }
            return success(level);
        }

        ItemStack emptyBucket = FluidUtil.tryEmptyContainer(bucket, entity.fluidTank, filled, player, true).getResult();
        if (!emptyBucket.isEmpty()) {
            player.setItemInHand(hand, emptyBucket);
            if (fluidType == Fluids.WATER) {
                player.playSound(SoundEvents.BUCKET_EMPTY, 1.0f, 1.0f);
            } else if (fluidType == Fluids.LAVA) {
                player.playSound(SoundEvents.BUCKET_EMPTY_LAVA, 1.0f, 1.0f);
            } else {
                player.playSound(SoundEvents.BUCKET_EMPTY, 1.0f, 1.0f);
            }
            return success(level);
        }
        return ItemInteractionResult.FAIL;
    }

    private ItemInteractionResult handleFluidDraining(PickleJarsBlockEntity entity, Player player, InteractionHand hand, ItemStack bucket, Level level) {
        FluidStack tankFluid = entity.fluidTank.getFluid();
        if (tankFluid.isEmpty()) return ItemInteractionResult.FAIL;
        Fluid fluidType = tankFluid.getFluid();
        if (player.isCreative()) {
            int drainAmount = 1000;
            if (entity.fluidTank.getFluidAmount() >= drainAmount) {
                entity.fluidTank.drain(drainAmount, IFluidHandler.FluidAction.EXECUTE);
                if (fluidType == Fluids.WATER) {
                    player.playSound(SoundEvents.BUCKET_FILL, 1.0f, 1.0f);
                } else if (fluidType == Fluids.LAVA) {
                    player.playSound(SoundEvents.BUCKET_FILL_LAVA, 1.0f, 1.0f);
                } else {
                    player.playSound(SoundEvents.BUCKET_FILL, 1.0f, 1.0f);
                }
                return success(level);
            } else {
                return ItemInteractionResult.FAIL;
            }
        }

        FluidActionResult result = FluidUtil.tryFillContainer(bucket, entity.fluidTank, 1000, player, true);
        if (result.isSuccess()) {
            player.setItemInHand(hand, result.getResult());
            if (fluidType == Fluids.WATER) {
                player.playSound(SoundEvents.BUCKET_FILL, 1.0f, 1.0f);
            } else if (fluidType == Fluids.LAVA) {
                player.playSound(SoundEvents.BUCKET_FILL_LAVA, 1.0f, 1.0f);
            } else {
                player.playSound(SoundEvents.BUCKET_FILL, 1.0f, 1.0f);
            }
            return success(level);
        }
        return ItemInteractionResult.FAIL;
    }

    private ItemInteractionResult success(Level level) {
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(COVER) ? COVER_TRUE : COVER_FALSE;
    }

    @Override
    protected MapCodec<PickleJarsBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(COVER);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PickleJarsBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : LibUtils.getTicker(blockEntityType, ModBlocks.PICKLE_JARS_BLOCK_ENTITY.get(), PickleJarsBlockEntity::serverTick);
    }
}
