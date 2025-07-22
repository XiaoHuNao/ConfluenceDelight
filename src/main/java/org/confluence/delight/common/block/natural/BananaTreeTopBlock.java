package org.confluence.delight.common.block.natural;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.PushReaction;
import org.confluence.delight.common.init.CDNaturalBlocks;

public class BananaTreeTopBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public BananaTreeTopBlock() {
        super(BlockBehaviour.Properties.of().strength(1.0f).pushReaction(PushReaction.DESTROY).randomTicks());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockPos belowPos = pos.below();
        BlockState belowState = level.getBlockState(belowPos);
        if (belowState.isAir() && !belowState.is(CDNaturalBlocks.BANANA_SKEWERS_BLOCK) && random.nextFloat() < 0.35) {
            level.setBlockAndUpdate(belowPos, CDNaturalBlocks.BANANA_SKEWERS_BLOCK.get().defaultBlockState());
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos.relative(state.getValue(FACING)));
        net.neoforged.neoforge.common.util.TriState soilDecision = blockstate.canSustainPlant(level, pos.relative(state.getValue(FACING)), state.getValue(FACING).getOpposite(), state);
        if (!soilDecision.isDefault()) return soilDecision.isTrue();
        return blockstate.is(CDNaturalBlocks.BANANA_TRUNK_BLOCK);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return facing == state.getValue(FACING) && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
