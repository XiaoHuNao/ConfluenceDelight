package org.confluence.delight.common.data.gen.recipe;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.confluence.delight.common.init.CDFoodItems;
import org.confluence.delight.common.init.CDMaterialItems;
import org.confluence.lib.common.data.gen.AbstractRecipeProvider;
import org.confluence.mod.common.init.item.ConsumableItems;
import org.confluence.mod.common.init.item.FoodItems;
import org.confluence.mod.common.init.item.MaterialItems;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.concurrent.CompletableFuture;


public class FDRecipe extends AbstractRecipeProvider {

    public FDRecipe(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        //砧板
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.HONEY_MOONCAKES.get()), Ingredient.of(ModTags.KNIVES), FoodItems.HONEY_MOONCAKES_CHUNKS, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.EGG_YOLK_MOONCAKES.get()), Ingredient.of(ModTags.KNIVES), FoodItems.EGG_YOLK_MOONCAKES_CHUNKS, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.POTATO), Ingredient.of(ModTags.KNIVES), CDFoodItems.POTATO_PIECE.get(), 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.SPICY_PEPPER.get()), Ingredient.of(ModTags.KNIVES), CDFoodItems.CRUSHED_CHILLI.get(), 5).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.CARROT), Ingredient.of(ModTags.KNIVES), CDFoodItems.CARROT_CUBES, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.CLOUD_BREAD), Ingredient.of(ModTags.KNIVES), CDFoodItems.CLOUD_BREAD_SLICE, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.RAW_SQUIRREL), Ingredient.of(ModTags.KNIVES), CDFoodItems.SQUIRREL_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.RAW_BIRD), Ingredient.of(ModTags.KNIVES), CDFoodItems.BIRD_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.RAW_DUCK), Ingredient.of(ModTags.KNIVES), CDFoodItems.DUCK_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.RAW_FROG), Ingredient.of(ModTags.KNIVES), CDFoodItems.FROG_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(CDFoodItems.RAW_DONKEY_MEAT), Ingredient.of(ModTags.KNIVES), CDFoodItems.DONKEY_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(CDFoodItems.RAW_HORSE_MEAT), Ingredient.of(ModTags.KNIVES), CDFoodItems.HORSE_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(CDFoodItems.SQUIRREL_MEAT_CHUNKS), Ingredient.of(ModTags.KNIVES), CDFoodItems.SQUIRREL_CITATAP).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.COOKED_SQUIRREL), Ingredient.of(ModTags.KNIVES), CDFoodItems.COOKED_SQUIRREL_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.COOKED_BIRD), Ingredient.of(ModTags.KNIVES), CDFoodItems.COOKED_BIRD_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.COOKED_DUCK), Ingredient.of(ModTags.KNIVES), CDFoodItems.COOKED_DUCK_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.COOKED_FROG), Ingredient.of(ModTags.KNIVES), CDFoodItems.COOKED_FROG_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(CDFoodItems.COOKED_DONKEY_MEAT), Ingredient.of(ModTags.KNIVES), CDFoodItems.COOKED_DONKEY_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(CDFoodItems.COOKED_HORSE_MEAT), Ingredient.of(ModTags.KNIVES), CDFoodItems.COOKED_HORSE_MEAT_CHUNKS, 2).build(recipeOutput);

        //厨锅
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.BUTTER.get(), 1, 50, 0.2f)
                .addIngredient(ModItems.MILK_BOTTLE.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.BLACKCURRANT_JAM.get(), 1, 25, 0.2f, Items.GLASS_BOTTLE)
                .addIngredient(FoodItems.BLACKCURRANT.get())
                .addIngredient(Items.SUGAR)
                .setRecipeBookTab(CookingPotRecipeBookTab.MISC)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.BRAISED_BEEF_RICE.get(), 1, 200, 1.0f, Items.BOWL)
                .addIngredient(ModItems.RICE.get())
                .addIngredient(Items.BEEF)
                .addIngredient(CDFoodItems.POTATO_PIECE)
                .addIngredient(ModItems.CABBAGE.get())
                .addIngredient(MaterialItems.LIFE_MUSHROOM)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.BRAISED_BEEF_NOODLES.get(), 1, 200, 1.0f, Items.BOWL)
                .addIngredient(ModItems.RAW_PASTA.get())
                .addIngredient(ModItems.CABBAGE.get())
                .addIngredient(Items.BEEF)
                .addIngredient(CDFoodItems.POTATO_PIECE)
                .addIngredient(CDFoodItems.CARROT_CUBES)
                .addIngredient(MaterialItems.LIFE_MUSHROOM)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.CRISPY_RICE_WITH_POTATOES.get(), 1, 100, 1.0f, Items.BOWL)
                .addIngredient(CDFoodItems.POTATO_PIECE)
                .addIngredient(CDFoodItems.CRUSHED_CHILLI)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.CUMIN_FLAVORED_HORSE_MEAT_OVER_RICE.get(), 1, 200, 1.0f, Items.BOWL)
                .addIngredient(CDMaterialItems.CUMIN_POWDER)
                .addIngredient(CDFoodItems.RAW_HORSE_MEAT)
                .addIngredient(ModItems.RICE.get())
                .addIngredient(ModItems.ONION.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.CUMIN_FLAVORED_DONKEY_MEAT_OVER_RICE.get(), 1, 200, 1.0f, Items.BOWL)
                .addIngredient(CDMaterialItems.CUMIN_POWDER)
                .addIngredient(CDFoodItems.RAW_DONKEY_MEAT)
                .addIngredient(ModItems.RICE.get())
                .addIngredient(ModItems.ONION.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.BRAISED_CHICKEN.get(), 1, 400, 1.0f)
                .addIngredient(Items.CHICKEN)
                .addIngredient(Items.SUGAR)
                .addIngredient(CDMaterialItems.SALT)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.HOT_STAR_CHICKEN.get(), 1, 400, 1.0f)
                .addIngredient(Items.CHICKEN)
                .addIngredient(CDMaterialItems.CHILI_POWDER)
                .addIngredient(CDMaterialItems.SALT)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.COOKED_SQUIRREL_MEAT_CHUNKS.get(), 1, 50, 0.2f)
                .addIngredient(CDFoodItems.SQUIRREL_MEAT_CHUNKS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.COOKED_DUCK_MEAT_CHUNKS.get(), 1, 50, 0.2f)
                .addIngredient(CDFoodItems.DUCK_MEAT_CHUNKS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.COOKED_FROG_MEAT_CHUNKS.get(), 1, 50, 0.2f)
                .addIngredient(CDFoodItems.FROG_MEAT_CHUNKS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.COOKED_DONKEY_MEAT_CHUNKS.get(), 1, 50, 0.2f)
                .addIngredient(CDFoodItems.DONKEY_MEAT_CHUNKS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.COOKED_HORSE_MEAT_CHUNKS.get(), 1, 50, 0.2f)
                .addIngredient(CDFoodItems.HORSE_MEAT_CHUNKS.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(FoodItems.COOKED_SQUIRREL.get(), 1, 50, 0.2f)
                .addIngredient(FoodItems.RAW_SQUIRREL.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(FoodItems.COOKED_BIRD.get(), 1, 50, 0.2f)
                .addIngredient(FoodItems.RAW_BIRD.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(FoodItems.COOKED_DUCK.get(), 1, 50, 0.2f)
                .addIngredient(FoodItems.RAW_DUCK.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(FoodItems.COOKED_FROG.get(), 1, 50, 0.2f)
                .addIngredient(FoodItems.RAW_FROG.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.COOKED_DONKEY_MEAT.get(), 1, 50, 0.2f)
                .addIngredient(CDFoodItems.RAW_DONKEY_MEAT.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.COOKED_HORSE_MEAT.get(), 1, 50, 0.2f)
                .addIngredient(CDFoodItems.RAW_HORSE_MEAT.get())
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(CDFoodItems.THE_MEAL_OF_LIFE.get(), 1, 400, 1.0f, Items.BOWL)
                .addIngredient(MaterialItems.LIFE_MUSHROOM.get())
                .addIngredient(Items.BROWN_MUSHROOM)
                .addIngredient(Items.RED_MUSHROOM)
                .addIngredient(Items.CHICKEN)
                .addIngredient(Items.GOLDEN_APPLE)
                .addIngredient(ConsumableItems.LIFE_CRYSTAL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(recipeOutput);
    }
}
