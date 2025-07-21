package org.confluence.delight.common.data.gen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.confluence.delight.common.init.CDFoodItems;
import org.confluence.delight.common.init.CDNaturalBlocks;
import org.confluence.delight.common.init.CDNaturalItems;
import org.confluence.delight.common.init.CDTags;
import org.confluence.mod.common.init.ModTags;
import org.confluence.mod.common.init.block.OreBlocks;
import org.confluence.mod.common.init.item.FoodItems;
import org.confluence.mod.common.init.item.PotionItems;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static org.confluence.delight.ConfluenceDelight.MODID;


public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> b, @Nullable ExistingFileHelper helper) {
        super(output, provider, b, MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        IntrinsicTagAppender<Item> foods = tag(Tags.Items.FOODS);
        for (DeferredHolder<Item, ? extends Item> food : CDFoodItems.ITEMS.getEntries()) {
            foods.add(food.get());
        }
        addFruitToTag(CDTags.Items.C_FRUIT_MANGO, FoodItems.MANGO.get());
        addFruitToTag(CDTags.Items.C_FRUIT_PINEAPPLE, FoodItems.PINEAPPLE.get());
        addFruitToTag(CDTags.Items.C_FRUIT_LEMON, FoodItems.LEMON.get());
        tag(CDTags.Items.JUICER_CONTAINER).add(
                Items.GLASS_BOTTLE,
                PotionItems.BOTTLE.get(),
                PotionItems.MUG.get()
        );
        tag(ItemTags.PARROT_FOOD).add(
                CDNaturalItems.PINEAPPLE_SEEDS.get(),
                CDNaturalItems.DRAGON_FRUIT_SEEDS.get(),
                CDNaturalItems.SPICY_PEPPER_SEEDS.get()
        );
        tag(ItemTags.CHICKEN_FOOD).add(
                CDNaturalItems.PINEAPPLE_SEEDS.get(),
                CDNaturalItems.DRAGON_FRUIT_SEEDS.get(),
                CDNaturalItems.SPICY_PEPPER_SEEDS.get()
        );
        tag(ItemTags.SAPLINGS).add(
                CDNaturalBlocks.APPLE_SAPLING.asItem(),
                CDNaturalBlocks.APRICOT_SAPLING.asItem(),
                CDNaturalBlocks.GRAPEFRUIT_SAPLING.asItem(),
                CDNaturalBlocks.LEMON_SAPLING.asItem(),
                CDNaturalBlocks.PEACH_SAPLING.asItem(),
                CDNaturalBlocks.CHERRY_SAPLING.asItem(),
                CDNaturalBlocks.PLUM_SAPLING.asItem(),
                CDNaturalBlocks.BLOOD_ORANGE_SAPLING.asItem(),
                CDNaturalBlocks.RAMBUTAN_SAPLING.asItem(),
                CDNaturalBlocks.MANGO_SAPLING.asItem(),
                CDNaturalBlocks.BANANA_SAPLING.asItem(),
                CDNaturalBlocks.COCONUT_SAPLING.asItem(),
                CDNaturalBlocks.STAR_FRUIT_SAPLING.asItem(),
                CDNaturalBlocks.POMEGRANATE_SAPLING.asItem()
        );
        tag(ItemTags.LEAVES).add(
                CDNaturalBlocks.APPLE_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.APRICOT_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.GRAPEFRUIT_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.LEMON_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.PEACH_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.CHERRY_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.PLUM_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.BLOOD_ORANGE_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.RAMBUTAN_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.MANGO_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.BANANA_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.COCONUT_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.STAR_FRUIT_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.POMEGRANATE_TREE_LEAVES_BLOCK.asItem()
        );
        tag(ItemTags.COMPLETES_FIND_TREE_TUTORIAL).add(
                CDNaturalBlocks.APPLE_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.APRICOT_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.GRAPEFRUIT_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.LEMON_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.PEACH_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.CHERRY_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.PLUM_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.BLOOD_ORANGE_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.RAMBUTAN_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.MANGO_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.BANANA_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.COCONUT_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.STAR_FRUIT_TREE_LEAVES_BLOCK.asItem(),
                CDNaturalBlocks.POMEGRANATE_TREE_LEAVES_BLOCK.asItem()
        );
        tag(CDTags.Items.C_FRUIT_BLACKCURRANT).add(FoodItems.BLACKCURRANT.get());
        tag(CDTags.Items.C_FRUIT_BLOOD_ORANGE).add(FoodItems.BLOOD_ORANGE.get());
        tag(CDTags.Items.C_FRUIT_BLOODY_MOSCATO).add(FoodItems.BLOODY_MOSCATO.get());
        tag(CDTags.Items.C_FRUIT_ELDERBERRY).add(FoodItems.ELDERBERRY.get());
        tag(CDTags.Items.C_FRUIT_APRICOT).add(FoodItems.APRICOT.get());
        tag(CDTags.Items.C_FRUIT_BANANA).add(FoodItems.BANANA.get());
        tag(CDTags.Items.C_FRUIT_CHERRY).add(FoodItems.CHERRY.get());
        tag(CDTags.Items.C_FRUIT_COCONUT).add(FoodItems.COCONUT.get());
        tag(CDTags.Items.C_FRUIT_DRAGON_FRUIT).add(FoodItems.DRAGON_FRUIT.get());
        tag(CDTags.Items.C_FRUIT_GRAPE_FRUIT).add(FoodItems.GRAPE_FRUIT.get());
        tag(CDTags.Items.C_FRUIT_LEMON).add(FoodItems.LEMON.get());
        tag(CDTags.Items.C_FRUIT_MANGO).add(FoodItems.MANGO.get());
        tag(CDTags.Items.C_FRUIT_PEACH).add(FoodItems.PEACH.get());
        tag(CDTags.Items.C_FRUIT_PINEAPPLE).add(FoodItems.PINEAPPLE.get());
        tag(CDTags.Items.C_FRUIT_PLUM).add(FoodItems.PLUM.get());
        tag(CDTags.Items.C_FRUIT_SPICY_PEPPER).add(FoodItems.SPICY_PEPPER.get());
        tag(CDTags.Items.C_FRUIT_STAR_FRUIT).add(FoodItems.STAR_FRUIT.get());
        tag(CDTags.Items.C_FRUIT_POMEGRANATE).add(FoodItems.POMEGRANATE.get());
        tag(CDTags.Items.C_FRUIT_RAMBUTAN).add(FoodItems.RAMBUTAN.get());

        tag(CDTags.Items.BLACKCURRANT_CRATE).add(CDNaturalBlocks.BLACKCURRANT_CRATE.asItem());
        tag(CDTags.Items.BLOOD_ORANGE_CRATE).add(CDNaturalBlocks.BLOOD_ORANGE_CRATE.asItem());
        tag(CDTags.Items.BLOODY_MOSCATO_CRATE).add(CDNaturalBlocks.BLOODY_MOSCATO_CRATE.asItem());
        tag(CDTags.Items.ELDERBERRY_CRATE).add(CDNaturalBlocks.ELDERBERRY_CRATE.asItem());
        tag(CDTags.Items.APRICOT_CRATE).add(CDNaturalBlocks.APRICOT_CRATE.asItem());
        tag(CDTags.Items.BANANA_CRATE).add(CDNaturalBlocks.BANANA_CRATE.asItem());
        tag(CDTags.Items.CHERRY_CRATE).add(CDNaturalBlocks.CHERRY_CRATE.asItem());
        tag(CDTags.Items.COCONUT_CRATE).add(CDNaturalBlocks.COCONUT_CRATE.asItem());
        tag(CDTags.Items.DRAGON_FRUIT_CRATE).add(CDNaturalBlocks.DRAGON_FRUIT_CRATE.asItem());
        tag(CDTags.Items.GRAPE_FRUIT_CRATE).add(CDNaturalBlocks.GRAPE_FRUIT_CRATE.asItem());
        tag(CDTags.Items.LEMON_CRATE).add(CDNaturalBlocks.LEMON_CRATE.asItem());
        tag(CDTags.Items.MANGO_CRATE).add(CDNaturalBlocks.MANGO_CRATE.asItem());
        tag(CDTags.Items.PEACH_CRATE).add(CDNaturalBlocks.PEACH_CRATE.asItem());
        tag(CDTags.Items.PINEAPPLE_CRATE).add(CDNaturalBlocks.PINEAPPLE_CRATE.asItem());
        tag(CDTags.Items.PLUM_CRATE).add(CDNaturalBlocks.PLUM_CRATE.asItem());
        tag(CDTags.Items.SPICY_PEPPER_CRATE).add(CDNaturalBlocks.SPICY_PEPPER_CRATE.asItem());
        tag(CDTags.Items.STAR_FRUIT_CRATE).add(CDNaturalBlocks.STAR_FRUIT_CRATE.asItem());
        tag(CDTags.Items.POMEGRANATE_CRATE).add(CDNaturalBlocks.POMEGRANATE_CRATE.asItem());
        tag(CDTags.Items.RAMBUTAN_CRATE).add(CDNaturalBlocks.RAMBUTAN_CRATE.asItem());
    }

    @Override
    public IntrinsicTagAppender<Item> tag(TagKey<Item> tag) {
        return super.tag(tag);
    }

    private void addFruitToTag(TagKey<Item> tagKey, Item fruit) {
        tag(tagKey).add(fruit);
    }
}
