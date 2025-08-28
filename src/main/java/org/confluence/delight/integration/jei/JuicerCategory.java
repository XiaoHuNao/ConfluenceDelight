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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.fluids.FluidStack;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.StartupConfigs;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.recipe.JuicerRecipe;

import static org.confluence.mod.integration.jei.ModJeiPlugin.addInput;

public class JuicerCategory implements IRecipeCategory<RecipeHolder<JuicerRecipe>> {
    public static final RecipeType<RecipeHolder<JuicerRecipe>> RECIPE_TYPE = RecipeType.createRecipeHolderType(ConfluenceDelight.asResource("juicer"));
    public static final Component TITLE = Component.translatable("title.confluence_delight.juicer");
    ResourceLocation background = ConfluenceDelight.asResource("textures/gui/jei/juicer/juicer.png");
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
        return CDJeiPlugin.DefaultWeight;
    }

    @Override
    public int getHeight() {
        return CDJeiPlugin.DefaultHeight;
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
        NonNullList<Ingredient> ingredients = recipe.value().getIngredients();
        FluidStack fluidStack = recipe.value().getFluid();
        int size = ingredients.size();
        if (size == 1) {
            addInput(builder, 42, 27, ingredients.getFirst());
        } else if (size == 2) {
            addInput(builder, 42, 27, ingredients.getFirst());
            addInput(builder, 60, 27, ingredients.get(1));
        } else if (size == 3) {
            addInput(builder, 42, 27, ingredients.getFirst());
            addInput(builder, 60, 27, ingredients.get(1));
            addInput(builder, 51, 45, ingredients.get(2));
        }
        Item container = recipe.value().getContainer().asItem();
        addInput(builder, 89, 48, Ingredient.of(container));
        CDJeiPlugin.renderFluid(builder, fluidStack, StartupConfigs.FLUID_CAPACITY.get());
        //Output
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124, 34).addItemStack(recipe.value().getResultItem(null));
    }

    @Override
    public void draw(RecipeHolder<JuicerRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        guiGraphics.blit(background, 0, 0, 0, 0, CDJeiPlugin.DefaultWeight, CDJeiPlugin.DefaultHeight);
        int cycle = recipe.value().getCycle();
        Component timeText = Component.translatable("jei.confluence_delight.info.juicer.cycle", cycle);
        guiGraphics.pose().pushPose();
        float scale = 0.8f;
        guiGraphics.pose().scale(scale, scale, 1.0f);
        int x = (int) (92 / scale);
        int y = (int) (30 / scale);
        guiGraphics.drawString(Minecraft.getInstance().font, timeText, x, y, 0xFFFFFF, true);
        guiGraphics.pose().popPose();
        CDJeiPlugin.drawFluidStack(guiGraphics, 0, 8);
    }
}
