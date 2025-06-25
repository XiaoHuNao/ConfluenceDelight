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
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.CDFluids;
import org.confluence.delight.common.init.CDFoodItems;
import org.confluence.delight.common.init.CDMaterialItems;
import org.confluence.delight.common.init.CDTags;
import org.confluence.delight.common.recipe.PickleJarsRecipe;
import org.confluence.lib.common.data.gen.AbstractRecipeProvider;
import org.confluence.lib.common.recipe.AmountIngredient;
import org.confluence.mod.Confluence;
import org.confluence.mod.common.init.item.ConsumableItems;
import org.confluence.mod.common.init.item.FoodItems;
import org.confluence.mod.common.init.item.MaterialItems;
import org.confluence.mod.common.init.item.PotionItems;
import org.confluence.mod.common.recipe.CookingPotRecipe;
import org.confluence.mod.common.recipe.HeavyWorkBenchRecipe;
import org.confluence.terra_curio.common.init.TCItems;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.List;
import java.util.Map;
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
        cookingPot(recipeOutput, CDFoodItems.CHICKEN_HOT_POT.toStack(), bowlContainer, stoveHeatSource, 500, AmountIngredient.of(16, FoodItems.SPICY_PEPPER), AmountIngredient.of(4, ModItems.CHICKEN_CUTS.get()), AmountIngredient.of(3, CDFoodItems.POTATO_PIECE));
        cookingPot(recipeOutput, CDFoodItems.ROYAL_GUMMY.toStack(), Ingredient.EMPTY, CookingPotRecipe.HeatSourcePredicate.EMPTY, 200, Ingredient.of(Items.HONEY_BOTTLE), Ingredient.of(TCItems.ROYAL_GEL.get()), Ingredient.of(MaterialItems.AMBER));
        cookingPot(recipeOutput, CDFoodItems.ATLANTIS_TSUNAMI.toStack(), Ingredient.of(PotionItems.MUG), blueIceHeatSource, 300, Ingredient.of(MaterialItems.HEIM), Ingredient.of(Items.SUGAR), Ingredient.of(CDTags.Items.FRUIT_LEMON), Ingredient.of(PotionItems.ALE));
        cookingPot(recipeOutput, CDFoodItems.DONKEY_MEAT_FIRE.toStack(), Ingredient.EMPTY, stoveHeatSource, 400, Ingredient.of(CDFoodItems.RAW_DONKEY_MEAT), Ingredient.of(Items.BREAD));
        cookingPot(recipeOutput, CDFoodItems.HORSE_MEAT_SASHIMI.toStack(), Ingredient.EMPTY, stoveHeatSource, 400, Ingredient.of(CDFoodItems.RAW_HORSE_MEAT));
        cookingPot(recipeOutput, CDFoodItems.BLACK_LUCK.toStack(), mugContainer, blueIceHeatSource, 200, Ingredient.of(Items.ICE), Ingredient.of(Items.MILK_BUCKET), Ingredient.of(FoodItems.BLACKCURRANT));
        cookingPot(recipeOutput, CDFoodItems.WHITE_DAWN.toStack(), mugContainer, blueIceHeatSource, 200, Ingredient.of(Items.ICE), Ingredient.of(Items.MILK_BUCKET), Ingredient.of(FoodItems.BANANA));
        cookingPot(recipeOutput, CDFoodItems.SWEET_CARROT_CUBES.toStack(), mugContainer, CookingPotRecipe.HeatSourcePredicate.EMPTY, 100, Ingredient.of(Items.CARROT), Ingredient.of(Items.SUGAR), Ingredient.of(FoodItems.SPICY_PEPPER));
        cookingPot(recipeOutput, CDFoodItems.BUTTER.toStack(), Ingredient.EMPTY, stoveHeatSource, 50, Ingredient.of(ModItems.MILK_BOTTLE.get()));
        cookingPot(recipeOutput, CDFoodItems.SPICY_BOMB_FISH.toStack(), bowlContainer, stoveHeatSource, 50, Ingredient.of(ConsumableItems.BOMB_FISH), Ingredient.of(FoodItems.SPICY_PEPPER), Ingredient.of(ModItems.CABBAGE.get()));
        cookingPot(recipeOutput, CDFoodItems.CRISPY_RICE_WITH_POTATOES.toStack(), bowlContainer, stoveHeatSource, 120, Ingredient.of(CDFoodItems.POTATO_PIECE), Ingredient.of(CDFoodItems.CRUSHED_CHILLI));

        //重型工作台
        heavyWorkBench(recipeOutput, "", ShapedRecipePattern.of(Map.of(
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

        //泡菜罐
        pickleJarsRecipe(recipeOutput, true, CDFoodItems.JAR_CHILI_PEPPERS.toStack(), new FluidStack(Fluids.WATER, 2000), 1000, Ingredient.of(FoodItems.SPICY_PEPPER));
        pickleJarsRecipe(recipeOutput, true, CDFoodItems.SPICY_PICKLED_FISH.toStack(), new FluidStack(CDFluids.WINE.fluid(), 2000), 1200, Ingredient.of(ItemTags.CAT_FOOD), AmountIngredient.of(10, FoodItems.SPICY_PEPPER), AmountIngredient.of(2, Items.SUGAR));

        //砧板
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.HONEY_MOONCAKES.get()), Ingredient.of(ModTags.KNIVES), FoodItems.HONEY_MOONCAKES_CHUNKS, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.EGG_YOLK_MOONCAKES.get()), Ingredient.of(ModTags.KNIVES), FoodItems.EGG_YOLK_MOONCAKES_CHUNKS, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.POTATO), Ingredient.of(ModTags.KNIVES), CDFoodItems.POTATO_PIECE.get(), 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.SPICY_PEPPER.get()), Ingredient.of(ModTags.KNIVES), CDFoodItems.CRUSHED_CHILLI.get(), 5).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.CARROT), Ingredient.of(ModTags.KNIVES), CDFoodItems.CARROT_CUBES, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.CLOUD_BREAD), Ingredient.of(ModTags.KNIVES), CDFoodItems.CLOUD_BREAD_SLICE, 2).build(recipeOutput);

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
    }

    protected void cookingPot(RecipeOutput recipeOutput, ItemStack result, Ingredient container, CookingPotRecipe.HeatSourcePredicate heatSource, int cookingTime, Ingredient... ingredients) {
        ResourceLocation id = Confluence.asResource("cooking_pot/" + getItemName(result.getItem()));
        NonNullList<Ingredient> zingredients = NonNullList.of(Ingredient.EMPTY, ingredients);
        recipeOutput.accept(id, new CookingPotRecipe(result, zingredients, container, heatSource, cookingTime), null);
    }

    protected void heavyWorkBench(RecipeOutput recipeOutput, String suffix, ShapedRecipePattern pattern, ItemStack result) {
        ResourceLocation id = Confluence.asResource("heavy_work_bench/" + getItemName(result.getItem()) + suffix);
        recipeOutput.accept(id, new HeavyWorkBenchRecipe(result, pattern), null);
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

}
