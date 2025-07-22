package org.confluence.delight.common.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.confluence.delight.common.init.CDNaturalBlocks;

public class BananaTrunkBlock extends Block {
    public BananaTrunkBlock() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).randomTicks());
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        Direction randomHorizontalDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos targetPos = pos.relative(randomHorizontalDir);
        BlockState targetState = level.getBlockState(targetPos);
        if (targetState.isAir() && random.nextFloat() < 0.35) {
            Direction bananaFacing = randomHorizontalDir.getOpposite();
            level.setBlockAndUpdate(targetPos, CDNaturalBlocks.BANANA_TREE_TOP_BLOCK.get().defaultBlockState().setValue(BananaTreeTopBlock.FACING, bananaFacing));
        }
    }
}
