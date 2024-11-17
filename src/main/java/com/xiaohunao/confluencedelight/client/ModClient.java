package com.xiaohunao.confluencedelight.client;

import com.xiaohunao.confluencedelight.ConfluenceDelight;
import com.xiaohunao.confluencedelight.client.container.menu.FridgeMenu;
import com.xiaohunao.confluencedelight.client.container.screen.FridgeScreen;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModClient {
    public static class ModMenuTypes{
        public static final DeferredRegister<MenuType<?>> MENU_TYPES =
                DeferredRegister.create(Registries.MENU, ConfluenceDelight.MODID);

        public static final Supplier<MenuType<FridgeMenu>> FRIDGE =
                MENU_TYPES.register("fridge",
                        () -> IMenuTypeExtension.create(FridgeMenu::new));
    }

    @EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
    public static class ScreenRegister {
        @SubscribeEvent
        private static void registerScreens(RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.FRIDGE.get(), FridgeScreen::new);
        }
    }
}
