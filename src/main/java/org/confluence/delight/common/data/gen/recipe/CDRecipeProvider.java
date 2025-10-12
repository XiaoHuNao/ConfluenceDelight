package org.confluence.delight.common.data.gen.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidStack;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.CDFluids;
import org.confluence.delight.common.init.CDFoodItems;
import org.confluence.delight.common.init.CDNaturalBlocks;
import org.confluence.delight.common.recipe.BlockAndItemInteractionRecipe;
import org.confluence.delight.common.recipe.JuicerRecipe;
import org.confluence.delight.common.recipe.MillStoneRecipe;
import org.confluence.delight.common.recipe.PickleJarsRecipe;
import org.confluence.lib.common.data.gen.AbstractRecipeProvider;
import org.confluence.lib.common.recipe.AmountIngredient;
import org.confluence.mod.common.init.block.NatureBlocks;
import org.confluence.mod.common.init.item.FoodItems;

import java.util.concurrent.CompletableFuture;

public class CDRecipeProvider extends AbstractRecipeProvider {
    public CDRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        //泡菜罐
        FluidStack water1000 = new FluidStack(Fluids.WATER, 1000);
        FluidStack water2000 = new FluidStack(Fluids.WATER, 2000);
        FluidStack wine1000 = new FluidStack(CDFluids.WINE.fluid(), 1000);
        FluidStack wine2000 = new FluidStack(CDFluids.WINE.fluid(), 2000);
        pickleJarsRecipe(recipeOutput, true, CDFoodItems.JAR_CHILI_PEPPERS.toStack(), water2000, 1000, Ingredient.of(FoodItems.SPICY_PEPPER));
        pickleJarsRecipe(recipeOutput, true, CDFoodItems.CHOP_BELL_PEPPER.toStack(10), wine1000, 1000, AmountIngredient.of(10, Items.SUGAR), AmountIngredient.of(10, CDFoodItems.CRUSHED_CHILLI));

        //磨盘

