package com.xiaohunao.confluencedelight.common.init;

import com.xiaohunao.confluencedelight.ConfluenceDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ConfluenceDelight.MODID);

    public static final DeferredHolder<CreativeModeTab,CreativeModeTab> TAB_FARMERS_DELIGHT = CREATIVE_TABS.register(ConfluenceDelight.MODID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.terra_delight"))
                    .icon(() -> new ItemStack(ModBlocks.STOVE.get()))
                    .displayItems((parameters, output) -> {
                        ModItems.ITEMS.getEntries().forEach((item) -> output.accept(item.get()));
                        com.xiaohunao.confluencedelight.common.init.ModBlocks.BLOCKS.getEntries().forEach((block) -> output.accept(block.get()));
                    })
                    .build());
}
