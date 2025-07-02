package org.confluence.delight.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.block.function.crafting.*;
import org.confluence.delight.common.block.function.crafting.SapCollectorsBlock;

import java.util.function.Supplier;

import static org.confluence.delight.ConfluenceDelight.chineseProviders;

public class CDBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ConfluenceDelight.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ConfluenceDelight.MODID);

    public static final DeferredBlock<PickleJarsBlock> PICKLE_JARS_BLOCK = registerBlockItem("pickle_jars_block", "泡菜罐", () -> new PickleJarsBlock(BlockBehaviour.Properties.of().strength(1.0f).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<BlockEntityType<PickleJarsBlockEntity>> PICKLE_JARS_BLOCK_ENTITY = BLOCK_ENTITIES.register("pickle_jars_block", () -> BlockEntityType.Builder.of(PickleJarsBlockEntity::new, PICKLE_JARS_BLOCK.get()).build(null));
    public static final DeferredBlock<SapCollectorsBlock> SAP_COLLECTORS_BLOCK = registerBlockItem("sap_collectors_block", "树液采集器", () -> new SapCollectorsBlock(BlockBehaviour.Properties.of().strength(1.0f).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<BlockEntityType<SapCollectorsBlock.Entity>> SAP_COLLECTORS_BLOCK_ENTITY = BLOCK_ENTITIES.register("sap_collectors_block", () -> BlockEntityType.Builder.of(SapCollectorsBlock.Entity::new, SAP_COLLECTORS_BLOCK.get()).build(null));
    public static final DeferredBlock<MillStoneBlock> MILLSTONE_BLOCK = registerBlockItem("millstone_block", "磨盘", () -> new MillStoneBlock(BlockBehaviour.Properties.of().strength(1.0f)));
    public static final Supplier<BlockEntityType<MillStoneBlockEntity>> MILLSTONE_BLOCK_ENTITY = BLOCK_ENTITIES.register("millstone_block", () -> BlockEntityType.Builder.of(MillStoneBlockEntity::new, MILLSTONE_BLOCK.get()).build(null));
    public static final DeferredBlock<JuicerBlock> JUICER_BLOCK = registerBlockItem("juicer_block", "榨汁机", () -> new JuicerBlock(BlockBehaviour.Properties.of().strength(1.0f)));
    public static final Supplier<BlockEntityType<JuicerBlockEntity>> JUICER_BLOCK_ENTITY = BLOCK_ENTITIES.register("juicer_block", () -> BlockEntityType.Builder.of(JuicerBlockEntity::new, JUICER_BLOCK.get()).build(null));
    //流体
    public static final DeferredBlock<LiquidBlock> WINE = registerWithoutItem("wine", "酒", () -> new LiquidBlock(CDFluids.WINE.fluid().get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).mapColor(DyeColor.WHITE)));


    public static DeferredHolder<Block, Block> register(final String en, final String zh) {
        DeferredHolder<Block, Block> block = BLOCKS.register(en, () -> new Block(BlockBehaviour.Properties.of()));
        chineseProviders.add(l -> l.addBlock(block, zh));
        return block;
    }

    public static <B extends Block> DeferredBlock<B> registerWithoutItem(final String en, Supplier<B> bl) {
        return BLOCKS.register(en, bl);
    }

    public static <B extends Block> DeferredBlock<B> registerWithoutItem(final String en, final String zh, Supplier<B> bl) {
        DeferredBlock<B> block = BLOCKS.register(en, bl);
        chineseProviders.add(l -> l.addBlock(block, zh));
        return block;
    }

    public static <B extends Block> DeferredBlock<B> registerBlockItem(final String en, final String zh, Supplier<B> bl) {
        DeferredBlock<B> block = BLOCKS.register(en, bl);
        CDMaterialItems.register(en, zh, () -> new BlockItem(block.get(), new Item.Properties()));
        chineseProviders.add(l -> l.addBlock(block, zh));
        return block;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ENTITIES.register(eventBus);
        CDNaturalBlocks.BLOCKS.register(eventBus);
        CDNaturalBlocks.BLOCK_ITEMS.register(eventBus);
        CDFoodBlocks.BLOCKS.register(eventBus);
    }
}
