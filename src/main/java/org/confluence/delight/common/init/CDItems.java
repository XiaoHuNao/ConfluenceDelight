package org.confluence.delight.common.init;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;

import java.util.function.Supplier;

import static org.confluence.delight.ConfluenceDelight.chineseProviders;

public class CDItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ConfluenceDelight.MODID);
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(ConfluenceDelight.MODID);

    public static final DeferredItem<Item> WINE_BUCKET = register("wine_bucket", "酒桶", () -> new BucketItem(CDFluids.WINE.fluid().get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> FUNGAL_YEAST = register("fungal_yeast", "菌曲", () -> new Item(new Item.Properties()));


    public static <I extends Item> DeferredItem<I> register(final String en, final String zh, Supplier<I> it) {
        DeferredItem<I> item = ITEMS.register(en, it);
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
        CDNaturalItems.ITEMS.register(eventBus);
        CDMaterialItems.ITEMS.register(eventBus);
        CDFoodItems.ITEMS.register(eventBus);
    }
}
