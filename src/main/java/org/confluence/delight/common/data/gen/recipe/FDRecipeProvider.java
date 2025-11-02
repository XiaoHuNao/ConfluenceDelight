package org.confluence.delight.common.data.gen.recipe;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import org.confluence.delight.common.init.CDFoodItems;
import org.confluence.lib.common.data.gen.AbstractRecipeProvider;
import org.confluence.mod.common.init.block.NatureBlocks;
import org.confluence.mod.common.init.item.ConsumableItems;
import org.confluence.mod.common.init.item.FoodItems;
import org.confluence.mod.common.init.item.MaterialItems;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.concurrent.CompletableFuture;


public class FDRecipeProvider extends AbstractRecipeProvider {

    public FDRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        //砧板
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.HONEY_MOONCAKES), Ingredient.of(ModTags.KNIVES), FoodItems.HONEY_MOONCAKES_CHUNKS, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.EGG_YOLK_MOONCAKES), Ingredient.of(ModTags.KNIVES), FoodItems.EGG_YOLK_MOONCAKES_CHUNKS, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.POTATO), Ingredient.of(ModTags.KNIVES), CDFoodItems.POTATO_PIECE, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.SPICY_PEPPER), Ingredient.of(ModTags.KNIVES), CDFoodItems.CRUSHED_CHILLI, 5).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.CLOUD_BREAD), Ingredient.of(ModTags.KNIVES), CDFoodItems.CLOUD_BREAD_SLICE, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(CDFoodItems.RAW_FLYING_FISH_MEAT), Ingredient.of(ModTags.KNIVES), CDFoodItems.RAW_FLYING_FISH_SLICES, 3).build(recipeOutput);

        //厨锅
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.BUTTER, 1, 50, 0.2f)
            .addIngredient(ModItems.MILK_BOTTLE.get())
            .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
            .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.BLACKCURRANT_JAM, 1, 25, 0.2f, Items.GLASS_BOTTLE)
            .addIngredient(FoodItems.BLACKCURRANT)
            .addIngredient(Items.SUGAR)
            .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
            .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.BRAISED_CHICKEN, 1, 400, 1.0f)
            .addIngredient(Items.CHICKEN)
            .addIngredient(Items.SUGAR)
            .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
            .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.HOT_STAR_CHICKEN, 1, 400, 1.0f)
            .addIngredient(Items.COOKED_CHICKEN)
            .addIngredient(CDFoodItems.CRUSHED_CHILLI)
            .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
            .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(FoodItems.COOKED_SQUIRREL, 1, 50, 0.2f)
            .addIngredient(FoodItems.RAW_SQUIRREL)
            .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
            .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(FoodItems.COOKED_BIRD, 1, 50, 0.2f)
            .addIngredient(FoodItems.RAW_BIRD)
            .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
            .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(FoodItems.COOKED_DUCK, 1, 50, 0.2f)
            .addIngredient(FoodItems.RAW_DUCK)
            .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
            .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(FoodItems.COOKED_FROG, 1, 50, 0.2f)
            .addIngredient(FoodItems.RAW_FROG)
            .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
            .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.THE_MEAL_OF_LIFE, 1, 400, 1.0f, Items.BOWL)
            .addIngredient(MaterialItems.LIFE_MUSHROOM)
            .addIngredient(Items.BROWN_MUSHROOM)
            .addIngredient(Items.RED_MUSHROOM)
            .addIngredient(Items.CHICKEN)
            .addIngredient(Items.GOLDEN_APPLE)
            .addIngredient(ConsumableItems.LIFE_CRYSTAL)
            .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
            .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.COOKED_ROSEWOOD_MEAT, 1, 200, 0.35f)
            .addIngredient(CDFoodItems.RAW_ROSEWOOD_MEAT)
            .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
            .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.COOKED_PROLIFERATING_FLESH_AND_BLOOD, 1, 200, 0.35f)
            .addIngredient(CDFoodItems.RAW_PROLIFERATING_FLESH_AND_BLOOD)
            .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
            .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.FLYING_FISH_SHARK_FIN_SOUP, 1, 200, 1.0f, Items.BOWL)
            .addIngredient(MaterialItems.FILAMENTOUS_FIN)
            .addIngredient(Tags.Items.MUSHROOMS)
            .addIngredient(ModItems.ONION.get())
            .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
            .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.FLYING_FISH_SOUP, 1, 200, 1.0f, Items.BOWL)
            .addIngredient(CDFoodItems.RAW_FLYING_FISH_MEAT)
            .addIngredient(Tags.Items.MUSHROOMS)
            .addIngredient(CDFoodItems.POTATO_PIECE)
            .addIngredient(CDFoodItems.POTATO_PIECE)
            .addIngredient(CDFoodItems.POTATO_PIECE)
            .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
            .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.ASH_TEA, 1, 300, 0.5f, Items.POTION)
            .addIngredient(ItemTags.LEAVES)
            .addIngredient(NatureBlocks.ASH_BLOCK)
            .addIngredient(Items.CHARCOAL)
            .addIngredient(Items.SUGAR)
            .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
            .build(recipeOutput);
    }
}
