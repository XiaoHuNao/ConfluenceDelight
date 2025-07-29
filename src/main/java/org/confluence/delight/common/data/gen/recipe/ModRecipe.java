package org.confluence.delight.common.data.gen.recipe;


import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.ICustomIngredient;
import net.neoforged.neoforge.fluids.FluidStack;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.*;
import org.confluence.delight.common.recipe.BlockInteractionRecipe;
import org.confluence.delight.common.recipe.JuicerRecipe;
import org.confluence.delight.common.recipe.MillStoneRecipe;
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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        Ingredient chinaBowlContainer = Ingredient.of(MaterialItems.CHINA_BOWL);
        CookingPotRecipe.HeatSourcePredicate campfireHeatSource = CookingPotRecipe.HeatSourcePredicate.builder().of(BlockTags.CAMPFIRES).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.LIT, true)).build();
        CookingPotRecipe.HeatSourcePredicate stoveHeatSource = CookingPotRecipe.HeatSourcePredicate.builder().of(ModBlocks.STOVE.get()).build();
        CookingPotRecipe.HeatSourcePredicate blueIceHeatSource = CookingPotRecipe.HeatSourcePredicate.builder().of(Blocks.BLUE_ICE).build();
        CookingPotRecipe.HeatSourcePredicate iceHeatSource = CookingPotRecipe.HeatSourcePredicate.builder().of(Blocks.ICE).build();
        cookingPot(recipeOutput, CDFoodItems.CHICKEN_HOT_POT.toStack(), bowlContainer, stoveHeatSource, 500, AmountIngredient.of(16, FoodItems.SPICY_PEPPER), AmountIngredient.of(4, ModItems.CHICKEN_CUTS.get()), AmountIngredient.of(3, CDFoodItems.POTATO_PIECE));
        cookingPot(recipeOutput, CDFoodItems.ROYAL_GUMMY.toStack(), Ingredient.EMPTY, CookingPotRecipe.HeatSourcePredicate.EMPTY, 200, Ingredient.of(Items.HONEY_BOTTLE), Ingredient.of(TCItems.ROYAL_GEL.get()), Ingredient.of(MaterialItems.AMBER));
        cookingPot(recipeOutput, CDFoodItems.ATLANTIS_TSUNAMI.toStack(), Ingredient.of(PotionItems.MUG), blueIceHeatSource, 300, Ingredient.of(MaterialItems.HEIM), Ingredient.of(Items.SUGAR), Ingredient.of(CDTags.Items.C_FRUIT_LEMON), Ingredient.of(PotionItems.ALE));
        cookingPot(recipeOutput, CDFoodItems.DONKEY_MEAT_FIRE.toStack(), Ingredient.EMPTY, stoveHeatSource, 350, Ingredient.of(CDFoodItems.RAW_DONKEY_MEAT), Ingredient.of(Items.BREAD));
        cookingPot(recipeOutput, CDFoodItems.HORSE_MEAT_SASHIMI.toStack(), Ingredient.EMPTY, stoveHeatSource, 350, Ingredient.of(CDFoodItems.RAW_HORSE_MEAT));
        cookingPot(recipeOutput, CDFoodItems.BLACK_LUCK.toStack(), mugContainer, blueIceHeatSource, 200, Ingredient.of(Items.ICE), Ingredient.of(Items.MILK_BUCKET), Ingredient.of(FoodItems.BLACKCURRANT));
        cookingPot(recipeOutput, CDFoodItems.WHITE_DAWN.toStack(), mugContainer, blueIceHeatSource, 200, Ingredient.of(Items.ICE), Ingredient.of(Items.MILK_BUCKET), Ingredient.of(FoodItems.BANANA));
        cookingPot(recipeOutput, CDFoodItems.SWEET_CARROT_CUBES.toStack(), mugContainer, CookingPotRecipe.HeatSourcePredicate.EMPTY, 100, Ingredient.of(Items.CARROT), Ingredient.of(Items.SUGAR), Ingredient.of(FoodItems.SPICY_PEPPER));
        cookingPot(recipeOutput, CDFoodItems.BUTTER.toStack(), Ingredient.EMPTY, stoveHeatSource, 50, Ingredient.of(ModItems.MILK_BOTTLE.get()));
        cookingPot(recipeOutput, CDFoodItems.SPICY_BOMB_FISH.toStack(), bowlContainer, stoveHeatSource, 50, Ingredient.of(ConsumableItems.BOMB_FISH), Ingredient.of(FoodItems.SPICY_PEPPER), Ingredient.of(ModItems.CABBAGE.get()));
        cookingPot(recipeOutput, CDFoodItems.CRISPY_RICE_WITH_POTATOES.toStack(), bowlContainer, stoveHeatSource, 120, Ingredient.of(CDFoodItems.POTATO_PIECE), Ingredient.of(CDFoodItems.CRUSHED_CHILLI));
        cookingPot(recipeOutput, CDFoodItems.BUTTER_FRIED_CLOUD_BREAD_SLICES.toStack(), Ingredient.EMPTY, stoveHeatSource, 100, Ingredient.of(CDFoodItems.CLOUD_BREAD_SLICE), Ingredient.of(CDFoodItems.BUTTER));
        cookingPot(recipeOutput, CDFoodItems.HARVEST_STEW.toStack(), bowlContainer, stoveHeatSource, 300, Ingredient.of(Items.WHEAT), Ingredient.of(Items.CARROT), Ingredient.of(Items.POTATO), Ingredient.of(Items.HONEY_BOTTLE));
        cookingPot(recipeOutput, CDFoodItems.GEMINI_LANDING_STAR.toStack(), mugContainer, iceHeatSource, 120, Ingredient.of(Items.MILK_BUCKET), Ingredient.of(CDFoodItems.VANILLA_POD), Ingredient.of(Items.COCOA_BEANS));
        cookingPot(recipeOutput, CDFoodItems.MANGO_PUDDING.toStack(), chinaBowlContainer, stoveHeatSource, 150, Ingredient.of(Items.MILK_BUCKET), Ingredient.of(Items.SUGAR), Ingredient.of(MaterialItems.GEL), Ingredient.of(CDTags.Items.C_FRUIT_MANGO));
        cookingPot(recipeOutput, CDFoodItems.BANANA_PUDDING.toStack(), chinaBowlContainer, stoveHeatSource, 150, Ingredient.of(Items.MILK_BUCKET), Ingredient.of(Items.SUGAR), Ingredient.of(MaterialItems.GEL), Ingredient.of(FoodItems.BANANA));
        cookingPot(recipeOutput, CDFoodItems.SLIME_DRAGON_PUDDING.toStack(), chinaBowlContainer, stoveHeatSource, 150, Ingredient.of(Items.HONEY_BOTTLE), Ingredient.of(Items.SUGAR), Ingredient.of(MaterialItems.GEL), Ingredient.of(Items.SLIME_BALL));

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
        heavyWorkBench(recipeOutput, "", ShapedRecipePattern.of(Map.of(
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

        //泡菜罐
        FluidStack water1000 = new FluidStack(Fluids.WATER, 1000);
        FluidStack water2000 = new FluidStack(Fluids.WATER, 2000);
        FluidStack wine1000 = new FluidStack(CDFluids.WINE.fluid(), 1000);
        FluidStack wine2000 = new FluidStack(CDFluids.WINE.fluid(), 2000);
        pickleJarsRecipe(recipeOutput, true, CDFoodItems.JAR_CHILI_PEPPERS.toStack(), water2000, 1000, Ingredient.of(FoodItems.SPICY_PEPPER));
        pickleJarsRecipe(recipeOutput, true, CDFoodItems.CHOP_BELL_PEPPER.toStack(10), wine1000, 1000, AmountIngredient.of(5, CDMaterialItems.SALT), AmountIngredient.of(10, Items.SUGAR), AmountIngredient.of(10, CDFoodItems.CRUSHED_CHILLI));

        //磨盘
        millStoneRecipe(recipeOutput, CDMaterialItems.CHILI_POWDER.toStack(), 5, Ingredient.of(FoodItems.SPICY_PEPPER));

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

        //砧板
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.HONEY_MOONCAKES.get()), Ingredient.of(ModTags.KNIVES), FoodItems.HONEY_MOONCAKES_CHUNKS, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.EGG_YOLK_MOONCAKES.get()), Ingredient.of(ModTags.KNIVES), FoodItems.EGG_YOLK_MOONCAKES_CHUNKS, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.POTATO), Ingredient.of(ModTags.KNIVES), CDFoodItems.POTATO_PIECE.get(), 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.SPICY_PEPPER.get()), Ingredient.of(ModTags.KNIVES), CDFoodItems.CRUSHED_CHILLI.get(), 5).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.CARROT), Ingredient.of(ModTags.KNIVES), CDFoodItems.CARROT_CUBES, 3).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.CLOUD_BREAD), Ingredient.of(ModTags.KNIVES), CDFoodItems.CLOUD_BREAD_SLICE, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.COOKED_SQUIRREL), Ingredient.of(ModTags.KNIVES), CDFoodItems.SQUIRREL_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.COOKED_BIRD), Ingredient.of(ModTags.KNIVES), CDFoodItems.BIRD_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.COOKED_DUCK), Ingredient.of(ModTags.KNIVES), CDFoodItems.DUCK_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FoodItems.COOKED_FROG), Ingredient.of(ModTags.KNIVES), CDFoodItems.FROG_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(CDFoodItems.COOKED_DONKEY_MEAT), Ingredient.of(ModTags.KNIVES), CDFoodItems.DONKEY_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(CDFoodItems.COOKED_HORSE_MEAT), Ingredient.of(ModTags.KNIVES), CDFoodItems.HORSE_MEAT_CHUNKS, 2).build(recipeOutput);
        CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(CDFoodItems.SQUIRREL_MEAT_CHUNKS), Ingredient.of(ModTags.KNIVES), CDFoodItems.SQUIRREL_CITATAP).build(recipeOutput);

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
    }

    protected void cookingPot(RecipeOutput recipeOutput, ItemStack result, Ingredient container, CookingPotRecipe.HeatSourcePredicate heatSource, int cookingTime, Ingredient... ingredients) {
        ResourceLocation id = Confluence.asResource("cooking_pot/" + getItemName(result.getItem()));
        NonNullList<Ingredient> zingredients = NonNullList.of(Ingredient.EMPTY, ingredients);
        recipeOutput.accept(id, new CookingPotRecipe(result, zingredients, container, heatSource, cookingTime), null);
    }

    protected void heavyWorkBench(RecipeOutput recipeOutput, String suffix, ShapedRecipePattern pattern, ItemStack result) {
        ResourceLocation id = Confluence.asResource("heavy_work_bench/" + getItemName(result.getItem()) + suffix);
        recipeOutput.accept(id, new HeavyWorkBenchRecipe(result, pattern), null);
    }    protected void pickleJarsRecipe(RecipeOutput recipeOutput, boolean cover, ItemStack result, FluidStack fluidInput, int craftTime, Ingredient... ingredients) {
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
        recipeOutput.accept(id, new BlockInteractionRecipe(inputItem, sourceBlocks, resultBlock), null);
    }

}
