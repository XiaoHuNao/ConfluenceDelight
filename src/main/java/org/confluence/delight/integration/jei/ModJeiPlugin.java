package org.confluence.delight.integration.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDRecipes;
import org.confluence.mod.Confluence;

@JeiPlugin
public class ModJeiPlugin implements IModPlugin {
    public static final ResourceLocation UID = Confluence.asResource("jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        registration.addRecipeCategories(new PickleJarsCategory(jeiHelpers));
        registration.addRecipeCategories(new MillStoneCategory(jeiHelpers));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ClientLevel level = Minecraft.getInstance().level;
        if (level == null) return;
        RecipeManager recipeManager = level.getRecipeManager();
        registration.addRecipes(PickleJarsCategory.RECIPE_TYPE, recipeManager.getAllRecipesFor(CDRecipes.PICKLE_JARS_TYPE.get()));
        registration.addRecipes(MillStoneCategory.RECIPE_TYPE, recipeManager.getAllRecipesFor(CDRecipes.MILLSTONE_TYPE.get()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(CDBlocks.PICKLE_JARS_BLOCK.toStack(), PickleJarsCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(CDBlocks.MILLSTONE_BLOCK.toStack(), MillStoneCategory.RECIPE_TYPE);
    }
}
