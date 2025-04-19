package com.xiaohunao.confluencedelight.client;

import com.xiaohunao.confluencedelight.ConfluenceDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModClient {
    public static class ModMenuTypes {
        public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, ConfluenceDelight.MODID);

//        @EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
//        public static class ScreenRegister {
//        }
    }
}
