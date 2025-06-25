package org.confluence.delight.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.mod.common.init.block.FunctionalBlocks;

public class CDCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ConfluenceDelight.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB_FARMERS_DELIGHT = CREATIVE_TABS.register(ConfluenceDelight.MODID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.terra_delight"))
                    .icon(() -> new ItemStack(FunctionalBlocks.COOKING_POT.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(CDBlocks.PICKLE_JARS_BLOCK.get());
                        CDItems.ITEMS.getEntries().forEach((item) -> output.accept(item.get()));
                        CDMaterialItems.ITEMS.getEntries().forEach((item) -> output.accept(item.get()));
                        CDNaturalItems.ITEMS.getEntries().forEach((item) -> output.accept(item.get()));
                        CDNaturalBlocks.BLOCK_ITEMS.getEntries().forEach((block) -> output.accept(block.get().asItem()));
                        CDFoodItems.ITEMS.getEntries().forEach((item) -> output.accept(item.get()));
                        CDFoodBlocks.BLOCKS.getEntries().forEach((block) -> output.accept(block.get().asItem()));
                    })
                    .withTabsAfter(ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath("confluence", "tools")))
                    .withTabsBefore(ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath("confluence", "food_and_potions")))
                    .build());
}
