package org.confluence.delight.common.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.confluence.delight.common.init.CDNaturalBlocks;

public class BananaTreeTopBlock extends Block {

    public BananaTreeTopBlock() {
        super(BlockBehaviour.Properties.of().noCollission().instabreak().randomTicks().pushReaction(PushReaction.DESTROY));
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            if (level.getBlockState(pos.relative(dir)).is(CDNaturalBlocks.BANANA_SKEWERS_BLOCK)) {
                return;
            }
        }
        Direction growthDir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos targetPos = pos.relative(growthDir);
        if (level.getBlockState(targetPos).isAir() && random.nextFloat() < 0.35) {
            level.setBlockAndUpdate(targetPos, CDNaturalBlocks.BANANA_SKEWERS_BLOCK.get().defaultBlockState());
        }
    }
}
