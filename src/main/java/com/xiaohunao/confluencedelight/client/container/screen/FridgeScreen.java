package com.xiaohunao.confluencedelight.client.container.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.xiaohunao.confluencedelight.ConfluenceDelight;
import com.xiaohunao.confluencedelight.client.container.menu.FridgeMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;

import java.awt.*;

public class FridgeScreen extends AbstractContainerScreen<FridgeMenu> {
    private static final ResourceLocation BACKGROUND_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ConfluenceDelight.MODID,
            "textures/gui/background/fridge.png");
    private static final ResourceLocation FUEL_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(ConfluenceDelight.MODID,
                    "textures/gui/ice_fuel.png");

    public FridgeScreen(FridgeMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);

    }

    public int getHeight(){
        int i = this.menu.getIceTemplate();  //max height 33
        return i % 33;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        int z = this.leftPos;
        int k = this.topPos;
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        guiGraphics.blit(BACKGROUND_TEXTURE, z, k,
                0, 0, this.imageWidth, this.imageHeight);
        int j1 = Mth.ceil(this.menu.getIceTemplateProgress() * 33.0F);
        guiGraphics.blit(FUEL_TEXTURE, z + 23, k + 17 + 33 - j1, 0, 33 - j1,
                14, j1, 14, 33);

//        ResourceLocation atlasLocation,
//        int x, int y,
//        int width, int height,
//        float uOffset, float vOffset,
//        int uWidth, int vHeight,
//        int textureWidth, int textureHeight
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.drawString(minecraft.font, "Mouse X: " + mouseX, 0, 0, Color.WHITE.getRGB());
        guiGraphics.drawString(minecraft.font, "Mouse Y: " + mouseY, 0, 10, Color.WHITE.getRGB());
        guiGraphics.drawString(minecraft.font, "Temp: " + this.menu.getIceTemplate(), 0, 20, Color.CYAN.getRGB());
        guiGraphics.drawString(minecraft.font, "TempProgress: " + this.menu.getIceTemplateProgress(), 0, 30, Color.CYAN.getRGB());
        guiGraphics.drawString(minecraft.font, "cooking: " + this.menu.getData().get(2), 0, 40, Color.CYAN.getRGB());
        guiGraphics.drawString(minecraft.font, "totalCookTime: " + this.menu.getData().get(3), 0, 50, Color.CYAN.getRGB());


        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);





    }

    @Override
    protected void slotClicked(Slot slot, int slotId, int mouseButton, ClickType type) {
        super.slotClicked(slot, slotId, mouseButton, type);
        if(slotId < 6){
            menu.clickMenuButton(minecraft.player, slotId);
            minecraft.gameMode.handleInventoryButtonClick((this.menu).containerId, slotId);

        }
    }
}
