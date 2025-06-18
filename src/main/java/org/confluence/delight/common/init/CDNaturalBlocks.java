package org.confluence.delight.common.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.block.natural.*;
import org.confluence.mod.common.init.item.FoodItems;

import java.util.function.Supplier;

import static org.confluence.delight.ConfluenceDelight.chineseProviders;

public class CDNaturalBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ConfluenceDelight.MODID);

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
    public static final DeferredBlock<BaseFruitTreeLeaveBlock> COCONUT_TREE_LEAVES_BLOCK = registerBlockItem("coconut_tree_leaves", "椰子树叶", () -> new BaseFruitTreeLeaveBlock(FoodItems.COCONUT));
    public static final DeferredBlock<BaseFruitTreeLeaveBlock> STAR_FRUIT_TREE_LEAVES_BLOCK = registerBlockItem("star_fruit_tree_leaves", "杨桃树叶", () -> new BaseFruitTreeLeaveBlock(FoodItems.STAR_FRUIT));
    public static final DeferredBlock<BaseFruitTreeLeaveBlock> POMEGRANATE_TREE_LEAVES_BLOCK = registerBlockItem("pomegranate_tree_leaves", "石榴树叶", () -> new BaseFruitTreeLeaveBlock(FoodItems.POMEGRANATE));

    //灌木
    public static final DeferredBlock<BaseFruitShrubBlock> BLACKCURRANT_SHRUB_BLOCK = registerBlockItem("blackcurrant_shrub", "黑醋栗灌木", () -> new BaseFruitShrubBlock(FoodItems.BLACKCURRANT));
    public static final DeferredBlock<BaseFruitShrubBlock> ELDERBERRY_SHRUB_BLOCK = registerBlockItem("elderberry_shrub", "接骨木灌木", () -> new BaseFruitShrubBlock(FoodItems.ELDERBERRY));

    //作物
    public static final DeferredBlock<PineappleCropBlock> PINEAPPLE_CROP = registerWithoutItem("pineapple_crop", PineappleCropBlock::new);
    public static final DeferredBlock<SpicyPepperBlock> SPICY_PEPPER_CROP = registerWithoutItem("spicy_pepper_crop", SpicyPepperBlock::new);
    public static final DeferredBlock<DragonFruitCactusBlock> DRAGON_FRUIT_CACTUS = registerWithoutItem("dragon_fruit_cactus", "火龙果仙人掌", DragonFruitCactusBlock::new);
    public static final DeferredBlock<DragonFruitBlock> DRAGON_FRUIT_BLOCK = registerWithoutItem("dragon_fruit", "火龙果", DragonFruitBlock::new);

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
}
