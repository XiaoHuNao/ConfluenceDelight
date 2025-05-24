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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
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
        if (!(handItem.getItem() instanceof BucketItem)) {
            return handleItemInteraction(entity, player, hand, handItem);
        }
        return handleBucketInteraction(entity, player, hand, handItem, level);
    }

    private ItemInteractionResult handleItemInteraction(PickleJarsBlockEntity entity, Player player, InteractionHand hand, ItemStack handItem) {
        Level level = player.level();
        if (player.isCrouching()) {
            ItemStack taken = entity.takeItem(-1);
            if (!taken.isEmpty()) {
                player.addItem(taken);
                return success(level);
            }
            return ItemInteractionResult.FAIL;
        }

        ItemStack remaining = entity.addItem(handItem);
        if (remaining.getCount() != handItem.getCount()) {
            player.setItemInHand(hand, remaining);
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
