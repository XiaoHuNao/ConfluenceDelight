package org.confluence.delight.client.event;


import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.CDFluids;

@EventBusSubscriber(value = Dist.CLIENT, modid = ConfluenceDelight.MODID)
public class ModClientEvents {

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(ModClientSetups.WINE_CLIENT_EXTENSIONS, CDFluids.WINE.type());
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ModClientSetups.setRenderLayers();
        });
    }
}
