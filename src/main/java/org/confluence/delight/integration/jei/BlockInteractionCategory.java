package org.confluence.delight.integration.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Blocks;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.recipe.BlockInteractionRecipe;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.confluence.terra_curio.integration.jei.ModJeiPlugin.addInput;

public class BlockInteractionCategory implements IRecipeCategory<RecipeHolder<BlockInteractionRecipe>> {
    public static final RecipeType<RecipeHolder<BlockInteractionRecipe>> RECIPE_TYPE = RecipeType.createRecipeHolderType(ConfluenceDelight.asResource("block_interaction"));
    public static final Component TITLE = Component.translatable("title.confluence_delight.block_interaction");
    ResourceLocation background = ConfluenceDelight.asResource("textures/gui/jei/juicer/juicer.png");
    private final IDrawable icon;

    private final int WIDTH = 158;
    private final int HEIGHT = 84;

    public BlockInteractionCategory(IJeiHelpers jeiHelpers) {
        this.icon = jeiHelpers.getGuiHelper().createDrawableItemStack(new ItemStack(Blocks.COBBLESTONE));
    }

    @Override
    public RecipeType<RecipeHolder<BlockInteractionRecipe>> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
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
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<BlockInteractionRecipe> recipe, IFocusGroup focuses) {
        addInput(builder, 42, 27, recipe.value().inputItem());
        List<ItemStack> sourceBlockStacks = Arrays.stream(recipe.value().sourceBlocks()).map(ItemStack::new).collect(Collectors.toList());
        builder.addSlot(RecipeIngredientRole.INPUT, 62, 27).addIngredientsUnsafe(sourceBlockStacks);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 102, 27).addItemStack(new ItemStack(recipe.value().resultBlock()));
    }

    @Override
    public void draw(RecipeHolder<BlockInteractionRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        guiGraphics.blit(background, 0, 0, 0, 0, WIDTH, HEIGHT);
    }
}
