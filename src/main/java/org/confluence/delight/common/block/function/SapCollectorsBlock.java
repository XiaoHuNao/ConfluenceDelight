package org.confluence.delight.common.block.function.crafting;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDMaterialItems;
import org.confluence.lib.util.LibUtils;
import org.confluence.mod.common.init.block.NatureBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class SapCollectorsBlock extends BaseEntityBlock {
    public static final MapCodec<SapCollectorsBlock> CODEC = simpleCodec(SapCollectorsBlock::new);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<FillType> FILL = EnumProperty.create("fill", FillType.class);
    private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(
            ImmutableMap.of(
                    Direction.NORTH,
                    Block.box(5.5, 3.0, 11.0, 10.5, 13.0, 16.0),
                    Direction.SOUTH,
                    Block.box(5.5, 3.0, 0.0, 10.5, 13.0, 5.0),
                    Direction.WEST,
                    Block.box(11.0, 3.0, 5.5, 16.0, 13.0, 10.5),
                    Direction.EAST,
                    Block.box(0.0, 3.0, 5.5, 5.0, 13.0, 10.5)
            )
    );

    public SapCollectorsBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(FILL, FillType.NONE));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = this.defaultBlockState();
        LevelReader levelreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Direction[] adirection = context.getNearestLookingDirections();
        for (Direction direction : adirection) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction1 = direction.getOpposite();
                blockstate = blockstate.setValue(FACING, direction1);
                if (blockstate.canSurvive(levelreader, blockpos)) {
                    return blockstate;
                }
            }
        }
        return null;
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!stack.is(Items.GLASS_BOTTLE)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        FillType fillType = state.getValue(FILL);
        ItemStack resultItem = getResultItemForFillType(fillType);
        if (stack.is(Items.GLASS_BOTTLE) && !player.isCreative()) {
            stack.shrink(1);
            if (stack.isEmpty()) {
                player.setItemInHand(hand, resultItem);
            } else if (!player.getInventory().add(resultItem)) {
                player.drop(resultItem, false);
            }
        } else {
            player.getInventory().placeItemBackInInventory(resultItem);
        }
        level.playSound(player, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
        if (!level.isClientSide) {
            level.setBlockAndUpdate(pos, state.setValue(FILL, FillType.NONE));
        }
        return ItemInteractionResult.SUCCESS;
    }

    private ItemStack getResultItemForFillType(FillType fillType) {
        return switch (fillType) {
            case MAPLE_SYRUP -> new ItemStack(CDMaterialItems.MAPLE_SYRUP.get());
            case WARPED_SAP -> new ItemStack(CDMaterialItems.WARPED_SAP.get());
            case GLOWING_MUSHROOM_SAUCE -> new ItemStack(CDMaterialItems.GLOWING_MUSHROOM_SAUCE.get());
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return getDirectSignal(state, level, pos, direction);
    }

    @Override
    public int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        FillType fillType = state.getValue(FILL);
        if (fillType != FillType.NONE) {
            return 15;
        }
        return 0;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction facing = state.getValue(FACING);
        BlockPos blockpos = pos.relative(facing.getOpposite());
        BlockState blockstate = level.getBlockState(blockpos);
        return blockstate.isFaceSturdy(level, blockpos, facing);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return facing.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : state;
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }


    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AABBS.get(state.getValue(FACING));
    }

    @Override
    protected MapCodec<SapCollectorsBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, FILL);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new Entity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : LibUtils.getTicker(blockEntityType, CDBlocks.SAP_COLLECTORS_BLOCK_ENTITY.get(), SapCollectorsBlock.Entity::serverTick);
    }

    public static class Entity extends BlockEntity {
        private int timer = 0;

        public Entity(BlockPos pos, BlockState blockState) {
            super(CDBlocks.SAP_COLLECTORS_BLOCK_ENTITY.get(), pos, blockState);
        }

        public static void serverTick(Level level, BlockPos pos, BlockState state, SapCollectorsBlock.Entity blockEntity) {
            Direction facingDirection = state.getValue(SapCollectorsBlock.FACING);
            BlockPos neighborPos = pos.relative(facingDirection.getOpposite());
            BlockState neighborBlockState = level.getBlockState(neighborPos);
            FillType currentFillType = state.getValue(SapCollectorsBlock.FILL);
            if (currentFillType != FillType.NONE) return;
            blockEntity.timer++;
            if (blockEntity.timer >= 600) {
                if (neighborBlockState.is(Blocks.BIRCH_LOG)) {
                    level.setBlockAndUpdate(pos, state.setValue(SapCollectorsBlock.FILL, FillType.MAPLE_SYRUP));
                } else if (neighborBlockState.is(Blocks.WARPED_STEM)) {
                    level.setBlockAndUpdate(pos, state.setValue(SapCollectorsBlock.FILL, FillType.WARPED_SAP));
                } else if (neighborBlockState.is(NatureBlocks.GLOWING_MUSHROOM_STEM_BLOCK)) {
                    level.setBlockAndUpdate(pos, state.setValue(SapCollectorsBlock.FILL, FillType.GLOWING_MUSHROOM_SAUCE));
                }
                blockEntity.timer = 0;
            }
        }
    }

    public static class SapCollectorDispenseBehavior implements DispenseItemBehavior {
        @Override
        public ItemStack dispense(BlockSource source, ItemStack stack) {
            Level level = source.level();
            BlockPos pos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
            BlockState blockState = level.getBlockState(pos);
            if (blockState.getBlock() instanceof SapCollectorsBlock) {
                SapCollectorsBlock.FillType fillType = blockState.getValue(SapCollectorsBlock.FILL);
                if (stack.getItem() == Items.GLASS_BOTTLE) {
                    ItemStack resultItem = switch (fillType) {
                        case MAPLE_SYRUP -> new ItemStack(CDMaterialItems.MAPLE_SYRUP.get());
                        case WARPED_SAP -> new ItemStack(CDMaterialItems.WARPED_SAP.get());
                        case GLOWING_MUSHROOM_SAUCE -> new ItemStack(CDMaterialItems.GLOWING_MUSHROOM_SAUCE.get());
                        default -> stack;
                    };
                    stack.shrink(1);
                    ItemStack remainingStack = source.blockEntity().insertItem(resultItem);
                    level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    if (!remainingStack.isEmpty()) {
                        Direction direction = source.state().getValue(DispenserBlock.FACING);
                        DefaultDispenseItemBehavior.spawnItem(level, remainingStack, 6, direction, DispenserBlock.getDispensePosition(source));
                    } else {
                        level.setBlockAndUpdate(pos, blockState.setValue(SapCollectorsBlock.FILL, FillType.NONE));
                    }
                    return stack;
                }
            }
            return DispenseItemBehavior.NOOP.dispense(source, stack);
        }
    }

    public enum FillType implements StringRepresentable {
        GLOWING_MUSHROOM_SAUCE,
        MAPLE_SYRUP,
        WARPED_SAP,
        NONE;

        @Override
        public String getSerializedName() {
            return this.name().toLowerCase();
        }
    }
}
