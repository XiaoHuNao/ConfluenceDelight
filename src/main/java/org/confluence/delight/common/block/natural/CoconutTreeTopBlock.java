package org.confluence.delight.common.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.confluence.delight.common.init.CDNaturalBlocks;

public class CoconutTreeTopBlock extends Block {
    public CoconutTreeTopBlock() {
        super(BlockBehaviour.Properties.of().randomTicks());
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        Direction randomHorizontalDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos targetPos = pos.relative(randomHorizontalDir);
        BlockState targetState = level.getBlockState(targetPos);
        if (targetState.isAir()) {
            Direction coconutFacing = randomHorizontalDir.getOpposite();
            level.setBlockAndUpdate(
                    targetPos,
                    CDNaturalBlocks.COCONUT_BLOCK.get()
                            .defaultBlockState()
                            .setValue(CoconutBlock.PIECE, 0)
                            .setValue(CoconutBlock.FACING, coconutFacing));
        } else if (targetState.is(CDNaturalBlocks.COCONUT_BLOCK.get())) {
            int currentPiece = targetState.getValue(CoconutBlock.PIECE);
            if (currentPiece < 3) {
                level.setBlockAndUpdate(
                        targetPos,
                        targetState.setValue(CoconutBlock.PIECE, currentPiece + 1)
                );
            }
        }
    }
}
