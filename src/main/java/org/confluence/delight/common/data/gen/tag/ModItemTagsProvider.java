package org.confluence.delight.common.data.gen.tag;

import dev.xkmc.fruitsdelight.init.food.FruitType;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.confluence.delight.common.init.CDFoodItems;
import org.confluence.delight.common.init.CDTags;
import org.confluence.delight.util.ModLoadUtil;
import org.confluence.mod.common.init.item.FoodItems;
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
        addFruitToTag(CDTags.Items.FRUIT_MANGO, FoodItems.MANGO.get());
        addFruitToTag(CDTags.Items.FRUIT_PINEAPPLE, FoodItems.PINEAPPLE.get());
        addFruitToTag(CDTags.Items.FRUIT_LEMON, FoodItems.LEMON.get());
        if (ModLoadUtil.isFruitDelightLoaded()) {
            addFruitToTag(CDTags.Items.FRUIT_MANGO, FruitType.MANGO.getFruit());
            addFruitToTag(CDTags.Items.FRUIT_PINEAPPLE, FruitType.PINEAPPLE.getFruit());
            addFruitToTag(CDTags.Items.FRUIT_LEMON, FruitType.LEMON.getFruit());
        }
    }

    @Override
    public IntrinsicTagAppender<Item> tag(TagKey<Item> tag) {
        return super.tag(tag);
    }

    private void addFruitToTag(TagKey<Item> tagKey, Item fruit) {
        tag(tagKey).add(fruit);
    }
}
