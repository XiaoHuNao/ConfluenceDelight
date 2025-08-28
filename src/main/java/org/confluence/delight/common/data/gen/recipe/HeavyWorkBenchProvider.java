package org.confluence.delight.common.data.gen.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.neoforged.neoforge.common.Tags;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDFoodItems;
import org.confluence.lib.common.data.gen.AbstractRecipeProvider;
import org.confluence.lib.common.recipe.AmountIngredient;
import org.confluence.lib.common.recipe.EnvironmentLevelAccess;
import org.confluence.mod.Confluence;
import org.confluence.mod.common.init.item.ConsumableItems;
import org.confluence.mod.common.recipe.HeavyWorkBenchRecipe;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class HeavyWorkBenchProvider extends AbstractRecipeProvider {
    public HeavyWorkBenchProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput, HolderLookup.Provider holderLookup) {
        shaped(recipeOutput, "", ShapedRecipePattern.of(Map.of(
                'C', AmountIngredient.of(99, CDFoodItems.LUCK_CHOCOLATE_COPPER_COIN),
                'S', AmountIngredient.of(99, CDFoodItems.LUCK_CHOCOLATE_SILVER_COIN),
                'G', AmountIngredient.of(99, CDFoodItems.LUCK_CHOCOLATE_GOLDEN_COIN),
                'P', AmountIngredient.of(99, CDFoodItems.LUCK_CHOCOLATE_PLATINUM_COIN),
                'K', AmountIngredient.of(64, Items.GOLD_BLOCK)
        ), List.of(
                "CCCK",
                "SSSK",
                "GGGK",
                "PPPK"
        )), CDFoodItems.GILDED_LUXURY_CHOCOLATE_LUCK_COIN_BOX.toStack());
        shaped(recipeOutput, "", ShapedRecipePattern.of(Map.of(
                'H', Ingredient.of(Items.HOPPER),
                'C', Ingredient.of(Items.COPPER_INGOT),
                'G', Ingredient.of(Tags.Items.GLASS_BLOCKS_CHEAP),
                'F', Ingredient.of(ItemTags.FENCES),
                'S', Ingredient.of(ConsumableItems.SHURIKEN)
        ), List.of(
                " H  ",
                "CGC ",
                "FSCG",
                "CGC "
        )), CDBlocks.JUICER_BLOCK.toStack());
    }

    protected void shaped(RecipeOutput recipeOutput, String suffix, ShapedRecipePattern pattern, ItemStack result) {
        ResourceLocation id = Confluence.asResource("heavy_work_bench/" + getItemName(result.getItem()) + suffix);
        recipeOutput.accept(id, new HeavyWorkBenchRecipe(result, pattern, EnvironmentLevelAccess.Matcher.EMPTY), null);
    }

    protected void shaped(RecipeOutput recipeOutput, ShapedRecipePattern pattern, ItemStack result) {
        ResourceLocation id = Confluence.asResource("heavy_work_bench/" + getItemName(result.getItem()));
        recipeOutput.accept(id, new HeavyWorkBenchRecipe(result, pattern, EnvironmentLevelAccess.Matcher.EMPTY), null);
    }
}
