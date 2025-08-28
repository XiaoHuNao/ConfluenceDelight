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
import org.confluence.delight.common.init.CDFoodItems;
import org.confluence.delight.common.init.CDTags;
import org.confluence.lib.common.data.gen.AbstractRecipeProvider;
import org.confluence.lib.common.recipe.AmountIngredient;
import org.confluence.mod.Confluence;
import org.confluence.mod.common.init.block.NatureBlocks;
import org.confluence.mod.common.init.item.ConsumableItems;
import org.confluence.mod.common.init.item.FoodItems;
import org.confluence.mod.common.init.item.MaterialItems;
import org.confluence.mod.common.init.item.PotionItems;
import org.confluence.mod.common.recipe.CookingPotRecipe;
import org.confluence.terra_curio.common.init.TCItems;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.concurrent.CompletableFuture;

public class ConfluenceRecipeProvider extends AbstractRecipeProvider {
    public ConfluenceRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
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
        cookingPot(recipeOutput, CDFoodItems.GRASS_SEED_SOUP.toStack(), bowlContainer, stoveHeatSource, 50, Ingredient.of(org.confluence.mod.common.init.item.ModItems.GRASS_SEED, org.confluence.mod.common.init.item.ModItems.HALLOWED_SEED), Ingredient.of(org.confluence.mod.common.init.item.ModItems.JUNGLE_GRASS_SEED, org.confluence.mod.common.init.item.ModItems.MUSHROOM_GRASS_SEED), Ingredient.of(org.confluence.mod.common.init.item.ModItems.CORRUPT_SEED, org.confluence.mod.common.init.item.ModItems.CRIMSON_SEED));
        cookingPot(recipeOutput, CDFoodItems.MUSHROOM_PLATTER.toStack(), bowlContainer, stoveHeatSource, 150, Ingredient.of(MaterialItems.LIFE_MUSHROOM), Ingredient.of(MaterialItems.GLOWING_MUSHROOM), Ingredient.of(MaterialItems.VICIOUS_MUSHROOM, MaterialItems.VILE_MUSHROOM));
        cookingPot(recipeOutput, CDFoodItems.BLOOD_RED_PORK_RIB_SOUP.toStack(), bowlContainer, stoveHeatSource, 200, Ingredient.of(MaterialItems.VERTEBRA), Ingredient.of(NatureBlocks.VICIOUS_MUSHROOM), Ingredient.of(ItemTags.MEAT), AmountIngredient.of(4, CDFoodItems.POTATO_PIECE));
        cookingPot(recipeOutput, CDFoodItems.COOKED_ROSEWOOD_MEAT.toStack(), Ingredient.EMPTY, stoveHeatSource, 200, Ingredient.of(CDFoodItems.RAW_ROSEWOOD_MEAT));
        cookingPot(recipeOutput, CDFoodItems.COOKED_PROLIFERATING_FLESH_AND_BLOOD.toStack(), Ingredient.EMPTY, stoveHeatSource, 200, Ingredient.of(CDFoodItems.RAW_PROLIFERATING_FLESH_AND_BLOOD));
    }

    protected void cookingPot(RecipeOutput recipeOutput, ItemStack result, Ingredient container, CookingPotRecipe.HeatSourcePredicate heatSource, int cookingTime, Ingredient... ingredients) {
        ResourceLocation id = Confluence.asResource("cooking_pot/" + getItemName(result.getItem()));
        NonNullList<Ingredient> zingredients = NonNullList.of(Ingredient.EMPTY, ingredients);
        recipeOutput.accept(id, new CookingPotRecipe(result, zingredients, container, heatSource, cookingTime), null);
    }
}
