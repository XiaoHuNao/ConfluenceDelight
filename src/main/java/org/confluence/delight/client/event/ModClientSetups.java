package org.confluence.delight.client.event;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDFluids;
import org.joml.Vector3f;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ModClientSetups {
    static final IClientFluidTypeExtensions WINE_CLIENT_EXTENSIONS = new IClientFluidTypeExtensions() {
        private static final ResourceLocation STILL = ConfluenceDelight.asResource("block/fluid/wine_still");
        private static final ResourceLocation FLOWING = ConfluenceDelight.asResource("block/fluid/wine_flowing");
        private static final Vector3f FOG_COLOR = new Vector3f(1.0F, 1.0F, 0.0F);

        @Override
        public ResourceLocation getStillTexture() {
            return STILL;
        }

        @Override
        public ResourceLocation getFlowingTexture() {
            return FLOWING;
        }

        @Override
        public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
            return FOG_COLOR;
        }

        @Override
        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
            RenderSystem.setShaderFogStart(0.125F);
            RenderSystem.setShaderFogEnd(5.0F);
        }
    };

    static final IClientFluidTypeExtensions BRINE_CLIENT_EXTENSIONS = new IClientFluidTypeExtensions() {
        private static final ResourceLocation STILL = ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_still");
        private static final ResourceLocation FLOWING = ResourceLocation.fromNamespaceAndPath("minecraft", "block/water_flow");
        private static final Vector3f FOG_COLOR = new Vector3f(0.3F, 0.5F, 0.9F);

        @Override
        public ResourceLocation getStillTexture() {
            return STILL;
        }

        @Override
        public ResourceLocation getFlowingTexture() {
            return FLOWING;
        }

        @Override
        public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
            return FOG_COLOR;
        }

        @Override
        public int getTintColor() {
            return 0xFFB0D0FF;
        }

        @Override
        public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
            RenderSystem.setShaderFogStart(0.125F);
            RenderSystem.setShaderFogEnd(5.0F);
        }
    };


    static void setRenderLayers() {
        RenderType translucent = RenderType.translucent();
        ItemBlockRenderTypes.setRenderLayer(CDFluids.WINE.fluid().get(), translucent);
        ItemBlockRenderTypes.setRenderLayer(CDFluids.WINE.flowing().get(), translucent);
        ItemBlockRenderTypes.setRenderLayer(CDFluids.BRINE.fluid().get(), translucent);
        ItemBlockRenderTypes.setRenderLayer(CDFluids.BRINE.flowing().get(), translucent);
        RenderType cutoutMipped = RenderType.cutoutMipped();
        ItemBlockRenderTypes.setRenderLayer(CDBlocks.PICKLE_JARS_BLOCK.get(), cutoutMipped);
    }
}
