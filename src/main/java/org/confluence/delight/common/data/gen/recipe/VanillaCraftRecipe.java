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
import org.confluence.delight.common.init.ModFoodItems;
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
                'C', Ingredient.of(ModFoodItems.CLOUD_BREAD_SLICE)
        ), List.of(
                "C  ",
                "S  ",
                "C  "
        )), ModFoodItems.CLOUD_VEGETABLES_SANDWICH.toStack());
        shaped(output, "", "", ShapedRecipePattern.of(Map.of(
                'C', Ingredient.of(ModFoodItems.CLOUD_BREAD_SLICE),
                'G', Ingredient.of(MaterialItems.GEL),
                'L', Ingredient.of(MaterialItems.LIFE_MUSHROOM)
        ), List.of(
                "CC ",
                "GL ",
                "CC "
        )), ModFoodItems.CLOUD_GEL_SANDWICH.toStack());
        shaped(output, "", "", ShapedRecipePattern.of(Map.of(
                'C', Ingredient.of(ModFoodItems.CLOUD_BREAD_SLICE),
                'G', Ingredient.of(MaterialItems.GEL),
                'E', Ingredient.of(ModItems.FRIED_EGG.get())
        ), List.of(
                "CC ",
                "GE ",
                "CC "
        )), ModFoodItems.CLOUD_FRIED_EGG_GEL_SANDWICH.toStack());
        shaped(output, "", "", ShapedRecipePattern.of(Map.of(
                'C', Ingredient.of(ModFoodItems.LUCK_CHOCOLATE_COPPER_COIN),
                'S', Ingredient.of(ModFoodItems.LUCK_CHOCOLATE_SILVER_COIN),
                'G', Ingredient.of(ModFoodItems.LUCK_CHOCOLATE_GOLDEN_COIN),
                'P', Ingredient.of(ModFoodItems.LUCK_CHOCOLATE_PLATINUM_COIN)
        ), List.of(
                "CSS",
                "SGP",
                "PPP"
        )), ModFoodItems.CHOCOLATE_LUCK_COIN_BOX.toStack());


        shapeless(output, "", "", ModFoodItems.CLOUD_BACON_SANDWICH.toStack(),
                Ingredient.of(ModFoodItems.BUTTER_FRIED_CLOUD_BREAD_SLICES),
                Ingredient.of(ModItems.COOKED_BACON.get()),
                Ingredient.of(ModItems.TOMATO.get()),
                Ingredient.of(Tags.Items.FOODS_FRUIT));
        shapeless(output, "", "", ModFoodItems.HONEY_GLAZED_HAM.toStack(),
                Ingredient.of(Items.HONEY_BOTTLE),
                Ingredient.of(Items.SUGAR),
                Ingredient.of(MaterialItems.GEL),
                Ingredient.of(ModItems.HAM.get()));
        shapeless(output, "", "", ModFoodItems.WHITE_CHOCOLATE.toStack(),
                Ingredient.of(Items.SUGAR),
                Ingredient.of(Items.MILK_BUCKET),
                Ingredient.of(Items.COCOA_BEANS));
        shapeless(output, "", "", ModFoodItems.BLACK_CHOCOLATE.toStack(),
                Ingredient.of(Items.SUGAR),
                Ingredient.of(Items.COCOA_BEANS));
        shapeless(output, "", "", ModFoodItems.FLAVORED_WHITE_CHOCOLATE.toStack(),
                Ingredient.of(MaterialItems.GEL),
                Ingredient.of(ModFoodItems.WHITE_CHOCOLATE));
        shapeless(output, "", "", ModFoodItems.BLACKCURRANT_DARK_CHOCOLATE_PIE.toStack(),
                Ingredient.of(MaterialItems.GEL),
                Ingredient.of(FoodItems.BLACKCURRANT),
                Ingredient.of(ModFoodItems.BLACK_CHOCOLATE));
        shapeless(output, "", "", ModFoodItems.MANGO_PUDDING.toStack(),
                Ingredient.of(Items.MILK_BUCKET),
                Ingredient.of(Items.SUGAR),
                Ingredient.of(MaterialItems.GEL),
                Ingredient.of(FoodItems.MANGO));
        shapeless(output, "", "", ModFoodItems.BANANA_PUDDING.toStack(),
                Ingredient.of(Items.MILK_BUCKET),
                Ingredient.of(Items.SUGAR),
                Ingredient.of(MaterialItems.GEL),
                Ingredient.of(FoodItems.BANANA));
        shapeless(output, "", "", ModFoodItems.BLACKCURRANT_JAM_BREAD.toStack(),
                Ingredient.of(ModFoodItems.BLACKCURRANT_JAM),
                Ingredient.of(Items.BREAD));
        shapeless(output, "", "", ModFoodItems.BLACKCURRANT_JAM_MANGO_PUDDING.toStack(),
                Ingredient.of(Items.SUGAR),
                Ingredient.of(Items.MILK_BUCKET),
                Ingredient.of(FoodItems.MANGO),
                Ingredient.of(ModFoodItems.BLACKCURRANT_JAM),
                Ingredient.of(MaterialItems.GEL));
        shapeless(output, "", "", ModFoodItems.LUCK_CHOCOLATE_COPPER_COIN.toStack(),
                Ingredient.of(Items.SUGAR),
                Ingredient.of(Items.MILK_BUCKET),
                Ingredient.of(Items.COCOA_BEANS),
                Ingredient.of(org.confluence.mod.common.init.item.ModItems.COPPER_COIN));
        shapeless(output, "", "", ModFoodItems.LUCK_CHOCOLATE_SILVER_COIN.toStack(),
                Ingredient.of(Items.SUGAR),
                Ingredient.of(Items.MILK_BUCKET),
                Ingredient.of(Items.COCOA_BEANS),
                Ingredient.of(org.confluence.mod.common.init.item.ModItems.SILVER_COIN));
        shapeless(output, "", "", ModFoodItems.LUCK_CHOCOLATE_GOLDEN_COIN.toStack(),
                Ingredient.of(Items.SUGAR),
                Ingredient.of(Items.MILK_BUCKET),
                Ingredient.of(Items.COCOA_BEANS),
                Ingredient.of(org.confluence.mod.common.init.item.ModItems.GOLDEN_COIN));
        shapeless(output, "", "", ModFoodItems.LUCK_CHOCOLATE_PLATINUM_COIN.toStack(),
                Ingredient.of(Items.SUGAR),
                Ingredient.of(Items.MILK_BUCKET),
                Ingredient.of(Items.COCOA_BEANS),
                Ingredient.of(org.confluence.mod.common.init.item.ModItems.PLATINUM_COIN));
        shapeless(output, "", "", ModFoodItems.LUCK_CHOCOLATE_EMERALD_COIN.toStack(),
                Ingredient.of(Items.SUGAR),
                Ingredient.of(Items.MILK_BUCKET),
                Ingredient.of(Items.COCOA_BEANS),
                Ingredient.of(org.confluence.mod.common.init.item.ModItems.EMERALD_COIN));


        //熔炉
        furnace(output, "", "", Ingredient.of(ModItems.MILK_BOTTLE.get()), ModFoodItems.BUTTER.toStack(), 0.2f, 50);
        furnace(output, "", "", Ingredient.of(ModFoodItems.RAW_DONKEY_MEAT.get()), ModFoodItems.COOKED_DONKEY_MEAT.toStack(), 0.2f, 100);
        furnace(output, "", "", Ingredient.of(ModFoodItems.RAW_HORSE_MEAT.get()), ModFoodItems.COOKED_HORSE_MEAT.toStack(), 0.2f, 100);
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
