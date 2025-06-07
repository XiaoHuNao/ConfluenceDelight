package org.confluence.delight.integration.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.neoforge.NeoForgeTypes;
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
import net.neoforged.neoforge.fluids.FluidStack;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.client.render.JarFluidIngredientRenderer;
import org.confluence.delight.common.init.ModBlocks;
import org.confluence.delight.common.recipe.PickleJarsRecipe;

import static org.confluence.terra_curio.integration.jei.ModJeiPlugin.addInput;

@SuppressWarnings("deprecation")
public class PickleJarsCategory implements IRecipeCategory<RecipeHolder<PickleJarsRecipe>> {
    public static final RecipeType<RecipeHolder<PickleJarsRecipe>> RECIPE_TYPE = RecipeType.createRecipeHolderType(ConfluenceDelight.asResource("pickle_jars"));
    public static final Component TITLE = Component.translatable("title.confluence_delight.pickle_jars");
    private final JarFluidIngredientRenderer fluidRenderer = new JarFluidIngredientRenderer();
    private final IDrawable icon;
    private final IDrawable background;

    public PickleJarsCategory(IJeiHelpers jeiHelpers) {
        ResourceLocation backGround = ResourceLocation.fromNamespaceAndPath(ConfluenceDelight.MODID, "textures/gui/jei/pickle_jars_background.png");
        this.background = jeiHelpers.getGuiHelper().createDrawable(backGround, 0, 0, 158, 84);
        this.icon = jeiHelpers.getGuiHelper().createDrawableItemStack(ModBlocks.PICKLE_JARS_BLOCK.toStack());
    }

    @Override
    public RecipeType<RecipeHolder<PickleJarsRecipe>> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return TITLE;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<PickleJarsRecipe> recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> ingredients = recipe.value().getIngredients();
        int size = ingredients.size();
        FluidStack fluidStack = recipe.value().getRequiredFluid();
        int fluidAmount = fluidStack.getAmount();
        //ItemInput
        if (size == 1) {
            addInput(builder, 47, 28, ingredients.getFirst());
        } else if (size == 2) {
            addInput(builder, 47, 28, ingredients.getFirst());
            addInput(builder, 40, 45, ingredients.get(1));
        } else if (size == 3) {
            addInput(builder, 47, 28, ingredients.getFirst());
            addInput(builder, 40, 45, ingredients.get(1));
            addInput(builder, 54, 45, ingredients.get(2));
        }
        //FluidInput
        builder.addSlot(RecipeIngredientRole.INPUT, 0, 0)
                .setFluidRenderer(fluidAmount, false, 16, 16)
                .addIngredient(NeoForgeTypes.FLUID_STACK, fluidStack);
        //Output
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124, 34)
                .addItemStack(recipe.value().getResultItem(null));
    }

    @Override
    public void draw(RecipeHolder<PickleJarsRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        fluidRenderer.render(guiGraphics, recipe.value().getRequiredFluid(), 0, 0);
        int craftTimeTicks = recipe.value().getCraftTime();
        Component timeText = Component.translatable("jei.confluence_delight.info.pickle_jars.crafttime", craftTimeTicks);
        guiGraphics.pose().pushPose();
        float scale = 0.7f;
        guiGraphics.pose().scale(scale, scale, 1.0f);
        int x = (int) (90 / scale);
        int y = (int) (55 / scale);
        guiGraphics.drawString(Minecraft.getInstance().font, timeText, x, y, 0xFFFFFFFF, true);
        guiGraphics.pose().popPose();
    }
}
