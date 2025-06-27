package org.confluence.delight.common.event;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.StartupConfigs;
import org.confluence.delight.common.CommonConfigs;
import org.confluence.delight.common.block.crafting.SapCollectorsBlock;

@EventBusSubscriber(modid = ConfluenceDelight.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEvent {
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CommonConfigs.onLoad();
            StartupConfigs.onLoad();
        });
        DispenserBlock.registerBehavior(Items.GLASS_BOTTLE, new SapCollectorsBlock.SapCollectorDispenseBehavior());
    }
}
