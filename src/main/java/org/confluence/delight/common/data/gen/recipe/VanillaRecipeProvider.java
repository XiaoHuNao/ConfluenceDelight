package org.confluence.delight.common.data.gen.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.ICustomIngredient;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDFoodItems;
import org.confluence.delight.common.init.CDNaturalItems;
import org.confluence.delight.common.init.CDTags;
import org.confluence.lib.common.data.gen.AbstractRecipeProvider;
import org.confluence.mod.common.init.item.ConsumableItems;
import org.confluence.mod.common.init.item.FoodItems;
import org.confluence.mod.common.init.item.MaterialItems;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class VanillaRecipeProvider extends AbstractRecipeProvider {
    public VanillaRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup);
    }

    @Override
    protected void buildRecipes(RecipeOutput output, HolderLookup.Provider holderLookup) {
        shaped(output, "", "", ShapedRecipePattern.of(Map.of(
            'S', Ingredient.of(ModItems.MIXED_SALAD.get()),
            'C', Ingredient.of(CDFoodItems.CLOUD_BREAD_SLICE)
        ), List.of(
            "C  ",
            "S  ",
            "C  "
        )), CDFoodItems.CLOUD_VEGETABLES_SANDWICH.toStack());
        shaped(output, "", "", ShapedRecipePattern.of(Map.of(
            'C', Ingredient.of(CDFoodItems.CLOUD_BREAD_SLICE),
            'G', Ingredient.of(MaterialItems.GEL),
            'L', Ingredient.of(MaterialItems.LIFE_MUSHROOM)
        ), List.of(
            "CC ",
            "GL ",
            "CC "
        )), CDFoodItems.CLOUD_GEL_SANDWICH.toStack());
        shaped(output, "", "", ShapedRecipePattern.of(Map.of(
            'C', Ingredient.of(CDFoodItems.CLOUD_BREAD_SLICE),
            'G', Ingredient.of(MaterialItems.GEL),
            'E', Ingredient.of(ModItems.FRIED_EGG.get())
        ), List.of(
            "CC ",
            "GE ",
            "CC "
        )), CDFoodItems.CLOUD_FRIED_EGG_GEL_SANDWICH.toStack());
        shaped(output, "", "", ShapedRecipePattern.of(Map.of(
            'C', Ingredient.of(CDFoodItems.LUCK_CHOCOLATE_COPPER_COIN),
            'S', Ingredient.of(CDFoodItems.LUCK_CHOCOLATE_SILVER_COIN),
            'G', Ingredient.of(CDFoodItems.LUCK_CHOCOLATE_GOLDEN_COIN),
            'P', Ingredient.of(CDFoodItems.LUCK_CHOCOLATE_PLATINUM_COIN)
        ), List.of(
            "CSS",
            "SGP",
            "PPP"
        )), CDFoodItems.CHOCOLATE_LUCK_COIN_BOX.toStack());
        shaped(output, "", "", ShapedRecipePattern.of(Map.of(
            'f', Ingredient.of(Items.FLOWER_POT),
            'd', Ingredient.of(Items.DECORATED_POT)
        ), List.of(
            "f  ",
            "d  ",
            "   "
        )), CDBlocks.PICKLE_JARS_BLOCK.toStack());
        shaped(output, "", "", ShapedRecipePattern.of(Map.of(
            'F', Ingredient.of(ItemTags.FENCES),
            'S', Ingredient.of(Items.SMOOTH_STONE),
            'T', Ingredient.of(Items.STONE)
        ), List.of(
            "  F",
            "SSS",
            "TTT"
        )), CDBlocks.MILLSTONE_BLOCK.toStack());

        shapeless(output, "", "", CDFoodItems.CLOUD_BACON_SANDWICH.toStack(),
            Ingredient.of(CDFoodItems.BUTTER_FRIED_CLOUD_BREAD_SLICES),
            Ingredient.of(ModItems.COOKED_BACON.get()),
            Ingredient.of(ModItems.TOMATO.get()),
            Ingredient.of(Tags.Items.FOODS_FRUIT));
        shapeless(output, "", "", CDFoodItems.HONEY_GLAZED_HAM.toStack(),
            Ingredient.of(Items.HONEY_BOTTLE),
            Ingredient.of(Items.SUGAR),
            Ingredient.of(MaterialItems.GEL),
            Ingredient.of(ModItems.HAM.get()));
        shapeless(output, "", "", CDFoodItems.WHITE_CHOCOLATE.toStack(),
            Ingredient.of(Items.SUGAR),
            Ingredient.of(Items.MILK_BUCKET),
            Ingredient.of(Items.COCOA_BEANS));
        shapeless(output, "", "", CDFoodItems.BLACK_CHOCOLATE.toStack(),
            Ingredient.of(Items.SUGAR),
            Ingredient.of(Items.COCOA_BEANS));
        shapeless(output, "", "", CDFoodItems.FLAVORED_WHITE_CHOCOLATE.toStack(),
            Ingredient.of(MaterialItems.GEL),
            Ingredient.of(CDFoodItems.WHITE_CHOCOLATE));
        shapeless(output, "", "", CDFoodItems.BLACKCURRANT_DARK_CHOCOLATE_PIE.toStack(),
            Ingredient.of(MaterialItems.GEL),
            Ingredient.of(FoodItems.BLACKCURRANT),
            Ingredient.of(CDFoodItems.BLACK_CHOCOLATE));
        shapeless(output, "", "", CDFoodItems.BLACKCURRANT_JAM_BREAD.toStack(),
            Ingredient.of(CDFoodItems.BLACKCURRANT_JAM),
            Ingredient.of(Items.BREAD));
        shapeless(output, "", "", CDFoodItems.BLACKCURRANT_JAM_MANGO_PUDDING.toStack(),
            Ingredient.of(Items.SUGAR),
            Ingredient.of(Items.MILK_BUCKET),
            Ingredient.of(CDTags.Items.C_FRUIT_MANGO),
            Ingredient.of(CDFoodItems.BLACKCURRANT_JAM),
            Ingredient.of(MaterialItems.GEL));
        shapeless(output, "", "", CDFoodItems.LUCK_CHOCOLATE_COPPER_COIN.toStack(),
            Ingredient.of(Items.SUGAR),
            Ingredient.of(Items.MILK_BUCKET),
            Ingredient.of(Items.COCOA_BEANS),
            Ingredient.of(org.confluence.mod.common.init.item.ModItems.COPPER_COIN));
        shapeless(output, "", "", CDFoodItems.LUCK_CHOCOLATE_SILVER_COIN.toStack(),
            Ingredient.of(Items.SUGAR),
            Ingredient.of(Items.MILK_BUCKET),
            Ingredient.of(Items.COCOA_BEANS),
            Ingredient.of(org.confluence.mod.common.init.item.ModItems.SILVER_COIN));
        shapeless(output, "", "", CDFoodItems.LUCK_CHOCOLATE_GOLDEN_COIN.toStack(),
            Ingredient.of(Items.SUGAR),
            Ingredient.of(Items.MILK_BUCKET),
            Ingredient.of(Items.COCOA_BEANS),
            Ingredient.of(org.confluence.mod.common.init.item.ModItems.GOLD_COIN));
        shapeless(output, "", "", CDFoodItems.LUCK_CHOCOLATE_PLATINUM_COIN.toStack(),
            Ingredient.of(Items.SUGAR),
            Ingredient.of(Items.MILK_BUCKET),
            Ingredient.of(Items.COCOA_BEANS),
            Ingredient.of(org.confluence.mod.common.init.item.ModItems.PLATINUM_COIN));
        shapeless(output, "", "", CDFoodItems.LUCK_CHOCOLATE_EMERALD_COIN.toStack(),
            Ingredient.of(Items.SUGAR),
            Ingredient.of(Items.MILK_BUCKET),
            Ingredient.of(Items.COCOA_BEANS),
            Ingredient.of(org.confluence.mod.common.init.item.ModItems.EMERALD_COIN));
        shapeless(output, "", "", CDNaturalItems.SPICY_PEPPER_SEEDS.toStack(2), Ingredient.of(FoodItems.SPICY_PEPPER));
        shapeless(output, "", "", CDNaturalItems.PINEAPPLE_SEEDS.toStack(2), Ingredient.of(CDTags.Items.C_FRUIT_PINEAPPLE));
        shapeless(output, "", "", CDNaturalItems.DRAGON_FRUIT_SEEDS.toStack(2), Ingredient.of(FoodItems.DRAGON_FRUIT));
        shapeless(output, "", "", CDFoodItems.BIG_CHICKEN_CUTLET.toStack(),
            Ingredient.of(Items.CHICKEN),
            Ingredient.of(FoodItems.SPICY_PEPPER),
            Ingredient.of(Items.LAVA_BUCKET));
        shapeless(output, "", "", CDFoodItems.FLYING_FISH_SASHIMI.toStack(),
            Ingredient.of(MaterialItems.FILAMENTOUS_FIN),
            Ingredient.of(CDFoodItems.FLYING_FISH_CAVIAR),
            Ingredient.of(ModItems.ONION.get()));
        shapeless(output, "", "", CDFoodItems.FLYING_FISH_CAVIAR.toStack(),
            Ingredient.of(Items.GLASS_BOTTLE),
            Ingredient.of(CDFoodItems.FLYING_FISH_ROE.toStack(64)));
        shapeless(output, "", "", CDFoodItems.SPEEDY_COKE.toStack(),
            Ingredient.of(Items.SUGAR),
            Ingredient.of(ConsumableItems.VITAL_CRYSTAL),
            Ingredient.of(Items.GLOWSTONE_DUST),
            Ingredient.of(FoodItems.JOJA_COLA));
        //熔炉
        cooking(output, SmeltingRecipe::new, "smelting/", "", Ingredient.of(ModItems.MILK_BOTTLE.get()), CDFoodItems.BUTTER.toStack(), 0.2f, 50);
        cooking(output, SmeltingRecipe::new, "smelting/", "", Ingredient.of(CDFoodItems.RAW_ROSEWOOD_MEAT.get()), CDFoodItems.COOKED_ROSEWOOD_MEAT.toStack(), 0.35f, 200);
        cooking(output, SmeltingRecipe::new, "smelting/", "", Ingredient.of(CDFoodItems.RAW_PROLIFERATING_FLESH_AND_BLOOD.get()), CDFoodItems.COOKED_PROLIFERATING_FLESH_AND_BLOOD.toStack(), 0.35f, 200);
        // 烟熏炉
        cooking(output, SmokingRecipe::new, "smoking/", "", Ingredient.of(CDFoodItems.RAW_PROLIFERATING_FLESH_AND_BLOOD.get()), CDFoodItems.COOKED_PROLIFERATING_FLESH_AND_BLOOD.toStack(), 0.35f, 200);
        // 篝火
        cooking(output, CampfireCookingRecipe::new, "campfire_cooking/", "", Ingredient.of(CDFoodItems.RAW_ROSEWOOD_MEAT.get()), CDFoodItems.COOKED_ROSEWOOD_MEAT.toStack(), 0.35f, 200);
        cooking(output, CampfireCookingRecipe::new, "campfire_cooking/", "", Ingredient.of(CDFoodItems.RAW_PROLIFERATING_FLESH_AND_BLOOD.get()), CDFoodItems.COOKED_PROLIFERATING_FLESH_AND_BLOOD.toStack(), 0.35f, 200);

        compressAndDecompressNine(output, FoodItems.BLACKCURRANT, CDTags.Items.C_FRUIT_BLACKCURRANT, CDBlocks.BLACKCURRANT_CRATE, CDTags.Items.BLACKCURRANT_CRATE);
        compressAndDecompressNine(output, FoodItems.BLOOD_ORANGE, CDTags.Items.C_FRUIT_BLOOD_ORANGE, CDBlocks.BLOOD_ORANGE_CRATE, CDTags.Items.BLOOD_ORANGE_CRATE);
        compressAndDecompressNine(output, FoodItems.ELDERBERRY, CDTags.Items.C_FRUIT_ELDERBERRY, CDBlocks.ELDERBERRY_CRATE, CDTags.Items.ELDERBERRY_CRATE);
        compressAndDecompressNine(output, FoodItems.APRICOT, CDTags.Items.C_FRUIT_APRICOT, CDBlocks.APRICOT_CRATE, CDTags.Items.APRICOT_CRATE);
        compressAndDecompressNine(output, FoodItems.BANANA, CDTags.Items.C_FRUIT_BANANA, CDBlocks.BANANA_CRATE, CDTags.Items.BANANA_CRATE);
        compressAndDecompressNine(output, FoodItems.CHERRY, CDTags.Items.C_FRUIT_CHERRY, CDBlocks.CHERRY_CRATE, CDTags.Items.CHERRY_CRATE);
        compressAndDecompressNine(output, FoodItems.COCONUT, CDTags.Items.C_FRUIT_COCONUT, CDBlocks.COCONUT_CRATE, CDTags.Items.COCONUT_CRATE);
        compressAndDecompressNine(output, FoodItems.DRAGON_FRUIT, CDTags.Items.C_FRUIT_DRAGON_FRUIT, CDBlocks.DRAGON_FRUIT_CRATE, CDTags.Items.DRAGON_FRUIT_CRATE);
        compressAndDecompressNine(output, FoodItems.GRAPE_FRUIT, CDTags.Items.C_FRUIT_GRAPE_FRUIT, CDBlocks.GRAPE_FRUIT_CRATE, CDTags.Items.GRAPE_FRUIT_CRATE);
        compressAndDecompressNine(output, FoodItems.LEMON, CDTags.Items.C_FRUIT_LEMON, CDBlocks.LEMON_CRATE, CDTags.Items.LEMON_CRATE);
        compressAndDecompressNine(output, FoodItems.MANGO, CDTags.Items.C_FRUIT_MANGO, CDBlocks.MANGO_CRATE, CDTags.Items.MANGO_CRATE);
        compressAndDecompressNine(output, FoodItems.PEACH, CDTags.Items.C_FRUIT_PEACH, CDBlocks.PEACH_CRATE, CDTags.Items.PEACH_CRATE);
        compressAndDecompressNine(output, FoodItems.PINEAPPLE, CDTags.Items.C_FRUIT_PINEAPPLE, CDBlocks.PINEAPPLE_CRATE, CDTags.Items.PINEAPPLE_CRATE);
        compressAndDecompressNine(output, FoodItems.PLUM, CDTags.Items.C_FRUIT_PLUM, CDBlocks.PLUM_CRATE, CDTags.Items.PLUM_CRATE);
        compressAndDecompressNine(output, FoodItems.SPICY_PEPPER, CDTags.Items.C_VEGETABLES_SPICY_PEPPER, CDBlocks.SPICY_PEPPER_CRATE, CDTags.Items.SPICY_PEPPER_CRATE);
        compressAndDecompressNine(output, FoodItems.STAR_FRUIT, CDTags.Items.C_FRUIT_STAR_FRUIT, CDBlocks.STAR_FRUIT_CRATE, CDTags.Items.STAR_FRUIT_CRATE);
        compressAndDecompressNine(output, FoodItems.POMEGRANATE, CDTags.Items.C_FRUIT_POMEGRANATE, CDBlocks.POMEGRANATE_CRATE, CDTags.Items.POMEGRANATE_CRATE);
        compressAndDecompressNine(output, FoodItems.RAMBUTAN, CDTags.Items.C_FRUIT_RAMBUTAN, CDBlocks.RAMBUTAN_CRATE, CDTags.Items.RAMBUTAN_CRATE);
    }

    protected void shaped(RecipeOutput output, String prefix, String suffix, ShapedRecipePattern pattern, ItemStack result) {
        ResourceLocation id = ConfluenceDelight.asResource("shaped/" + prefix + getItemName(result.getItem()) + suffix);
        output.accept(id, new ShapedRecipe("", CraftingBookCategory.MISC, pattern, result, true), null);
    }

    protected void shapeless(RecipeOutput output, String prefix, String suffix, ItemStack result, Ingredient... ingredients) {
        ResourceLocation id = ConfluenceDelight.asResource("shapeless/" + prefix + getItemName(result.getItem()) + suffix);
        NonNullList<Ingredient> zingredients = NonNullList.of(Ingredient.EMPTY, ingredients);
        output.accept(id, new ShapelessRecipe("", CraftingBookCategory.MISC, result, zingredients), null);
    }


    protected <T extends AbstractCookingRecipe> void cooking(RecipeOutput recipeOutput, AbstractCookingRecipe.Factory<T> factory, String prefix, String suffix, Ingredient ingredient, ItemStack result, float experience, int cookingTime) {
        ResourceLocation id = ConfluenceDelight.asResource(prefix + getItemName(result.getItem()) + suffix);
        NonNullList<Ingredient> ingredients = NonNullList.withSize(1, ingredient);
        recipeOutput.accept(id, factory.create("", CookingBookCategory.MISC, ingredient, result, experience, cookingTime), createAdvancementHolder(recipeOutput, id, ingredients));
    }

    protected void compressAndDecompressNine(RecipeOutput recipeOutput, ItemLike decompressed, TagKey<Item> decompressedTag, ItemLike compressed, TagKey<Item> compressedTag) {
        ResourceLocation id1 = ConfluenceDelight.asResource("shaped/" + getItemName(decompressed));
        NonNullList<Ingredient> ingredients = NonNullList.of(Ingredient.EMPTY, Ingredient.of(compressedTag));
        recipeOutput.accept(id1, new ShapelessRecipe("", CraftingBookCategory.BUILDING, new ItemStack(decompressed, 9), ingredients), createAdvancementHolder(recipeOutput, id1, ingredients));
        ResourceLocation id2 = ConfluenceDelight.asResource("shaped/" + getItemName(compressed));
        ShapedRecipePattern pattern = ShapedRecipePattern.of(Map.of('A', Ingredient.of(decompressedTag)), List.of("AAA", "AAA", "AAA"));
        recipeOutput.accept(id2, new ShapedRecipe("", CraftingBookCategory.BUILDING, pattern, compressed.asItem().getDefaultInstance()), createAdvancementHolder(recipeOutput, id2, pattern.ingredients()));
    }

    public static AdvancementHolder createAdvancementHolder(RecipeOutput recipeOutput, ResourceLocation id, NonNullList<Ingredient> ingredients) {
        Set<Item> itemCounter = new HashSet<>();
        Set<TagKey<Item>> tagCounter = new HashSet<>();
        Advancement.Builder builder = recipeOutput.advancement()
            .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
            .rewards(AdvancementRewards.Builder.recipe(id))
            .requirements(AdvancementRequirements.Strategy.OR);
        for (Ingredient ingredient : ingredients) {
            Ingredient.Value[] values;
            ICustomIngredient customIngredient = ingredient.getCustomIngredient();
            if (customIngredient == null) {
                values = ingredient.getValues();
            } else {
                values = customIngredient.getItems().map(Ingredient.ItemValue::new).toArray(Ingredient.Value[]::new);
            }
            for (Ingredient.Value value : values) {
                if (value instanceof Ingredient.ItemValue(ItemStack itemStack)) {
                    Item item = itemStack.getItem();
                    if (itemCounter.contains(item)) continue;
                    itemCounter.add(item);
                    builder.addCriterion(getHasName(item), has(item));
                } else if (value instanceof Ingredient.TagValue(TagKey<Item> tag)) {
                    if (tagCounter.contains(tag)) continue;
                    tagCounter.add(tag);
                    builder.addCriterion("has_tag_" + tag.location().getPath(), has(tag));
                }
            }
        }
        return builder.build(id.withPrefix("recipes/confluence_delight/"));
    }
}
