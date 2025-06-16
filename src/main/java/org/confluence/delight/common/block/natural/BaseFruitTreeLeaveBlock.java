package org.confluence.delight.common.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.IShearable;

import static net.neoforged.neoforge.common.CommonHooks.canCropGrow;

public class BaseFruitTreeLeaveBlock extends LeavesBlock implements BonemealableBlock, SimpleWaterloggedBlock, IShearable {
    private final Item fruit;
    private static final IntegerProperty AGE = BlockStateProperties.AGE_15;

    public BaseFruitTreeLeaveBlock(Item fruit) {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
        this.fruit = fruit;
        registerDefaultState(this.stateDefinition.any()
                .setValue(AGE, 0)
                .setValue(WATERLOGGED, false)
                .setValue(PERSISTENT, false)
                .setValue(DISTANCE, 7));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return notPlacedByPlayer(state);
    }

    public boolean notPlacedByPlayer(BlockState state) {
        return shouldDecay(state) || canGrow(state) || state.getValue(AGE) == 0;
    }

    public boolean shouldDecay(BlockState state) {
        return state.getValue(DISTANCE) == 7 && !state.getValue(PERSISTENT);
    }

    public boolean canGrow(BlockState state) {
        return state.getValue(AGE) < 15 && (!state.getValue(PERSISTENT) || state.getValue(DISTANCE) < 7);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (shouldDecay(state)) {
            dropResources(state, level, pos);
            level.removeBlock(pos, false);
        } else if (canGrow(state) && level.getRawBrightness(pos.above(), 0) >= 9 && canCropGrow(level, pos, state, random.nextInt(5) == 0)) {
            level.setBlockAndUpdate(pos, state.setValue(AGE, state.getValue(AGE) + 1));
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlock(pos, updateDistance(state, level, pos), 3);
    }

    private static BlockState updateDistance(BlockState state, LevelAccessor level, BlockPos pos) {
        int i = 7;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (Direction direction : Direction.values()) {
            blockpos$mutableblockpos.setWithOffset(pos, direction);
            i = Math.min(i, getDistanceAt(level.getBlockState(blockpos$mutableblockpos)) + 1);
            if (i == 1) {
                break;
            }
        }

        return state.setValue(DISTANCE, Integer.valueOf(i));
    }

    private static int getDistanceAt(BlockState neighbor) {
        return getOptionalDistanceAt(neighbor).orElse(7);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int currentAge = state.getValue(AGE);
        if (!state.getValue(PERSISTENT) && state.getValue(DISTANCE) < 7 && currentAge >= 13) {
            if (!level.isClientSide()) {
                ItemStack fruitStack = new ItemStack(fruit, 1);
                player.getInventory().add(fruitStack);
                int newAge = Math.max(12, currentAge - 1);
                level.setBlockAndUpdate(pos, state.setValue(AGE, newAge));
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE, WATERLOGGED, DISTANCE, PERSISTENT);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < 15;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        int currentAge = state.getValue(AGE);
        if (currentAge < 15 && !state.getValue(PERSISTENT)) {
            int newAge = Math.min(15, currentAge + random.nextInt(3) + 1);
            level.setBlockAndUpdate(pos, state.setValue(AGE, newAge));
        }
    }
}
