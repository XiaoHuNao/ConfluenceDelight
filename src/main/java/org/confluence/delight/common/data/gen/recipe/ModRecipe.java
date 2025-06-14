package org.confluence.delight.common.data.gen.recipe;


import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.ModFluids;
import org.confluence.delight.common.init.ModFoodItems;
import org.confluence.delight.common.recipe.PickleJarsRecipe;
import org.confluence.lib.common.data.gen.AbstractRecipeProvider;
import org.confluence.lib.common.recipe.AmountIngredient;
import org.confluence.mod.Confluence;
import org.confluence.mod.common.init.item.FoodItems;
import org.confluence.mod.common.init.item.MaterialItems;
import org.confluence.mod.common.init.item.PotionItems;
import org.confluence.mod.common.recipe.CookingPotRecipe;
import org.confluence.terra_curio.common.init.TCItems;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.concurrent.CompletableFuture;


public class ModRecipe extends AbstractRecipeProvider {

    public ModRecipe(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        //汇流烹饪锅
        Ingredient bottleContainer = Ingredient.of(PotionItems.BOTTLE);
        Ingredient mugContainer = Ingredient.of(PotionItems.MUG);
        Ingredient bowlContainer = Ingredient.of(Items.BOWL);
        CookingPotRecipe.HeatSourcePredicate campfireHeatSource = CookingPotRecipe.HeatSourcePredicate.builder().of(BlockTags.CAMPFIRES).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.LIT, true)).build();
        CookingPotRecipe.HeatSourcePredicate stoveHeatSource = CookingPotRecipe.HeatSourcePredicate.builder().of(ModBlocks.STOVE.get()).build();
        CookingPotRecipe.HeatSourcePredicate blueIceHeatSource = CookingPotRecipe.HeatSourcePredicate.builder().of(Blocks.BLUE_ICE).build();
        cookingPot(recipeOutput, ModFoodItems.CHICKEN_HOT_POT.toStack(), bowlContainer, stoveHeatSource, 500, AmountIngredient.of(16, FoodItems.SPICY_PEPPER), AmountIngredient.of(4, ModItems.CHICKEN_CUTS.get()), AmountIngredient.of(3, ModFoodItems.POTATO_PIECE));
        cookingPot(recipeOutput, ModFoodItems.ROYAL_GUMMY.toStack(), Ingredient.EMPTY, CookingPotRecipe.HeatSourcePredicate.EMPTY, 200, Ingredient.of(Items.HONEY_BOTTLE), Ingredient.of(TCItems.ROYAL_GEL.get()), Ingredient.of(MaterialItems.AMBER));
        cookingPot(recipeOutput, ModFoodItems.ATLANTIS_TSUNAMI.toStack(), Ingredient.of(PotionItems.MUG), blueIceHeatSource, 300, Ingredient.of(MaterialItems.HEIM), Ingredient.of(Items.SUGAR), Ingredient.of(FoodItems.LEMON), Ingredient.of(PotionItems.ALE));
        cookingPot(recipeOutput, ModFoodItems.DONKEY_MEAT_FIRE.toStack(), Ingredient.EMPTY, stoveHeatSource, 400, Ingredient.of(ModFoodItems.RAW_DONKEY_MEAT), Ingredient.of(Items.BREAD));
        cookingPot(recipeOutput, ModFoodItems.HORSE_MEAT_SASHIMI.toStack(), Ingredient.EMPTY, stoveHeatSource, 400, Ingredient.of(ModFoodItems.RAW_HORSE_MEAT));
        cookingPot(recipeOutput, ModFoodItems.BLACK_LUCK.toStack(), mugContainer, blueIceHeatSource, 200, Ingredient.of(Items.ICE), Ingredient.of(Items.MILK_BUCKET), Ingredient.of(FoodItems.BLACKCURRANT));
        cookingPot(recipeOutput, ModFoodItems.WHITE_DAWN.toStack(), mugContainer, blueIceHeatSource, 200, Ingredient.of(Items.ICE), Ingredient.of(Items.MILK_BUCKET), Ingredient.of(FoodItems.BANANA));
        cookingPot(recipeOutput, ModFoodItems.SWEET_CARROT_CUBES.toStack(), mugContainer, CookingPotRecipe.HeatSourcePredicate.EMPTY, 100, Ingredient.of(Items.CARROT), Ingredient.of(Items.SUGAR), Ingredient.of(FoodItems.SPICY_PEPPER));
        cookingPot(recipeOutput, ModFoodItems.BUTTER.toStack(), Ingredient.EMPTY, stoveHeatSource, 50, Ingredient.of(ModItems.MILK_BOTTLE.get()));

        //泡菜罐
        pickleJarsRecipe(recipeOutput, true, ModFoodItems.JAR_CHILI_PEPPERS.toStack(), new FluidStack(Fluids.WATER, 2000), 1000, Ingredient.of(FoodItems.SPICY_PEPPER));
        pickleJarsRecipe(recipeOutput, true, ModFoodItems.SPICY_PICKLED_FISH.toStack(), new FluidStack(ModFluids.WINE.fluid(), 2000), 1200, Ingredient.of(ItemTags.CAT_FOOD), AmountIngredient.of(10, FoodItems.SPICY_PEPPER), AmountIngredient.of(2, Items.SUGAR));

        //砧板
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.HONEY_MOONCAKES.get()), Ingredient.of(ModTags.KNIVES), FoodItems.HONEY_MOONCAKES_CHUNKS, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.EGG_YOLK_MOONCAKES.get()), Ingredient.of(ModTags.KNIVES), FoodItems.EGG_YOLK_MOONCAKES_CHUNKS, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.POTATO), Ingredient.of(ModTags.KNIVES), ModFoodItems.POTATO_PIECE.get(), 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.SPICY_PEPPER.get()), Ingredient.of(ModTags.KNIVES), ModFoodItems.CRUSHED_CHILLI.get(), 5).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.CARROT), Ingredient.of(ModTags.KNIVES), ModFoodItems.CARROT_CUBES, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.CLOUD_BREAD), Ingredient.of(ModTags.KNIVES), ModFoodItems.CLOUD_BREAD_SLICE, 2).build(recipeOutput);

        //厨锅
        CookingPotRecipeBuilder.cookingPotRecipe(ModFoodItems.BUTTER.get(), 1, 50, 0.2f).addIngredient(ModItems.MILK_BOTTLE.get()).setRecipeBookTab(CookingPotRecipeBookTab.MISC).build(recipeOutput);
        CookingPotRecipeBuilder.cookingPotRecipe(ModFoodItems.BLACKCURRANT_JAM.get(), 1, 25, 0.2f, Items.GLASS_BOTTLE).addIngredient(FoodItems.BLACKCURRANT.get()).addIngredient(Items.SUGAR).setRecipeBookTab(CookingPotRecipeBookTab.MISC).build(recipeOutput);
    }

    protected void cookingPot(RecipeOutput recipeOutput, ItemStack result, Ingredient container, CookingPotRecipe.HeatSourcePredicate heatSource, int cookingTime, Ingredient... ingredients) {
        ResourceLocation id = Confluence.asResource("cooking_pot/" + getItemName(result.getItem()));
        NonNullList<Ingredient> zingredients = NonNullList.of(Ingredient.EMPTY, ingredients);
        recipeOutput.accept(id, new CookingPotRecipe(result, zingredients, container, heatSource, cookingTime), null);
    }

    protected void pickleJarsRecipe(RecipeOutput recipeOutput, boolean cover, ItemStack result, FluidStack fluidInput, int craftTime, Ingredient... ingredients) {
        ResourceLocation id = ConfluenceDelight.asResource("pickle_jars/" + getItemName(result.getItem()));
        NonNullList<Ingredient> recipeIngredients = NonNullList.of(Ingredient.EMPTY, ingredients);
        PickleJarsRecipe recipe = new PickleJarsRecipe(result, recipeIngredients, fluidInput, craftTime, cover);
        recipeOutput.accept(id, recipe, null);
    }


}
