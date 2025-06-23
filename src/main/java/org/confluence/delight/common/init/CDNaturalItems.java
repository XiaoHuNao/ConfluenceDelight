package org.confluence.delight.common.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;

import java.util.function.Supplier;

import static org.confluence.delight.ConfluenceDelight.chineseProviders;

public class CDNaturalItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ConfluenceDelight.MODID);

    //crop
    public static final DeferredItem<Item> PINEAPPLE_SEEDS = register("pineapple_seeds", "菠萝种子", () -> new ItemNameBlockItem(CDNaturalBlocks.PINEAPPLE_CROP.get(), new Item.Properties()));
    public static final DeferredItem<Item> DRAGON_FRUIT_SEEDS = register("dragon_fruit_seeds", "火龙果种子", () -> new ItemNameBlockItem(CDNaturalBlocks.DRAGON_FRUIT_CACTUS.get(), new Item.Properties()));
    public static final DeferredItem<Item> SPICY_PEPPER_SEEDS = register("spicy_pepper_seeds", "辣椒种子", () -> new ItemNameBlockItem(CDNaturalBlocks.SPICY_PEPPER_CROP.get(), new Item.Properties()));

    private static <I extends Item> DeferredItem<I> register(final String en, final String zh, Supplier<I> it) {
        DeferredItem<I> item = ITEMS.register(en, it);
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }
}
