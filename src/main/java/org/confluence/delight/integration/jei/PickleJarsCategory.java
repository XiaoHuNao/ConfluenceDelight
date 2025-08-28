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
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.neoforged.neoforge.fluids.FluidStack;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.StartupConfigs;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.recipe.PickleJarsRecipe;

import static org.confluence.mod.integration.jei.ModJeiPlugin.addInput;

public class PickleJarsCategory implements IRecipeCategory<RecipeHolder<PickleJarsRecipe>> {
    public static final RecipeType<RecipeHolder<PickleJarsRecipe>> RECIPE_TYPE = RecipeType.createRecipeHolderType(ConfluenceDelight.asResource("pickle_jars"));
    public static final Component TITLE = Component.translatable("title.confluence_delight.pickle_jars");
    private final IDrawable icon;
    ResourceLocation OpenCoverGround = ResourceLocation.fromNamespaceAndPath(ConfluenceDelight.MODID, "textures/gui/jei/pickle_jars/pickle_jars_background_0.png");
    ResourceLocation CloseCoverGround = ResourceLocation.fromNamespaceAndPath(ConfluenceDelight.MODID, "textures/gui/jei/pickle_jars/pickle_jars_background_1.png");

    public PickleJarsCategory(IJeiHelpers jeiHelpers) {
        this.icon = jeiHelpers.getGuiHelper().createDrawableItemStack(CDBlocks.PICKLE_JARS_BLOCK.toStack());
    }

    @Override
    public RecipeType<RecipeHolder<PickleJarsRecipe>> getRecipeType() {
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
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<PickleJarsRecipe> recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> ingredients = recipe.value().getIngredients();
        FluidStack fluidStack = recipe.value().getRequiredFluid();
        int size = ingredients.size();
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
        //FermentedItemInput
        if (recipe.value().isFermentation()) {
            builder.addSlot(RecipeIngredientRole.INPUT, 17, 0)
                    .addItemStack(recipe.value().getFermentedItems())
                    .addRichTooltipCallback((recipeSlotView, tooltip) -> tooltip.add(Component.translatable("jei.confluence_delight.info.pickle_jars.fermented_item")));
        }
        //FluidInput
        CDJeiPlugin.renderFluid(builder, fluidStack, StartupConfigs.FLUID_CAPACITY.get());
        //Output
        builder.addSlot(RecipeIngredientRole.OUTPUT, 124, 34)
                .addItemStack(recipe.value().getResultItem(null));
    }

    @Override
    public void draw(RecipeHolder<PickleJarsRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        if (recipe.value().getCover()) {
            guiGraphics.blit(CloseCoverGround, 0, 0, 0, 0, CDJeiPlugin.DefaultWeight, CDJeiPlugin.DefaultHeight);
        } else {
            guiGraphics.blit(OpenCoverGround, 0, 0, 0, 0, CDJeiPlugin.DefaultWeight, CDJeiPlugin.DefaultHeight);
        }
        int craftTimeTicks = recipe.value().getCraftTime();
        if (recipe.value().isFermentation() && Screen.hasShiftDown()) {
            craftTimeTicks = (craftTimeTicks + 1) / 2;
        }
        Component timeText = Component.translatable("jei.confluence_delight.info.pickle_jars.crafttime", craftTimeTicks);
        guiGraphics.pose().pushPose();
        float scale = 0.7f;
        guiGraphics.pose().scale(scale, scale, 1.0f);
        int x = (int) (90 / scale);
        int y = (int) (55 / scale);
        guiGraphics.drawString(Minecraft.getInstance().font, timeText, x, y, 0xFFFFFF, true);
        guiGraphics.pose().popPose();
        if (recipe.value().isFermentation()) {
            int textWidth = Minecraft.getInstance().font.width(timeText);
            int textHeight = Minecraft.getInstance().font.lineHeight;
            double scaledMouseX = mouseX / scale;
            double scaledMouseY = mouseY / scale;
            if (scaledMouseX >= x && scaledMouseX <= x + textWidth && scaledMouseY >= y && scaledMouseY <= y + textHeight) {
                Component tooltip = Component.translatable("jei.confluence_delight.info.pickle_jars.fermented_item");
                guiGraphics.renderTooltip(Minecraft.getInstance().font, tooltip, (int) mouseX, (int) mouseY);
            }
        }
        CDJeiPlugin.drawFluidStack(guiGraphics, 0, 8);
    }
}
