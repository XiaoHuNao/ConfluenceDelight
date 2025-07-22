package org.confluence.delight.common.block.natural;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.confluence.mod.common.block.natural.PalmLeaves;
import org.confluence.mod.common.init.block.NatureBlocks;

public class HalfLeavesBlock extends PalmLeaves {
    public HalfLeavesBlock() {
        super(BlockBehaviour.Properties.ofFullCopy(NatureBlocks.PALM_LOG_BLOCKS.getLeaves().get()));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
    }
}
