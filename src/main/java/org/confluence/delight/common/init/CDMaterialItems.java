package org.confluence.delight.common.init;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.food.DelightFoodProperties;
import org.confluence.mod.common.item.food.BaseFoodItem;

import java.util.function.Supplier;

import static org.confluence.delight.ConfluenceDelight.chineseProviders;

public class CDMaterialItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ConfluenceDelight.MODID);

    public static final DeferredItem<Item> RICE_NOODLES = register("rice_noodles", "米粉");

    private static <I extends Item> DeferredItem<I> register(final String en, final String zh, Supplier<I> it) {
        DeferredItem<I> item = ITEMS.register(en, it);
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    private static DeferredItem<Item> register(final String en, final String zh) {
        return register(en, zh, () -> new Item(new Item.Properties()));
    }
}
