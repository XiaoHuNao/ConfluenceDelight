package org.confluence.delight.common.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.confluence.delight.common.init.CDNaturalBlocks;

public class DragonFruitCactusBlock extends CactusBlock {
    public DragonFruitCactusBlock() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.CACTUS));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState blockstate1 = level.getBlockState(pos.below());
        net.neoforged.neoforge.common.util.TriState soilDecision = blockstate1.canSustainPlant(level, pos.below(), Direction.UP, state);
        if (!soilDecision.isDefault()) return soilDecision.isTrue();
        return (blockstate1.is(this) || blockstate1.is(BlockTags.SAND)) && !level.getBlockState(pos.above()).liquid();
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockPos blockpos = pos.above();
        if (level.isEmptyBlock(blockpos)) {
            int i = 1;
            while (level.getBlockState(pos.below(i)).is(this)) {
                i++;
            }
            if (i < 5) {
                int j = state.getValue(AGE);
                if (net.neoforged.neoforge.common.CommonHooks.canCropGrow(level, blockpos, state, true)) {
                    if (j == 15) {
                        level.setBlockAndUpdate(blockpos, this.defaultBlockState());
                        BlockState blockstate = state.setValue(AGE, 0);
                        level.setBlockAndUpdate(pos, blockstate);
                        level.neighborChanged(blockstate, blockpos, this, pos, false);
                    } else {
                        level.setBlockAndUpdate(pos, state.setValue(AGE, j + 1));
                    }
                    net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(level, pos, state);
                }
            }
        }
        if (state.getValue(AGE) == 15) {
            Direction[] directions = Direction.Plane.HORIZONTAL.stream().toList().toArray(new Direction[0]);
            Direction selectedDirection = directions[random.nextInt(directions.length)];
            BlockPos neighborPos = pos.relative(selectedDirection);
            if (level.isEmptyBlock(neighborPos)) {
                level.setBlockAndUpdate(neighborPos, CDNaturalBlocks.DRAGON_FRUIT_BLOCK.get().defaultBlockState()
                        .setValue(DragonFruitBlock.FACING, selectedDirection.getOpposite())
                        .setValue(DragonFruitBlock.AGE, 0));
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
    }
}