        //榨汁机
        FluidStack WATER_1000 = new FluidStack(Fluids.WATER, 1000);
        ItemLike BOTTLE = Items.GLASS_BOTTLE;
        juicerRecipe(recipeOutput, FoodItems.APPLE_JUICE.toStack(), 5, BOTTLE, WATER_1000, AmountIngredient.of(2, Items.APPLE));
        juicerRecipe(recipeOutput, CDFoodItems.APRICOT_JUICE.toStack(), 5, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.APRICOT));
        juicerRecipe(recipeOutput, CDFoodItems.BANANA_JUICE.toStack(), 5, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.BANANA));
        juicerRecipe(recipeOutput, CDFoodItems.CHERRY_JUICE.toStack(), 4, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.CHERRY));
        juicerRecipe(recipeOutput, CDFoodItems.COCONUT_JUICE.toStack(), 6, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.COCONUT));
        juicerRecipe(recipeOutput, CDFoodItems.DRAGON_FRUIT_JUICE.toStack(), 6, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.DRAGON_FRUIT));
        juicerRecipe(recipeOutput, CDFoodItems.GRAPEFRUIT_JUICE.toStack(), 5, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.GRAPE_FRUIT));
        juicerRecipe(recipeOutput, CDFoodItems.LEMON_JUICE.toStack(), 4, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.LEMON));
        juicerRecipe(recipeOutput, CDFoodItems.MANGO_JUICE.toStack(), 5, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.MANGO));
        juicerRecipe(recipeOutput, CDFoodItems.PEACH_JUICE.toStack(), 4, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.PEACH));
        juicerRecipe(recipeOutput, CDFoodItems.PINEAPPLE_JUICE.toStack(), 6, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.PINEAPPLE));
        juicerRecipe(recipeOutput, CDFoodItems.PLUM_JUICE.toStack(), 4, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.PLUM));
        juicerRecipe(recipeOutput, CDFoodItems.GRAPE_JUICE.toStack(), 5, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.GRAPE));
        juicerRecipe(recipeOutput, CDFoodItems.STAR_FRUIT_JUICE.toStack(), 5, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.STAR_FRUIT));
        juicerRecipe(recipeOutput, CDFoodItems.POMEGRANATE_JUICE.toStack(), 5, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.POMEGRANATE));
        juicerRecipe(recipeOutput, CDFoodItems.RAMBUTAN_JUICE.toStack(), 5, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.RAMBUTAN));
        juicerRecipe(recipeOutput, CDFoodItems.BLOOD_ORANGE_JUICE.toStack(), 5, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.BLOOD_ORANGE));
        juicerRecipe(recipeOutput, CDFoodItems.ELDERBERRY_JUICE.toStack(), 4, BOTTLE, WATER_1000, AmountIngredient.of(2, FoodItems.ELDERBERRY));
        juicerRecipe(recipeOutput, CDFoodItems.FRESHLY_SQUEEZED_VITALITY.toStack(), 8, BOTTLE, WATER_1000, Ingredient.of(Tags.Items.FOODS_FRUIT), Ingredient.of(Tags.Items.FOODS_FRUIT), Ingredient.of(Tags.Items.FOODS_FRUIT));

        //方块转换
        blockInteractionRecipe(recipeOutput, Ingredient.of(Items.APPLE), CDNaturalBlocks.APPLE_SAPLING.get(), Blocks.OAK_SAPLING);
        blockInteractionRecipe(recipeOutput, Ingredient.of(FoodItems.APRICOT), CDNaturalBlocks.APRICOT_SAPLING.get(), Blocks.BIRCH_SAPLING);
        blockInteractionRecipe(recipeOutput, Ingredient.of(FoodItems.GRAPE_FRUIT), CDNaturalBlocks.GRAPEFRUIT_SAPLING.get(), Blocks.JUNGLE_SAPLING);
        blockInteractionRecipe(recipeOutput, Ingredient.of(FoodItems.LEMON), CDNaturalBlocks.LEMON_SAPLING.get(), Blocks.SPRUCE_SAPLING);
        blockInteractionRecipe(recipeOutput, Ingredient.of(FoodItems.PEACH), CDNaturalBlocks.PEACH_SAPLING.get(), Blocks.OAK_SAPLING);
        blockInteractionRecipe(recipeOutput, Ingredient.of(FoodItems.CHERRY), CDNaturalBlocks.CHERRY_SAPLING.get(), Blocks.SPRUCE_SAPLING);
        blockInteractionRecipe(recipeOutput, Ingredient.of(FoodItems.PLUM), CDNaturalBlocks.PLUM_SAPLING.get(), Blocks.BIRCH_SAPLING);
        blockInteractionRecipe(recipeOutput, Ingredient.of(FoodItems.BLOOD_ORANGE), CDNaturalBlocks.BLOOD_ORANGE_SAPLING.get(), Blocks.JUNGLE_SAPLING);
        blockInteractionRecipe(recipeOutput, Ingredient.of(FoodItems.RAMBUTAN), CDNaturalBlocks.RAMBUTAN_SAPLING.get(), Blocks.JUNGLE_SAPLING);
        blockInteractionRecipe(recipeOutput, Ingredient.of(FoodItems.MANGO), CDNaturalBlocks.MANGO_SAPLING.get(), Blocks.JUNGLE_SAPLING);
        blockInteractionRecipe(recipeOutput, Ingredient.of(FoodItems.BANANA), CDNaturalBlocks.BANANA_SAPLING.get(), Blocks.JUNGLE_SAPLING);
        blockInteractionRecipe(recipeOutput, Ingredient.of(FoodItems.COCONUT), CDNaturalBlocks.COCONUT_SAPLING.get(), Blocks.JUNGLE_SAPLING);
        blockInteractionRecipe(recipeOutput, Ingredient.of(FoodItems.STAR_FRUIT), CDNaturalBlocks.STAR_FRUIT_SAPLING.get(), Blocks.OAK_SAPLING);
        blockInteractionRecipe(recipeOutput, Ingredient.of(FoodItems.POMEGRANATE), CDNaturalBlocks.POMEGRANATE_SAPLING.get(), Blocks.BIRCH_SAPLING);
        blockInteractionRecipe(recipeOutput, Ingredient.of(FoodItems.BLACKCURRANT), CDNaturalBlocks.BLACKCURRANT_SHRUB_BLOCK.get(), Blocks.AZALEA, Blocks.FLOWERING_AZALEA);
        blockInteractionRecipe(recipeOutput, Ingredient.of(FoodItems.ELDERBERRY), CDNaturalBlocks.ELDERBERRY_SHRUB_BLOCK.get(), Blocks.AZALEA, Blocks.FLOWERING_AZALEA);
        blockInteractionRecipe(recipeOutput, Ingredient.of(CDFoodItems.BLOOD_TUMOR_FRUIT), CDNaturalBlocks.BLOOD_MEAT_VINE_BLOCK.get(), NatureBlocks.CRIMSON_DROOPING_VINE.get());
    }

    protected void pickleJarsRecipe(RecipeOutput recipeOutput, boolean cover, ItemStack result, FluidStack fluidInput, int craftTime, Ingredient... ingredients) {
        ResourceLocation id = ConfluenceDelight.asResource("pickle_jars/" + getItemName(result.getItem()));
        NonNullList<Ingredient> recipeIngredients = NonNullList.of(Ingredient.EMPTY, ingredients);
        PickleJarsRecipe recipe = new PickleJarsRecipe(result, recipeIngredients, fluidInput, craftTime, cover);
        recipeOutput.accept(id, recipe, null);
    }

    protected void pickleJarsRecipe(RecipeOutput recipeOutput, boolean fermentation, boolean cover, ItemStack result, FluidStack fluidInput, int craftTime, Ingredient... ingredients) {
        ResourceLocation id = ConfluenceDelight.asResource("pickle_jars/" + getItemName(result.getItem()));
        NonNullList<Ingredient> recipeIngredients = NonNullList.of(Ingredient.EMPTY, ingredients);
        PickleJarsRecipe recipe = new PickleJarsRecipe(fermentation, result, recipeIngredients, fluidInput, craftTime, cover);
        recipeOutput.accept(id, recipe, null);
    }


    protected void millStoneRecipe(RecipeOutput recipeOutput, ItemStack result, int workCircles, Ingredient... ingredients) {
        ResourceLocation id = ConfluenceDelight.asResource("millstone/" + getItemName(result.getItem()));
        NonNullList<Ingredient> recipeIngredients = NonNullList.of(Ingredient.EMPTY, ingredients);
        recipeOutput.accept(id, new MillStoneRecipe(result, recipeIngredients, workCircles), null);
    }

    protected void juicerRecipe(RecipeOutput recipeOutput, ItemStack result, int cycle, ItemLike container, FluidStack fluidInput, Ingredient... ingredients) {
        ResourceLocation id = ConfluenceDelight.asResource("juicer/" + getItemName(result.getItem()));
        NonNullList<Ingredient> recipeIngredients = NonNullList.of(Ingredient.EMPTY, ingredients);
        recipeOutput.accept(id, new JuicerRecipe(result, container, cycle, fluidInput, recipeIngredients), null);
    }

    protected void blockInteractionRecipe(RecipeOutput recipeOutput, Ingredient inputItem, Block resultBlock, Block... sourceBlocks) {
        ResourceLocation id = ConfluenceDelight.asResource("block_interaction/" + getItemName(resultBlock));
        recipeOutput.accept(id, new BlockAndItemInteractionRecipe(inputItem, sourceBlocks, resultBlock), null);
    }
}
