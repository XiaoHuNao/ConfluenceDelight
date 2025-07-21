package org.confluence.delight.common.init;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.block.natural.*;
import org.confluence.delight.common.item.ToolTipBlockItem;
import org.confluence.mod.common.block.natural.sapling.BaseSaplingBlock;
import org.confluence.mod.common.init.block.NatureBlocks;
import org.confluence.mod.common.init.item.FoodItems;
import vectorwing.farmersdelight.common.block.WildCropBlock;

import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.world.level.block.Blocks.ACACIA_PLANKS;
import static net.minecraft.world.level.block.Blocks.STONE_BRICKS;
import static org.confluence.delight.ConfluenceDelight.chineseProviders;
import static org.confluence.mod.common.init.block.ModBlocks.registerWithItem;

public class CDNaturalBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ConfluenceDelight.MODID);
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(ConfluenceDelight.MODID);

    //树苗
    public static final DeferredBlock<BaseSaplingBlock> APPLE_SAPLING = registerBlockItem("apple_sapling", "苹果树苗", () -> new BaseSaplingBlock(CDFeature.TreeGrowers.APPLE_GROWER, BlockTags.DIRT), block -> new ToolTipBlockItem(block, Component.translatable("tooltip.item.confluence_delight.apple_sapling")));
    public static final DeferredBlock<BaseSaplingBlock> APRICOT_SAPLING = registerBlockItem("apricot_sapling", "杏树苗", () -> new BaseSaplingBlock(CDFeature.TreeGrowers.APRICOT_GROWER, BlockTags.DIRT), block -> new ToolTipBlockItem(block, Component.translatable("tooltip.item.confluence_delight.apricot_sapling")));
    public static final DeferredBlock<BaseSaplingBlock> GRAPEFRUIT_SAPLING = registerBlockItem("grapefruit_sapling", "葡萄柚树苗", () -> new BaseSaplingBlock(CDFeature.TreeGrowers.GRAPEFRUIT_GROWER, BlockTags.DIRT), block -> new ToolTipBlockItem(block, Component.translatable("tooltip.item.confluence_delight.grapefruit_sapling")));
    public static final DeferredBlock<BaseSaplingBlock> LEMON_SAPLING = registerBlockItem("lemon_sapling", "柠檬树苗", () -> new BaseSaplingBlock(CDFeature.TreeGrowers.LEMON_GROWER, BlockTags.DIRT), block -> new ToolTipBlockItem(block, Component.translatable("tooltip.item.confluence_delight.lemon_sapling")));
    public static final DeferredBlock<BaseSaplingBlock> PEACH_SAPLING = registerBlockItem("peach_sapling", "桃树苗", () -> new BaseSaplingBlock(CDFeature.TreeGrowers.PEACH_GROWER, BlockTags.DIRT), block -> new ToolTipBlockItem(block, Component.translatable("tooltip.item.confluence_delight.peach_sapling")));
    public static final DeferredBlock<BaseSaplingBlock> CHERRY_SAPLING = registerBlockItem("cherry_sapling", "樱桃树苗", () -> new BaseSaplingBlock(CDFeature.TreeGrowers.CHERRY_GROWER, BlockTags.DIRT), block -> new ToolTipBlockItem(block, Component.translatable("tooltip.item.confluence_delight.cherry_sapling")));
    public static final DeferredBlock<BaseSaplingBlock> PLUM_SAPLING = registerBlockItem("plum_sapling", "李子树苗", () -> new BaseSaplingBlock(CDFeature.TreeGrowers.PLUM_GROWER, BlockTags.DIRT), block -> new ToolTipBlockItem(block, Component.translatable("tooltip.item.confluence_delight.plum_sapling")));
    public static final DeferredBlock<BaseSaplingBlock> BLOOD_ORANGE_SAPLING = registerBlockItem("blood_orange_sapling", "血橙树苗", () -> new BaseSaplingBlock(CDFeature.TreeGrowers.BLOOD_ORANGE_GROWER, BlockTags.DIRT), block -> new ToolTipBlockItem(block, Component.translatable("tooltip.item.confluence_delight.blood_orange_sapling")));
    public static final DeferredBlock<BaseSaplingBlock> RAMBUTAN_SAPLING = registerBlockItem("rambutan_sapling", "红毛丹树苗", () -> new BaseSaplingBlock(CDFeature.TreeGrowers.RAMBUTAN_GROWER, BlockTags.DIRT), block -> new ToolTipBlockItem(block, Component.translatable("tooltip.item.confluence_delight.rambutan_sapling")));
    public static final DeferredBlock<BaseSaplingBlock> MANGO_SAPLING = registerBlockItem("mango_sapling", "芒果树苗", () -> new BaseSaplingBlock(CDFeature.TreeGrowers.MANGO_GROWER, BlockTags.DIRT), block -> new ToolTipBlockItem(block, Component.translatable("tooltip.item.confluence_delight.mango_sapling")));
    public static final DeferredBlock<BaseSaplingBlock> BANANA_SAPLING = registerBlockItem("banana_sapling", "香蕉树苗", () -> new BaseSaplingBlock(CDFeature.TreeGrowers.BANANA_GROWER, BlockTags.DIRT), block -> new ToolTipBlockItem(block, Component.translatable("tooltip.item.confluence_delight.banana_sapling")));
    public static final DeferredBlock<BaseSaplingBlock> COCONUT_SAPLING = registerBlockItem("coconut_sapling", "椰子树苗", () -> new BaseSaplingBlock(CDFeature.TreeGrowers.COCONUT_GROWER, BlockTags.DIRT), block -> new ToolTipBlockItem(block, Component.translatable("tooltip.item.confluence_delight.coconut_sapling")));
    public static final DeferredBlock<BaseSaplingBlock> STAR_FRUIT_SAPLING = registerBlockItem("star_fruit_sapling", "杨桃树苗", () -> new BaseSaplingBlock(CDFeature.TreeGrowers.STAR_FRUIT_GROWER, BlockTags.DIRT), block -> new ToolTipBlockItem(block, Component.translatable("tooltip.item.confluence_delight.star_fruit_sapling")));
    public static final DeferredBlock<BaseSaplingBlock> POMEGRANATE_SAPLING = registerBlockItem("pomegranate_sapling", "石榴树苗", () -> new BaseSaplingBlock(CDFeature.TreeGrowers.POMEGRANATE_GROWER, BlockTags.DIRT), block -> new ToolTipBlockItem(block, Component.translatable("tooltip.item.confluence_delight.pomegranate_sapling")));

    public static final DeferredBlock<CoconutTreeTopBlock> COCONUT_TREE_TOP_BLOCK = registerBlockItem("coconut_tree_top", "椰子树头", CoconutTreeTopBlock::new);

    //灌木
    public static final DeferredBlock<BaseFruitShrubBlock> BLACKCURRANT_SHRUB_BLOCK = registerBlockItem("blackcurrant_shrub", "黑醋栗灌木", () -> new BaseFruitShrubBlock(FoodItems.BLACKCURRANT), block -> new ToolTipBlockItem(block, Component.translatable("tooltip.item.confluence_delight.blackcurrant_shrub")));
    public static final DeferredBlock<BaseFruitShrubBlock> ELDERBERRY_SHRUB_BLOCK = registerBlockItem("elderberry_shrub", "接骨木灌木", () -> new BaseFruitShrubBlock(FoodItems.ELDERBERRY), block -> new ToolTipBlockItem(block, Component.translatable("tooltip.item.confluence_delight.elderberry_shrub")));

    //树叶
    public static final DeferredBlock<BaseFruitTreeLeaveBlock> APPLE_TREE_LEAVES_BLOCK = registerBlockItem("apple_tree_leaves", "苹果树叶", () -> new BaseFruitTreeLeaveBlock(Items.APPLE));
    public static final DeferredBlock<BaseFruitTreeLeaveBlock> APRICOT_TREE_LEAVES_BLOCK = registerBlockItem("apricot_tree_leaves", "杏树叶", () -> new BaseFruitTreeLeaveBlock(FoodItems.APRICOT));
    public static final DeferredBlock<BaseFruitTreeLeaveBlock> GRAPEFRUIT_TREE_LEAVES_BLOCK = registerBlockItem("grapefruit_tree_leaves", "葡萄柚树叶", () -> new BaseFruitTreeLeaveBlock(FoodItems.GRAPE_FRUIT));
    public static final DeferredBlock<BaseFruitTreeLeaveBlock> LEMON_TREE_LEAVES_BLOCK = registerBlockItem("lemon_tree_leaves", "柠檬树叶", () -> new BaseFruitTreeLeaveBlock(FoodItems.LEMON));
    public static final DeferredBlock<BaseFruitTreeLeaveBlock> PEACH_TREE_LEAVES_BLOCK = registerBlockItem("peach_tree_leaves", "桃树叶", () -> new BaseFruitTreeLeaveBlock(FoodItems.PEACH));
    public static final DeferredBlock<BaseFruitTreeLeaveBlock> CHERRY_TREE_LEAVES_BLOCK = registerBlockItem("cherry_tree_leaves", "樱桃树叶", () -> new BaseFruitTreeLeaveBlock(FoodItems.CHERRY));
    public static final DeferredBlock<BaseFruitTreeLeaveBlock> PLUM_TREE_LEAVES_BLOCK = registerBlockItem("plum_tree_leaves", "李子树叶", () -> new BaseFruitTreeLeaveBlock(FoodItems.PLUM));
    public static final DeferredBlock<BaseFruitTreeLeaveBlock> BLOOD_ORANGE_TREE_LEAVES_BLOCK = registerBlockItem("blood_orange_tree_leaves", "血橙树叶", () -> new BaseFruitTreeLeaveBlock(FoodItems.BLOOD_ORANGE));
    public static final DeferredBlock<BaseFruitTreeLeaveBlock> RAMBUTAN_TREE_LEAVES_BLOCK = registerBlockItem("rambutan_tree_leaves", "红毛丹树叶", () -> new BaseFruitTreeLeaveBlock(FoodItems.RAMBUTAN));
    public static final DeferredBlock<BaseFruitTreeLeaveBlock> MANGO_TREE_LEAVES_BLOCK = registerBlockItem("mango_tree_leaves", "芒果树叶", () -> new BaseFruitTreeLeaveBlock(FoodItems.MANGO));
    public static final DeferredBlock<BaseFruitTreeLeaveBlock> BANANA_TREE_LEAVES_BLOCK = registerBlockItem("banana_tree_leaves", "香蕉树叶", () -> new BaseFruitTreeLeaveBlock(FoodItems.BANANA));
    public static final DeferredBlock<Block> COCONUT_TREE_LEAVES_BLOCK = registerBlockItem("coconut_tree_leaves", "椰子树叶", CoconutLeavesBlock::new);
    public static final DeferredBlock<BaseFruitTreeLeaveBlock> STAR_FRUIT_TREE_LEAVES_BLOCK = registerBlockItem("star_fruit_tree_leaves", "杨桃树叶", () -> new BaseFruitTreeLeaveBlock(FoodItems.STAR_FRUIT));
    public static final DeferredBlock<BaseFruitTreeLeaveBlock> POMEGRANATE_TREE_LEAVES_BLOCK = registerBlockItem("pomegranate_tree_leaves", "石榴树叶", () -> new BaseFruitTreeLeaveBlock(FoodItems.POMEGRANATE));

    //作物
    public static final DeferredBlock<PineappleCropBlock> PINEAPPLE_CROP = registerWithoutItem("pineapple_crop", "菠萝", PineappleCropBlock::new);
    public static final DeferredBlock<SpicyPepperBlock> SPICY_PEPPER_CROP = registerWithoutItem("spicy_pepper_crop", "辣椒", SpicyPepperBlock::new);
    public static final DeferredBlock<DragonFruitCactusBlock> DRAGON_FRUIT_CACTUS = registerWithoutItem("dragon_fruit_cactus", "火龙果仙人掌", DragonFruitCactusBlock::new);
    public static final DeferredBlock<DragonFruitBlock> DRAGON_FRUIT_BLOCK = registerWithoutItem("dragon_fruit", "火龙果", DragonFruitBlock::new);
    public static final DeferredBlock<CoconutBlock> COCONUT_BLOCK = registerWithoutItem("coconut", "椰子", CoconutBlock::new);
    public static final DeferredBlock<CuminCropBlock> CUMIN_CROP = registerWithoutItem("cumin_crop", "孜然", CuminCropBlock::new);
    public static final DeferredBlock<WildCropBlock> WILD_CUMIN_CROP = registerWithoutItem("wild_cumin_crop", "野生孜然", () -> new WildCropBlock(MobEffects.SATURATION, 10, BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS)));

    // 存储块
    public static final DeferredBlock<Block> BLACKCURRANT_CRATE = registerBlockItem("blackcurrant_crate", "箱装黑醋栗", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_BLACK)));
    public static final DeferredBlock<Block> BLOOD_ORANGE_CRATE = registerBlockItem("blood_orange_crate", "箱装血橙", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_RED)));
    public static final DeferredBlock<Block> BLOODY_MOSCATO_CRATE = registerBlockItem("bloody_moscato_crate", "箱装血腥麝香葡萄", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_PURPLE)));
    public static final DeferredBlock<Block> ELDERBERRY_CRATE = registerBlockItem("elderberry_crate", "箱装接骨木果", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_PURPLE)));
    public static final DeferredBlock<Block> APRICOT_CRATE = registerBlockItem("apricot_crate", "箱装杏子", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_ORANGE)));
    public static final DeferredBlock<Block> BANANA_CRATE = registerBlockItem("banana_crate", "箱装香蕉", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_YELLOW)));
    public static final DeferredBlock<Block> CHERRY_CRATE = registerBlockItem("cherry_crate", "箱装樱桃", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_RED)));
    public static final DeferredBlock<Block> COCONUT_CRATE = registerBlockItem("coconut_crate", "箱装椰子", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_ORANGE)));
    public static final DeferredBlock<Block> DRAGON_FRUIT_CRATE = registerBlockItem("dragon_fruit_crate", "箱装火龙果", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_MAGENTA)));
    public static final DeferredBlock<Block> GRAPE_FRUIT_CRATE = registerBlockItem("grape_fruit_crate", "箱装葡萄柚", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_CYAN)));
    public static final DeferredBlock<Block> LEMON_CRATE = registerBlockItem("lemon_crate", "箱装柠檬", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_YELLOW)));
    public static final DeferredBlock<Block> MANGO_CRATE = registerBlockItem("mango_crate", "箱装芒果", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_ORANGE)));
    public static final DeferredBlock<Block> PEACH_CRATE = registerBlockItem("peach_crate", "箱装桃子", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_ORANGE)));
    public static final DeferredBlock<Block> PINEAPPLE_CRATE = registerBlockItem("pineapple_crate", "箱装菠萝", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_YELLOW)));
    public static final DeferredBlock<Block> PLUM_CRATE = registerBlockItem("plum_crate", "箱装李子", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_PURPLE)));
    public static final DeferredBlock<Block> SPICY_PEPPER_CRATE = registerBlockItem("spicy_pepper_crate", "箱装辣椒", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_RED)));
    public static final DeferredBlock<Block> STAR_FRUIT_CRATE = registerBlockItem("star_fruit_crate", "箱装杨桃", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_YELLOW)));
    public static final DeferredBlock<Block> POMEGRANATE_CRATE = registerBlockItem("pomegranate_crate", "箱装石榴", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_RED)));
    public static final DeferredBlock<Block> RAMBUTAN_CRATE = registerBlockItem("rambutan_crate", "箱装红毛丹", () -> new Block(BlockBehaviour.Properties.ofFullCopy(ACACIA_PLANKS).mapColor(MapColor.COLOR_RED)));

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
BLOCK_ITEMS.register(en, () -> new BlockItem(block.get(), new Item.Properties()));
chineseProviders.add(l -> l.addBlock(block, zh));
return block;
    }

    private static <B extends Block> DeferredBlock<B> registerBlockItem(final String en, final String zh, Supplier<B> blockSupplier, Function<Block, BlockItem> function) {
DeferredBlock<B> deferredBlock = BLOCKS.register(en, blockSupplier);
BLOCK_ITEMS.register(en, () -> function.apply(deferredBlock.get()));
chineseProviders.add(l -> l.addBlock(deferredBlock, zh));
return deferredBlock;
    }
}
