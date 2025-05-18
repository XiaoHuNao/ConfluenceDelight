package org.confluence.delight.common.data.gen.recipe;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.confluence.delight.common.init.ModFoodItems;
import org.confluence.mod.common.init.item.FoodItems;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.concurrent.CompletableFuture;

import static org.confluence.delight.ConfluenceDelight.MODID;


public class ModRecipe extends RecipeProvider {

    public ModRecipe(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.HONEY_MOONCAKES.get()), Ingredient.of(ModTags.KNIVES), FoodItems.HONEY_MOONCAKES_CHUNKS, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.EGG_YOLK_MOONCAKES.get()), Ingredient.of(ModTags.KNIVES), FoodItems.EGG_YOLK_MOONCAKES_CHUNKS, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.POTATO), Ingredient.of(ModTags.KNIVES), ModFoodItems.POTATO_PIECE.get(), 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.SPICY_PEPPER.get()), Ingredient.of(ModTags.KNIVES), ModFoodItems.CRUSHED_CHILLI.get(), 5).build(recipeOutput);
    }

    protected static <T extends AbstractCookingRecipe> void cookRecipes(RecipeOutput recipeOutput, String cookingMethod, RecipeSerializer<T> cookingSerializer, AbstractCookingRecipe.Factory<T> recipeFactory, int cookingTime) {
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
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(MODID, getItemName(result) + "_from_" + cookingMethod));
    }
}
