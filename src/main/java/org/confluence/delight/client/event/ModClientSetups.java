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
import org.confluence.delight.common.init.ModBlocks;
import org.confluence.delight.common.init.ModFluids;
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

    static void setRenderLayers() {
        RenderType translucent = RenderType.translucent();
        ItemBlockRenderTypes.setRenderLayer(ModFluids.WINE.fluid().get(), translucent);
        ItemBlockRenderTypes.setRenderLayer(ModFluids.WINE.flowing().get(), translucent);
        RenderType cutoutMipped = RenderType.cutoutMipped();
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PICKLE_JARS_BLOCK.get(), cutoutMipped);
    }
}
