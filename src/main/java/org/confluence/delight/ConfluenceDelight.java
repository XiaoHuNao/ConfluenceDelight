package org.confluence.delight;

import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.confluence.delight.client.ModClient;
import org.confluence.delight.common.CDCommonConfigs;
import org.confluence.delight.common.data.gen.ModLanguageProvider;
import org.confluence.delight.common.init.*;
import org.confluence.mod.client.ClientConfigs;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Mod(ConfluenceDelight.MODID)
public class ConfluenceDelight {
    public static final String MODID = "confluence_delight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static List<Consumer<ModLanguageProvider>> chineseProviders = new ArrayList<>();


    public ConfluenceDelight(IEventBus modEventBus, ModContainer modContainer) {
//        NeoForge.EVENT_BUS.register(this);
        CDStartupConfigs.register(modContainer);
        CDCommonConfigs.register(modContainer);
        if (FMLEnvironment.dist.isClient()) {
            ClientConfigs.register(modContainer);
            modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        }

        CDAttachmentTypes.TYPES.register(modEventBus);

        CDItems.register(modEventBus);
        CDBlocks.register(modEventBus);
        CDFluids.initialize();
        CDRecipes.register(modEventBus);
        CDEffects.EFFECTS.register(modEventBus);
        CDSoundEvents.EVENTS.register(modEventBus);
        CDFeature.register(modEventBus);
        ModClient.ModMenuTypes.MENU_TYPES.register(modEventBus);
        CDCreativeTabs.CREATIVE_TABS.register(modEventBus);
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public static <T> ResourceKey<T> asResourceKey(ResourceKey<? extends Registry<T>> registryKey, String path) {
        return ResourceKey.create(registryKey, asResource(path));
    }

    public static String asPlainId(String path) {
        return MODID + ':' + path;
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
