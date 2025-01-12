package com.xiaohunao.confluencedelight.common.init;

import com.xiaohunao.confluencedelight.ConfluenceDelight;
import com.xiaohunao.confluencedelight.common.item.MaterialItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.xiaohunao.confluencedelight.ConfluenceDelight.chineseProviders;

public class ModMaterialItems {
    public static final DeferredRegister<Item> ITEMS = net.neoforged.neoforge.registries.DeferredRegister.create(BuiltInRegistries.ITEM, ConfluenceDelight.MODID);

    public static final DeferredHolder<Item, Item> Ezfic_Beef_Sauce = register("ezfic_beef_sauce", "星星牛肉酱");
    public static final DeferredHolder<Item, Item> Dragonfish_Ham = register("dragonfish_ham", "龙鱼尾");
    public static final DeferredHolder<Item, Item> Cloud_Pasta = register("cloud_pasta", "云朵意面");
    public static final DeferredHolder<Item, Item> Butter = register("butter", "黄油");
    public static final DeferredHolder<Item, Item> Sclerasperm = register("sclerasperm", "乳白物");

    // 浆果
    public static final DeferredHolder<Item, Item> Blue_Berries = register("blue_berries", "蓝莓");
    public static final DeferredHolder<Item, Item> Cloud_Berries = register("cloud_berries", "云莓");
    public static final DeferredHolder<Item, Item> Raspberries = register("raspberries", "覆盆子");
    public static final DeferredHolder<Item, Item> Royaloranges = register("royaloranges", "御膳橘");
    public static final DeferredHolder<Item, Item> Gooseberry = register("gooseberry", "鹅莓");
    public static final DeferredHolder<Item, Item> Creptberries = register("creptberries", "炸弹浆果");

    //邪恶水果
    public static final DeferredHolder<Item, Item> Corruptruit = register("corruptruit", "腐烂果");
    public static final DeferredHolder<Item, Item> Crimsonapple = register("crimsonapple", "血肉果");


    public static final DeferredHolder<Item, Item> Oyster_Sauce = register("oyster_sauce", "蚝油");
    public static final DeferredHolder<Item, Item> Meteorite_Ingredients = register("meteorite_ingredients", "陨石食材");
    public static final DeferredHolder<Item, Item> Hard_Meat = register("hard_meat", "肉山肉块");
    public static final DeferredHolder<Item, Item> Lavapple = register("lavapple", "熔岩果");
    public static final DeferredHolder<Item, Item> Blazice = register("blazice", "烈焰米");


    public static final DeferredHolder<Item, Item> SUN_PLATE_BOWL = register("sun_plate_bowl", "日盘碗");


    public static DeferredHolder<Item, Item> register(final String en, final String zh, Supplier<? extends Item> it) {
        DeferredHolder<Item, Item> item = ITEMS.register(en, it);
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    private static DeferredHolder<Item, Item> register(final String en, final String zh) {
        return register(en, zh, () -> new MaterialItem(new Item.Properties()));
    }
}
