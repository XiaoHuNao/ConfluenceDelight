package org.confluence.delight.common.data.gen.tag;

import dev.xkmc.fruitsdelight.init.food.FruitType;
import dev.xkmc.fruitsdelight.init.plants.FDPineapple;
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
import org.confluence.delight.integration.ModLoadUtil;
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
    }

    @Override
    public IntrinsicTagAppender<Item> tag(TagKey<Item> tag) {
        return super.tag(tag);
    }

    private void addFruitToTag(TagKey<Item> tagKey, Item fruit) {
        tag(tagKey).add(fruit);
    }
}
