package com.xiaohunao.confluencedelight;

import com.mojang.logging.LogUtils;
import com.xiaohunao.confluencedelight.client.ModClient;
import com.xiaohunao.confluencedelight.common.data.gen.ModLanguageProvider;
import com.xiaohunao.confluencedelight.common.init.ModBlocks;
import com.xiaohunao.confluencedelight.common.init.ModCreativeTabs;
import com.xiaohunao.confluencedelight.common.init.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
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

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.ENTITIES.register(modEventBus);
        ModClient.ModMenuTypes.MENU_TYPES.register(modEventBus);
        ModCreativeTabs.CREATIVE_TABS.register(modEventBus);
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
