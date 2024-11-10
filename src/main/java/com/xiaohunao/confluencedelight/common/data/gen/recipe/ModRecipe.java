package com.xiaohunao.confluencedelight.common.data.gen.recipe;


import com.xiaohunao.confluencedelight.common.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;


import java.util.concurrent.CompletableFuture;

import static com.xiaohunao.confluencedelight.ConfluenceDelight.MODID;


public class ModRecipe extends RecipeProvider {

    public ModRecipe(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, PlantItems.BREAD_SWORD.get())
//                .pattern("A")
//                .pattern("A")
//                .pattern("B")
//                .define('A',Items.BREAD)
//                .define('B',Items.STICK)
//                .unlockedBy("has_ruby",has(Items.BREAD))
//                .save(recipeOutput);
//
//
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TEST_FOOD.get())
                .requires(Items.WHEAT,2)
                .unlockedBy("has_wheat",has(Items.WHEAT))
                .save(recipeOutput);


//        cookRecipes(recipeOutput, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 100);
    }

    protected static <T extends AbstractCookingRecipe> void cookRecipes(
            RecipeOutput recipeOutput, String cookingMethod, RecipeSerializer<T> cookingSerializer, AbstractCookingRecipe.Factory<T> recipeFactory, int cookingTime
    ) {
//        simpleCookingRecipe(recipeOutput, cookingMethod, cookingSerializer, recipeFactory, cookingTime, ModItems.BREAD_SWORD, ModItems.BREAD_SWORD_HOT, 0.35F);
//        simpleCookingRecipe(recipeOutput, cookingMethod, cookingSerializer, recipeFactory, cookingTime, ModItems.BREAD_SWORD_HOT, ModItems.BREAD_SWORD_VERY_HOT, 0.35F);
//

    }

    protected static <T extends AbstractCookingRecipe> void simpleCookingRecipe(
            RecipeOutput recipeOutput,
            String cookingMethod,
            RecipeSerializer<T> cookingSerializer,
            AbstractCookingRecipe.Factory<T> recipeFactory,
            int cookingTime,
            ItemLike material,
            ItemLike result,
            float experience
    ) {
        SimpleCookingRecipeBuilder.generic(Ingredient.of(material), RecipeCategory.FOOD, result, experience, cookingTime, cookingSerializer, recipeFactory)
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(MODID,getItemName(result) + "_from_" + cookingMethod));
    }
}
