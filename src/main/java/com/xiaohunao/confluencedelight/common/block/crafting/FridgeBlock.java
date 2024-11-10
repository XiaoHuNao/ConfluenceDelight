package com.xiaohunao.confluencedelight.common.block.crafting;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class FridgeBlock extends Block {
    private static final VoxelShape SHAPE = Shapes.box(0.05, 0, 0.05,
            0.95, 2, 0.95);

    public FridgeBlock() {
        super(Properties.ofFullCopy(Blocks.IRON_BLOCK));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
