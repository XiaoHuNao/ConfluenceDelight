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
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.recipe.JuicerRecipe;
import org.confluence.delight.common.recipe.MillStoneRecipe;

public class JuicerCategory implements IRecipeCategory<RecipeHolder<JuicerRecipe>> {
    public static final RecipeType<RecipeHolder<JuicerRecipe>> RECIPE_TYPE = RecipeType.createRecipeHolderType(ConfluenceDelight.asResource("juicer"));
    public static final Component TITLE = Component.translatable("title.confluence_delight.juicer");
    ResourceLocation background = ConfluenceDelight.asResource("textures/gui/jei/millstone/millstone.png");  //todo
    private final IDrawable icon;

    public JuicerCategory(IJeiHelpers jeiHelpers) {
        this.icon = jeiHelpers.getGuiHelper().createDrawableItemStack(CDBlocks.JUICER_BLOCK.toStack());
    }

    @Override
    public RecipeType<RecipeHolder<JuicerRecipe>> getRecipeType() {
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
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<JuicerRecipe> recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> ingredients = recipe.value().getIngredient();
        //ItemInput
        for (int i = 0; i < ingredients.size(); i++) {
            int x = 42 + (i % 2) * 18;
            int y = 10 + (i / 2) * 18;

            builder.addSlot(RecipeIngredientRole.INPUT, x, y)
                    .addIngredients(ingredients.get(i));
        }
        //Output
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124, 34).addItemStack(recipe.value().getResultItem(null));
    }

    @Override
    public void draw(RecipeHolder<JuicerRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
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
