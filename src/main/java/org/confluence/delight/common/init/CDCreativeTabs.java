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
                        output.accept(CDBlocks.MILLSTONE_BLOCK.get());
                        output.accept(CDBlocks.JUICER_BLOCK.get());
                        output.accept(CDBlocks.SAP_COLLECTORS_BLOCK.get());
                        output.accept(CDBlocks.APRICOT_CRATE.get());
                        output.accept(CDBlocks.GRAPE_FRUIT_CRATE.get());
                        output.accept(CDBlocks.LEMON_CRATE.get());
                        output.accept(CDBlocks.PEACH_CRATE.get());
                        output.accept(CDBlocks.CHERRY_CRATE.get());
                        output.accept(CDBlocks.PLUM_CRATE.get());
                        output.accept(CDBlocks.BLOOD_ORANGE_CRATE.get());
                        output.accept(CDBlocks.DRAGON_FRUIT_CRATE.get());
                        output.accept(CDBlocks.RAMBUTAN_CRATE.get());
                        output.accept(CDBlocks.MANGO_CRATE.get());
                        output.accept(CDBlocks.BANANA_CRATE.get());
                        output.accept(CDBlocks.COCONUT_CRATE.get());
                        output.accept(CDBlocks.STAR_FRUIT_CRATE.get());
                        output.accept(CDBlocks.POMEGRANATE_CRATE.get());
                        output.accept(CDBlocks.BLACKCURRANT_CRATE.get());
                        output.accept(CDBlocks.BLOODY_MOSCATO_CRATE.get());
                        output.accept(CDBlocks.ELDERBERRY_CRATE.get());
                        output.accept(CDBlocks.PINEAPPLE_CRATE.get());
                        output.accept(CDBlocks.SPICY_PEPPER_CRATE.get());
                        CDNaturalItems.ITEMS.getEntries().forEach((item) -> output.accept(item.get()));
                        CDNaturalBlocks.BLOCK_ITEMS.getEntries().forEach((block) -> output.accept(block.get().asItem()));
                        CDItems.ITEMS.getEntries().forEach((item) -> output.accept(item.get()));
                        CDMaterialItems.ITEMS.getEntries().forEach((item) -> output.accept(item.get()));
                        CDFoodItems.ITEMS.getEntries().forEach((item) -> output.accept(item.get()));
                        CDFoodBlocks.BLOCKS.getEntries().forEach((block) -> output.accept(block.get().asItem()));
                    })
                    .withTabsAfter(ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath("confluence", "tools")))
                    .withTabsBefore(ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath("confluence", "food_and_potions")))
                    .build());
}
