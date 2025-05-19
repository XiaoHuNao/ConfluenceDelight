package org.confluence.delight.common.data.gen.recipe;


import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.confluence.delight.common.init.ModFoodItems;
import org.confluence.lib.common.recipe.AmountIngredient;
import org.confluence.mod.Confluence;
import org.confluence.mod.common.init.item.FoodItems;
import org.confluence.mod.common.init.item.MaterialItems;
import org.confluence.mod.common.init.item.PotionItems;
import org.confluence.mod.common.recipe.CookingPotRecipe;
import org.confluence.terra_curio.common.init.TCItems;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
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
        //汇流烹饪锅
        Ingredient bottleContainer = Ingredient.of(PotionItems.BOTTLE);
        Ingredient bowlContainer = Ingredient.of(Items.BOWL);
        CookingPotRecipe.HeatSourcePredicate campfireHeatSource = CookingPotRecipe.HeatSourcePredicate.builder().of(BlockTags.CAMPFIRES).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.LIT, true)).build();
        CookingPotRecipe.HeatSourcePredicate stoveHeatSource = CookingPotRecipe.HeatSourcePredicate.builder().of(ModBlocks.STOVE.get()).build();
        CookingPotRecipe.HeatSourcePredicate blueIceHeatSource = CookingPotRecipe.HeatSourcePredicate.builder().of(Blocks.BLUE_ICE).build();
        cookingPot(recipeOutput, ModFoodItems.CHICKEN_HOT_POT.toStack(), bowlContainer, stoveHeatSource, 500, AmountIngredient.of(16, FoodItems.SPICY_PEPPER), AmountIngredient.of(4, ModItems.CHICKEN_CUTS.get()), AmountIngredient.of(3, ModFoodItems.POTATO_PIECE));
        cookingPot(recipeOutput, ModFoodItems.ROYAL_GUMMY.toStack(), Ingredient.EMPTY, CookingPotRecipe.HeatSourcePredicate.EMPTY, 200, Ingredient.of(Items.HONEY_BOTTLE), Ingredient.of(TCItems.ROYAL_GEL.get()), Ingredient.of(MaterialItems.AMBER));
        cookingPot(recipeOutput, ModFoodItems.ATLANTIS_TSUNAMI.toStack(), Ingredient.of(PotionItems.MUG), blueIceHeatSource, 300, Ingredient.of(MaterialItems.HEIM), Ingredient.of(Items.SUGAR), Ingredient.of(FoodItems.LEMON), Ingredient.of(PotionItems.ALE));

        //砧板
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.HONEY_MOONCAKES.get()), Ingredient.of(ModTags.KNIVES), FoodItems.HONEY_MOONCAKES_CHUNKS, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.EGG_YOLK_MOONCAKES.get()), Ingredient.of(ModTags.KNIVES), FoodItems.EGG_YOLK_MOONCAKES_CHUNKS, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.POTATO), Ingredient.of(ModTags.KNIVES), ModFoodItems.POTATO_PIECE.get(), 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.SPICY_PEPPER.get()), Ingredient.of(ModTags.KNIVES), ModFoodItems.CRUSHED_CHILLI.get(), 5).build(recipeOutput);
    }

    protected void cookingPot(RecipeOutput recipeOutput, ItemStack result, Ingredient container, CookingPotRecipe.HeatSourcePredicate heatSource, int cookingTime, Ingredient... ingredients) {
        ResourceLocation id = Confluence.asResource("cooking_pot/" + getItemName(result.getItem()) + "");
        NonNullList<Ingredient> zingredients = NonNullList.of(Ingredient.EMPTY, ingredients);
        recipeOutput.accept(id, new CookingPotRecipe(result, zingredients, container, heatSource, cookingTime), null);
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
