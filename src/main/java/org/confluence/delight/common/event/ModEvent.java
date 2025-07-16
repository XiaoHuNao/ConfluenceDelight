package org.confluence.delight.common.event;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.RegisterCauldronFluidContentEvent;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.StartupConfigs;
import org.confluence.delight.common.CommonConfigs;
import org.confluence.delight.common.block.common.BrineCauldronBlock;
import org.confluence.delight.common.block.common.SaltCauldronBlock;
import org.confluence.delight.common.block.common.WineCauldronBlock;
import org.confluence.delight.common.block.function.SapCollectorsBlock;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDFluids;
import org.confluence.delight.common.init.CDItems;

import java.util.Map;

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

    @SubscribeEvent
    public static void loadComplete(FMLLoadCompleteEvent event) {
        event.enqueueWork(() -> {
            CauldronInteraction.INTERACTIONS.values().forEach(map -> {
                Map<Item, CauldronInteraction> interactionMap = map.map();
                interactionMap.put(CDItems.WINE_BUCKET.get(), WineCauldronBlock.FILL_WINE);
                interactionMap.put(CDItems.BRINE_BUCKET.get(), BrineCauldronBlock.FILL_BRINE);
                interactionMap.put(CDBlocks.SALT_BLOCK.get().asItem(), SaltCauldronBlock.FILL_SALT);
            });
        });
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, CDBlocks.PICKLE_JARS_BLOCK_ENTITY.get(), (pickleJarsBlockEntity, side) -> pickleJarsBlockEntity.fluidTank);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, CDBlocks.JUICER_BLOCK_ENTITY.get(), (juicerBlockEntity, side) -> juicerBlockEntity.fluidTank);
    }

    @SubscribeEvent
    public static void registerCauldronFluidContent(RegisterCauldronFluidContentEvent event) {
        event.register(CDBlocks.WINE_CAULDRON.get(), CDFluids.WINE.fluid().get(), FluidType.BUCKET_VOLUME, null);
        event.register(CDBlocks.BRINE_CAULDRON.get(), CDFluids.BRINE.fluid().get(), FluidType.BUCKET_VOLUME, null);
    }
}
