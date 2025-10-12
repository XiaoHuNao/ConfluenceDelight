package org.confluence.delight.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.neoforged.neoforge.fluids.FluidStack;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDRecipes;
import org.confluence.mod.Confluence;

@JeiPlugin
public class CDJeiPlugin implements IModPlugin {
    public static final ResourceLocation UID = Confluence.asResource("jei_plugin");
    public static final ResourceLocation FLUID_STACK = ConfluenceDelight.asResource("textures/gui/jei/fluid_stack.png");
    public static final int DefaultWeight = 158;
    public static final int DefaultHeight = 84;

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        registration.addRecipeCategories(new PickleJarsCategory(jeiHelpers));
        registration.addRecipeCategories(new MillStoneCategory(jeiHelpers));
        registration.addRecipeCategories(new JuicerCategory(jeiHelpers));
        registration.addRecipeCategories(new BlockAndItemInteractionCategory(jeiHelpers));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) return;
        RecipeManager recipeManager = level.getRecipeManager();
        registration.addRecipes(PickleJarsCategory.RECIPE_TYPE, recipeManager.getAllRecipesFor(CDRecipes.PICKLE_JARS_TYPE.get()));
        registration.addRecipes(MillStoneCategory.RECIPE_TYPE, recipeManager.getAllRecipesFor(CDRecipes.MILLSTONE_TYPE.get()));
        registration.addRecipes(JuicerCategory.RECIPE_TYPE, recipeManager.getAllRecipesFor(CDRecipes.JUICER_TYPE.get()));
        registration.addRecipes(BlockAndItemInteractionCategory.RECIPE_TYPE, recipeManager.getAllRecipesFor(CDRecipes.BLOCK_AND_ITEM_INTERACTION_TYPE.get()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(CDBlocks.PICKLE_JARS_BLOCK.toStack(), PickleJarsCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(CDBlocks.MILLSTONE_BLOCK.toStack(), MillStoneCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(CDBlocks.JUICER_BLOCK.toStack(), JuicerCategory.RECIPE_TYPE);
    }

    public static void drawFluidStack(GuiGraphics guiGraphics, int x, int y) {
        guiGraphics.blit(FLUID_STACK, x, y, 0, 0, 18, 66, 18, 66);
    }

    public static void renderFluid(IRecipeLayoutBuilder builder, FluidStack fluidStack, int maxAmount) {
        int amount = fluidStack.getAmount();
        int maxHeight = 64;
        int height = Math.max(1, (int) ((long) amount * maxHeight / maxAmount));
        height = Math.min(height, maxHeight);
        int y = 69 - height + 4;
        builder.addSlot(RecipeIngredientRole.INPUT, 1, y)
                .setFluidRenderer(amount, false, 16, height)
                .addIngredient(NeoForgeTypes.FLUID_STACK, fluidStack);
    }
}
