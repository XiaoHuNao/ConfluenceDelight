package org.confluence.delight;

import com.mojang.logging.LogUtils;
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
import org.confluence.delight.common.CommonConfigs;
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
    private static final Logger LOGGER = LogUtils.getLogger();

    public static List<Consumer<ModLanguageProvider>> chineseProviders = new ArrayList<>();


    public ConfluenceDelight(IEventBus modEventBus, ModContainer modContainer) {
//        NeoForge.EVENT_BUS.register(this);
        StartupConfigs.register(modContainer);
        CommonConfigs.register(modContainer);
        if (FMLEnvironment.dist.isClient()) {
            ClientConfigs.register(modContainer);
            modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        }

        ModItems.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.BLOCK_ENTITIES.register(modEventBus);
        ModFluids.initialize();
        ModEffects.EFFECTS.register(modEventBus);
        ModClient.ModMenuTypes.MENU_TYPES.register(modEventBus);
        ModCreativeTabs.CREATIVE_TABS.register(modEventBus);
        ModRecipes.register(modEventBus);
    }

    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
