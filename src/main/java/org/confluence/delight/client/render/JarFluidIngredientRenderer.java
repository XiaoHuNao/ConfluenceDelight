package org.confluence.delight.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.fluids.FluidStack;
import org.confluence.delight.ConfluenceDelight;

import java.util.List;

@SuppressWarnings("deprecation")
public class JarFluidIngredientRenderer implements IIngredientRenderer<FluidStack> {
    private static final ResourceLocation JAR_MASK_TEXTURE = ResourceLocation.fromNamespaceAndPath(ConfluenceDelight.MODID, "textures/gui/jei/pickle_jar_mask.png");

    public JarFluidIngredientRenderer() {}

    @Override
    public void render(GuiGraphics guiGraphics, FluidStack fluidStack) {
        render(guiGraphics, fluidStack, 0, 0);
    }

    @Override
    public void render(GuiGraphics guiGraphics, FluidStack fluidStack, int x, int y) {
        Minecraft mc = Minecraft.getInstance();
        TextureManager textureManager = mc.getTextureManager();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        textureManager.bindForSetup(JAR_MASK_TEXTURE);
        guiGraphics.blit(JAR_MASK_TEXTURE, x, y, 0, 0, 256, 256);
        RenderSystem.disableBlend();
    }

    @Override
    public List<Component> getTooltip(FluidStack ingredient, TooltipFlag tooltipFlag) {
        return List.of();
    }
}
