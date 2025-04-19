package com.xiaohunao.confluencedelight.common.init;

import com.xiaohunao.confluencedelight.ConfluenceDelight;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.xiaohunao.confluencedelight.ConfluenceDelight.chineseProviders;

public class ModMaterialItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ConfluenceDelight.MODID);

    public static DeferredHolder<Item, Item> register(final String en, final String zh, Supplier<? extends Item> it) {
        DeferredHolder<Item, Item> item = ITEMS.register(en, it);
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    private static DeferredHolder<Item, Item> register(final String en, final String zh) {
        return register(en, zh, () -> new Item(new Item.Properties()));
    }
}
