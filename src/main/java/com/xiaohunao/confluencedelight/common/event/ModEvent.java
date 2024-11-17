package com.xiaohunao.confluencedelight.common.event;

import com.xiaohunao.confluencedelight.ConfluenceDelight;
import com.xiaohunao.confluencedelight.common.init.ModItems;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.HashMap;

@EventBusSubscriber(modid = ConfluenceDelight.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEvent {
    @SubscribeEvent
    public static void commandSetup(FMLCommonSetupEvent event){
        ModItems.consumptionProbabilityMap = new HashMap<>();
        //100 -> 1.0F
        ModItems.consumptionProbabilityMap.put(Items.ICE, 5.0D);
        ModItems.consumptionProbabilityMap.put(Items.PACKED_ICE, 7.8D);
        ModItems.consumptionProbabilityMap.put(Items.BLUE_ICE, 10.2D);
    }
}
