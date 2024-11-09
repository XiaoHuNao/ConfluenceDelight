package com.xiaohunao.confluencedelight.common.init;

import com.xiaohunao.confluencedelight.ConfluenceDelight;
import com.xiaohunao.confluencedelight.common.block.SimpleFeastBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.registry.ModItems;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, ConfluenceDelight.MODID);

    public static final DeferredHolder<Block,SimpleFeastBlock> SIMPLE_FEAST_BLOCK = BLOCKS.register("simple_feast_block", () -> new SimpleFeastBlock(Block.Properties.ofFullCopy(Blocks.PUMPKIN), ModItems.STUFFED_PUMPKIN, false));

}
