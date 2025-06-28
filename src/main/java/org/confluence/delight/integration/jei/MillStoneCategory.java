package org.confluence.delight.integration.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.recipe.MillStoneRecipe;

public class MillStoneCategory implements IRecipeCategory<RecipeHolder<MillStoneRecipe>> {
    public static final RecipeType<RecipeHolder<MillStoneRecipe>> RECIPE_TYPE = RecipeType.createRecipeHolderType(ConfluenceDelight.asResource("millstone"));
    public static final Component TITLE = Component.translatable("title.confluence_delight.millstone");
    ResourceLocation background = ConfluenceDelight.asResource("textures/gui/jei/millstone/millstone.png");
    private final IDrawable icon;

    public MillStoneCategory(IJeiHelpers jeiHelpers) {
        this.icon = jeiHelpers.getGuiHelper().createDrawableItemStack(CDBlocks.MILLSTONE_BLOCK.toStack());
    }

    @Override
    public RecipeType<RecipeHolder<MillStoneRecipe>> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public int getWidth() {
        return 158;
    }

    @Override
    public int getHeight() {
        return 84;
    }

    @Override
    public Component getTitle() {
        return TITLE;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<MillStoneRecipe> recipe, IFocusGroup focuses) {
        Ingredient ingredient1 = recipe.value().getIngredient1();
        Ingredient ingredient2 = recipe.value().getIngredient2();
        //ItemInput
        builder.addSlot(RecipeIngredientRole.INPUT, 42, 10).addIngredients(ingredient1);
        builder.addSlot(RecipeIngredientRole.INPUT, 60, 10).addIngredients(ingredient2);
        //Output
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124, 34).addItemStack(recipe.value().getResultItem(null));
    }

    @Override
    public void draw(RecipeHolder<MillStoneRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        guiGraphics.blit(background, 0, 0, 0, 0, 158, 84);
        int workCircles = recipe.value().getWorkCircles();
        Component timeText = Component.translatable("jei.confluence_delight.info.millstone.work_circles", workCircles);
        guiGraphics.pose().pushPose();
        float scale = 0.8f;
        guiGraphics.pose().scale(scale, scale, 1.0f);
        int x = (int) (92 / scale);
        int y = (int) (55 / scale);
        guiGraphics.drawString(Minecraft.getInstance().font, timeText, x, y, 0xFFFFFF, true);
        guiGraphics.pose().popPose();
    }
}
