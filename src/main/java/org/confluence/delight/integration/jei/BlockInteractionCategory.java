package org.confluence.delight.integration.jei;

import com.mojang.blaze3d.vertex.PoseStack;
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
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.recipe.BlockInteractionRecipe;
import org.joml.Vector3d;
import org.joml.Vector3f;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.confluence.terra_curio.integration.jei.ModJeiPlugin.addInput;

public class BlockInteractionCategory implements IRecipeCategory<RecipeHolder<BlockInteractionRecipe>> {
    public static final RecipeType<RecipeHolder<BlockInteractionRecipe>> RECIPE_TYPE = RecipeType.createRecipeHolderType(ConfluenceDelight.asResource("block_interaction"));
    public static final Component TITLE = Component.translatable("title.confluence_delight.block_interaction");
    private final IDrawable icon;

    public BlockInteractionCategory(IJeiHelpers jeiHelpers) {
        this.icon = jeiHelpers.getGuiHelper().createDrawableItemStack(new ItemStack(Blocks.COBBLESTONE));
    }

    @Override
    public RecipeType<RecipeHolder<BlockInteractionRecipe>> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public int getWidth() {
        return 100;
    }

    @Override
    public int getHeight() {
        return 12;
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
        addInput(builder, 5, -2, recipe.value().inputItem());
        List<ItemStack> sourceBlockStacks = Arrays.stream(recipe.value().sourceBlocks()).map(ItemStack::new).collect(Collectors.toList());
        builder.addSlot(RecipeIngredientRole.INPUT, 40, -2).addIngredientsUnsafe(sourceBlockStacks);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, -2).addItemStack(new ItemStack(recipe.value().resultBlock()));
    }
}