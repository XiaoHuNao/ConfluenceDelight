package org.confluence.delight.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.block.common.BrineCauldronBlock;
import org.confluence.delight.common.block.common.SaltCauldronBlock;
import org.confluence.delight.common.block.common.WineCauldronBlock;
import org.confluence.delight.common.block.function.SapCollectorsBlock;
import org.confluence.delight.common.block.function.crafting.*;

import java.util.function.Supplier;

import static net.minecraft.world.level.block.Blocks.ACACIA_PLANKS;
import static org.confluence.delight.ConfluenceDelight.chineseProviders;

public class CDBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ConfluenceDelight.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, ConfluenceDelight.MODID);

    public static final DeferredBlock<PickleJarsBlock> PICKLE_JARS_BLOCK = registerWithItem("pickle_jars_block", "泡菜罐", () -> new PickleJarsBlock(BlockBehaviour.Properties.of().strength(1.0f).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<BlockEntityType<PickleJarsBlockEntity>> PICKLE_JARS_BLOCK_ENTITY = BLOCK_ENTITIES.register("pickle_jars_block", () -> BlockEntityType.Builder.of(PickleJarsBlockEntity::new, PICKLE_JARS_BLOCK.get()).build(null));
    public static final DeferredBlock<SapCollectorsBlock> SAP_COLLECTORS_BLOCK = registerWithItem("sap_collectors_block", "树液采集器", () -> new SapCollectorsBlock(BlockBehaviour.Properties.of().strength(1.0f).pushReaction(PushReaction.DESTROY)));
    public static final Supplier<BlockEntityType<SapCollectorsBlock.Entity>> SAP_COLLECTORS_BLOCK_ENTITY = BLOCK_ENTITIES.register("sap_collectors_block", () -> BlockEntityType.Builder.of(SapCollectorsBlock.Entity::new, SAP_COLLECTORS_BLOCK.get()).build(null));
    public static final DeferredBlock<MillStoneBlock> MILLSTONE_BLOCK = registerWithItem("millstone_block", "磨盘", () -> new MillStoneBlock(BlockBehaviour.Properties.of().strength(1.0f)));
    public static final Supplier<BlockEntityType<MillStoneBlockEntity>> MILLSTONE_BLOCK_ENTITY = BLOCK_ENTITIES.register("millstone_block", () -> BlockEntityType.Builder.of(MillStoneBlockEntity::new, MILLSTONE_BLOCK.get()).build(null));
    public static final DeferredBlock<JuicerBlock> JUICER_BLOCK = registerWithItem("juicer_block", "榨汁机", () -> new JuicerBlock(BlockBehaviour.Properties.of().strength(1.0f)));
    public static final Supplier<BlockEntityType<JuicerBlockEntity>> JUICER_BLOCK_ENTITY = BLOCK_ENTITIES.register("juicer_block", () -> BlockEntityType.Builder.of(JuicerBlockEntity::new, JUICER_BLOCK.get()).build(null));

    public static final DeferredBlock<LiquidBlock> WINE = registerWithoutItem("wine", "酒", () -> new LiquidBlock(CDFluids.WINE.fluid().get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).mapColor(DyeColor.WHITE)));
    public static final DeferredBlock<LiquidBlock> BRINE = registerWithoutItem("brine", "卤水", () -> new LiquidBlock(CDFluids.BRINE.fluid().get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).mapColor(DyeColor.BROWN)));
    public static final DeferredBlock<WineCauldronBlock> WINE_CAULDRON = registerWithoutItem("wine_cauldron", "装有酒的炼药锅", () -> new WineCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WATER_CAULDRON)));
    public static final DeferredBlock<BrineCauldronBlock> BRINE_CAULDRON = registerWithoutItem("brine_cauldron", "装有卤水的炼药锅", () -> new BrineCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WATER_CAULDRON)));
    public static final Supplier<BlockEntityType<BrineCauldronBlock.Entity>> BRINE_CAULDRON_ENTITY = BLOCK_ENTITIES.register("brine_cauldron", () -> BlockEntityType.Builder.of(BrineCauldronBlock.Entity::new, BRINE_CAULDRON.get()).build(null));

    public static final DeferredBlock<Block> SALT_BLOCK = registerWithItem("salt_block", "盐块", () -> new Block(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> SALT_CAULDRON = registerWithoutItem("salt_cauldron", "装有盐的炼药锅", () -> new SaltCauldronBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WATER_CAULDRON)));

    // 箱装水果
    public static final DeferredBlock<Block> APRICOT_CRATE = registerWithItem("apricot_crate", "箱装杏子", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_ORANGE)));
    public static final DeferredBlock<Block> GRAPE_FRUIT_CRATE = registerWithItem("grape_fruit_crate", "箱装葡萄柚", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_CYAN)));
    public static final DeferredBlock<Block> LEMON_CRATE = registerWithItem("lemon_crate", "箱装柠檬", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_YELLOW)));
    public static final DeferredBlock<Block> PEACH_CRATE = registerWithItem("peach_crate", "箱装桃子", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_ORANGE)));
    public static final DeferredBlock<Block> CHERRY_CRATE = registerWithItem("cherry_crate", "箱装樱桃", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_RED)));
    public static final DeferredBlock<Block> PLUM_CRATE = registerWithItem("plum_crate", "箱装李子", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_PURPLE)));
    public static final DeferredBlock<Block> BLOOD_ORANGE_CRATE = registerWithItem("blood_orange_crate", "箱装血橙", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_RED)));
    public static final DeferredBlock<Block> DRAGON_FRUIT_CRATE = registerWithItem("dragon_fruit_crate", "箱装火龙果", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_RED)));
    public static final DeferredBlock<Block> RAMBUTAN_CRATE = registerWithItem("rambutan_crate", "箱装红毛丹", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_RED)));
    public static final DeferredBlock<Block> MANGO_CRATE = registerWithItem("mango_crate", "箱装芒果", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_ORANGE)));
    public static final DeferredBlock<Block> BANANA_CRATE = registerWithItem("banana_crate", "箱装香蕉", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_YELLOW)));
    public static final DeferredBlock<Block> COCONUT_CRATE = registerWithItem("coconut_crate", "箱装椰子", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_ORANGE)));
    public static final DeferredBlock<Block> STAR_FRUIT_CRATE = registerWithItem("star_fruit_crate", "箱装杨桃", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_YELLOW)));
    public static final DeferredBlock<Block> POMEGRANATE_CRATE = registerWithItem("pomegranate_crate", "箱装石榴", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_RED)));
    public static final DeferredBlock<Block> BLACKCURRANT_CRATE = registerWithItem("blackcurrant_crate", "箱装黑醋栗", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_BLACK)));
    public static final DeferredBlock<Block> BLOODY_MOSCATO_CRATE = registerWithItem("bloody_moscato_crate", "箱装血腥麝香葡萄", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_PURPLE)));
    public static final DeferredBlock<Block> ELDERBERRY_CRATE = registerWithItem("elderberry_crate", "箱装接骨木果", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_PURPLE)));
    public static final DeferredBlock<Block> PINEAPPLE_CRATE = registerWithItem("pineapple_crate", "箱装菠萝", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_YELLOW)));
    public static final DeferredBlock<Block> SPICY_PEPPER_CRATE = registerWithItem("spicy_pepper_crate", "箱装辣椒", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_RED)));

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

    public static <B extends Block> DeferredBlock<B> registerWithItem(final String en, final String zh, Supplier<B> bl) {
        DeferredBlock<B> block = BLOCKS.register(en, bl);
        CDItems.BLOCK_ITEMS.registerSimpleBlockItem(block, new Item.Properties());
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
