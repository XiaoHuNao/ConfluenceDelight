package org.confluence.delight.common.init;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;

import java.util.function.Supplier;

public class CDItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ConfluenceDelight.MODID);
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(ConfluenceDelight.MODID);

    public static final DeferredHolder<Item, Item> WINE_BUCKET = register("wine_bucket", "酒桶", () -> new BucketItem(CDFluids.WINE.fluid().get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final DeferredItem<Item> PINEAPPLE_SEEDS = register("pineapple_seeds", "菠萝种子", () -> new ItemNameBlockItem(CDNaturalBlocks.PINEAPPLE_CROP.get(), new Item.Properties()));
    public static final DeferredItem<Item> DRAGON_FRUIT_SEEDS = register("dragon_fruit_seeds", "火龙果种子", () -> new ItemNameBlockItem(CDNaturalBlocks.DRAGON_FRUIT_CACTUS.get(), new Item.Properties()));
    public static final DeferredItem<Item> SPICY_PEPPER_SEEDS = register("spicy_pepper_seeds", "辣椒种子", () -> new ItemNameBlockItem(CDNaturalBlocks.SPICY_PEPPER_CROP.get(), new Item.Properties()));

    private static <I extends Item> DeferredItem<I> register(final String en, final String zh, Supplier<I> item) {
        return CDMaterialItems.register(en, zh, item);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        BLOCK_ITEMS.register(eventBus);
        CDFoodItems.ITEMS.register(eventBus);
        CDMaterialItems.ITEMS.register(eventBus);
    }
}
