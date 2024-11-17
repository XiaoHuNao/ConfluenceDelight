package com.xiaohunao.confluencedelight.common.init;

import com.xiaohunao.confluencedelight.ConfluenceDelight;
import com.xiaohunao.confluencedelight.common.block.SimpleFeastBlock;
import com.xiaohunao.confluencedelight.common.block.crafting.FridgeBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Supplier;

import static com.xiaohunao.confluencedelight.ConfluenceDelight.chineseProviders;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, ConfluenceDelight.MODID);
    public static final DeferredRegister<BlockEntityType<?>> ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ConfluenceDelight.MODID);

    public static final DeferredHolder<Block,Block> SIMPLE_FEAST_BLOCK = register("simple_feast", "",   //todo zh
            () -> new SimpleFeastBlock(Block.Properties.ofFullCopy(Blocks.PUMPKIN), ModItems.STUFFED_PUMPKIN, false));

    //crafting
    public static final DeferredHolder<Block, Block> FRIDGE =
            register("fridge", "冰箱", FridgeBlock::new);


    public static final Supplier<BlockEntityType<FridgeBlock.Entity>> FRIDGE_ENTITY = ENTITIES.register("fridge_entity",
            () -> BlockEntityType.Builder.of(FridgeBlock.Entity::new, new Block[]{FRIDGE.get()})
                    .build(null));

    public static DeferredHolder<Block, Block> register(final String en, final String zh) {
        DeferredHolder<Block, Block> block = BLOCKS.register(en, () -> new Block(BlockBehaviour.Properties.of()));
        com.xiaohunao.confluencedelight.common.init.ModItems.registerItem(en, zh, () -> new BlockItem(block.get(), new Item.Properties()));
        chineseProviders.add(l -> l.addBlock(block, zh));
        return block;
    }

    public static DeferredHolder<Block, Block> register(final String en, final String zh, Supplier<? extends Block> bl) {
        DeferredHolder<Block, Block> block = BLOCKS.register(en, bl);
        com.xiaohunao.confluencedelight.common.init.ModItems.registerItem(en, zh, () -> new BlockItem(block.get(), new Item.Properties()));
        chineseProviders.add(l -> l.addBlock(block, zh));
        return block;
    }
}
