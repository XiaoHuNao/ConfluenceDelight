package org.confluence.delight.integration.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.recipe.BlockInteractionRecipe;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.confluence.mod.integration.jei.ModJeiPlugin.addInput;


public class BlockInteractionCategory implements IRecipeCategory<RecipeHolder<BlockInteractionRecipe>> {
    public static final RecipeType<RecipeHolder<BlockInteractionRecipe>> RECIPE_TYPE = RecipeType.createRecipeHolderType(ConfluenceDelight.asResource("block_interaction"));
    public static final Component TITLE = Component.translatable("title.confluence_delight.block_interaction");
    private final IDrawable icon;
    private final Rectangle sourceBlockRenderArea = new Rectangle();
    private static final long ANIMATION_INTERVAL = 1100L;
    private static final int SOURCE_BLOCK_RENDER_X = 37;
    private static final int BASE_RENDER_Y = 0;
    private static final int BASE_RENDER_SIZE = 20;
    private static final float BASE_SCALE = 20.0F;

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
        return 20;
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
        addInput(builder, 5, 2, recipe.value().inputItem());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 75, 2).addItemStack(new ItemStack(recipe.value().resultBlock()));
    }

    @Override
    public void draw(RecipeHolder<BlockInteractionRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        final Minecraft mc = Minecraft.getInstance();
        final BlockRenderDispatcher blockRenderDispatcher = mc.getBlockRenderer();
        final PoseStack poseStack = guiGraphics.pose();
        final List<Block> sourceBlocks = Arrays.asList(recipe.value().sourceBlocks());
        sourceBlockRenderArea.setBounds(SOURCE_BLOCK_RENDER_X, BASE_RENDER_Y, BASE_RENDER_SIZE, BASE_RENDER_SIZE);
        poseStack.pushPose();
        {
            if (!sourceBlocks.isEmpty()) {
                final int index = (int) ((System.currentTimeMillis() / ANIMATION_INTERVAL) % sourceBlocks.size());
                final Block currentBlock = sourceBlocks.get(index);
                renderBlock(blockRenderDispatcher, poseStack, currentBlock, SOURCE_BLOCK_RENDER_X);
            }
        }
        poseStack.popPose();
    }

    private void renderBlock(BlockRenderDispatcher dispatcher, PoseStack poseStack, Block block, int x) {
        poseStack.pushPose();
        {
            poseStack.translate(x, BlockInteractionCategory.BASE_RENDER_Y, 100);
            poseStack.scale(BlockInteractionCategory.BASE_SCALE, BlockInteractionCategory.BASE_SCALE, BlockInteractionCategory.BASE_SCALE);
            poseStack.translate(0.5D, 0.5D, 0.5D);
            poseStack.mulPose(Axis.XP.rotationDegrees(-12));
            poseStack.mulPose(Axis.YP.rotationDegrees(22));
            poseStack.mulPose(Axis.ZP.rotationDegrees(180));
            poseStack.translate(-0.5D, -0.5D, -0.5D);
            dispatcher.renderSingleBlock(block.defaultBlockState(), poseStack, Minecraft.getInstance().renderBuffers().bufferSource(), LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.cutout());
        }
        poseStack.popPose();
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, RecipeHolder<BlockInteractionRecipe> recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        List<Block> sourceBlocks = Arrays.asList(recipe.value().sourceBlocks());
        if (!sourceBlocks.isEmpty() && sourceBlockRenderArea.contains(mouseX, mouseY)) {
            int index = (int) ((System.currentTimeMillis() / ANIMATION_INTERVAL) % sourceBlocks.size());
            Block currentBlock = sourceBlocks.get(index);
            tooltip.add(Component.translatable(currentBlock.getDescriptionId()));
        }
    }
}
