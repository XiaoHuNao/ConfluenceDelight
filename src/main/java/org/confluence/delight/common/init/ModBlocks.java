package org.confluence.delight.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.block.food.*;

import java.util.function.Supplier;

import static org.confluence.delight.ConfluenceDelight.chineseProviders;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ConfluenceDelight.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ConfluenceDelight.MODID);

    public static final DeferredHolder<Block, Block> CHICKEN_HOT_POT = registerWithoutItem("chicken_hot_pot", ChickenStewBlock::new);

    public static DeferredHolder<Block, Block> register(final String en, final String zh) {
        DeferredHolder<Block, Block> block = BLOCKS.register(en, () -> new Block(BlockBehaviour.Properties.of()));
        chineseProviders.add(l -> l.addBlock(block, zh));
        return block;
    }

    public static <B extends Block> DeferredBlock<B> registerWithoutItem(final String en, Supplier<B> bl) {
        return BLOCKS.register(en, bl);
    }

    public static DeferredHolder<Block, Block> registerBlockItem(final String en, final String zh, Supplier<? extends Block> bl) {
        DeferredHolder<Block, Block> block = BLOCKS.register(en, bl);
        ModMaterialItems.register(en, zh, () -> new BlockItem(block.get(), new Item.Properties()));
        chineseProviders.add(l -> l.addBlock(block, zh));
        return block;
    }
}
