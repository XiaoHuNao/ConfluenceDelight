package org.confluence.delight.common.init;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;

import java.util.function.Supplier;

public class CDItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ConfluenceDelight.MODID);
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(ConfluenceDelight.MODID);

    public static final DeferredHolder<Item, Item> WINE_BUCKET = register("wine_bucket", "酒桶", () -> new BucketItem(CDFluids.WINE.fluid().get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    private static DeferredHolder<Item, Item> register(final String en, final String zh, Supplier<? extends Item> item) {
        return CDMaterialItems.register(en, zh, item);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
        CDFoodItems.ITEMS.register(eventBus);
        CDMaterialItems.ITEMS.register(eventBus);
    }
}
