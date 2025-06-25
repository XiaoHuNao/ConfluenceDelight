package org.confluence.delight.common.data.gen.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.common.Tags;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.*;
import org.confluence.lib.common.data.gen.AbstractRecipeProvider;
import org.confluence.mod.common.init.item.FoodItems;
import org.confluence.mod.common.init.item.MaterialItems;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class VanillaCraftRecipe extends AbstractRecipeProvider {
    public VanillaCraftRecipe(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
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
        shapeless(output, "", "", CDFoodItems.MANGO_PUDDING.toStack(),
                Ingredient.of(Items.MILK_BUCKET),
                Ingredient.of(Items.SUGAR),
                Ingredient.of(MaterialItems.GEL),
                Ingredient.of(CDTags.Items.FRUIT_MANGO));
        shapeless(output, "", "", CDFoodItems.BANANA_PUDDING.toStack(),
                Ingredient.of(Items.MILK_BUCKET),
                Ingredient.of(Items.SUGAR),
                Ingredient.of(MaterialItems.GEL),
                Ingredient.of(FoodItems.BANANA));
        shapeless(output, "", "", CDFoodItems.BLACKCURRANT_JAM_BREAD.toStack(),
                Ingredient.of(CDFoodItems.BLACKCURRANT_JAM),
                Ingredient.of(Items.BREAD));
        shapeless(output, "", "", CDFoodItems.BLACKCURRANT_JAM_MANGO_PUDDING.toStack(),
                Ingredient.of(Items.SUGAR),
                Ingredient.of(Items.MILK_BUCKET),
                Ingredient.of(CDTags.Items.FRUIT_MANGO),
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
                Ingredient.of(org.confluence.mod.common.init.item.ModItems.GOLDEN_COIN));
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
        shapeless(output, "", "", CDNaturalItems.PINEAPPLE_SEEDS.toStack(2), Ingredient.of(CDTags.Items.FRUIT_PINEAPPLE));
        shapeless(output, "", "", CDNaturalItems.DRAGON_FRUIT_SEEDS.toStack(2), Ingredient.of(FoodItems.DRAGON_FRUIT));
        shapeless(output, "", "", CDFoodItems.BIG_CHICKEN_CUTLET.toStack(),
                Ingredient.of(Items.CHICKEN),
                Ingredient.of(FoodItems.SPICY_PEPPER),
                Ingredient.of(Items.LAVA_BUCKET),
                Ingredient.of(CDMaterialItems.SALT));

        //熔炉
        furnace(output, "", "", Ingredient.of(ModItems.MILK_BOTTLE.get()), CDFoodItems.BUTTER.toStack(), 0.2f, 50);
        furnace(output, "", "", Ingredient.of(CDFoodItems.RAW_DONKEY_MEAT.get()), CDFoodItems.COOKED_DONKEY_MEAT.toStack(), 0.2f, 100);
        furnace(output, "", "", Ingredient.of(CDFoodItems.RAW_HORSE_MEAT.get()), CDFoodItems.COOKED_HORSE_MEAT.toStack(), 0.2f, 100);
        furnace(output, "", "", Ingredient.of(CDFoodItems.CRISPY_RICE_WITH_POTATOES.get()), CDFoodItems.POTATO_PIECE.toStack(), 0.2f, 120);
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

    protected void furnace(RecipeOutput output, String prefix, String suffix, Ingredient ingredient, ItemStack result, float experience, int cookingTime) {
        ResourceLocation id = ConfluenceDelight.asResource("furnace/" + prefix + getItemName(result.getItem()) + suffix);
        output.accept(id, new SmeltingRecipe("", CookingBookCategory.FOOD, ingredient, result, experience, cookingTime), null);
    }
}
