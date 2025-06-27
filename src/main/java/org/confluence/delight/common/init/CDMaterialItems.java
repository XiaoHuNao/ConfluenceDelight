package org.confluence.delight.common.init;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;

import java.util.function.Supplier;

import static org.confluence.delight.ConfluenceDelight.chineseProviders;

public class CDMaterialItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ConfluenceDelight.MODID);

    public static final DeferredItem<Item> SALT = register("salt", "盐");
    public static final DeferredItem<Item> CHILI_POWDER = register("chili_powder", "辣椒面");
    public static final DeferredItem<Item> CUMIN_POWDER = register("cumin_powder", "孜然粉");
    public static final DeferredItem<Item> WHITE_PEPPER = register("white_pepper", "白胡椒");
    public static final DeferredItem<Item> MAPLE_SYRUP = register("maple_syrup", "枫糖浆");
    public static final DeferredItem<Item> GLOWING_MUSHROOM_SAUCE = register("glowing_mushroom_sauce", "发光蘑菇酱");
    public static final DeferredItem<Item> WARPED_SAP = register("warped_sap", "诡异树液");

    public static <I extends Item> DeferredItem<I> register(final String en, final String zh, Supplier<I> it) {
        DeferredItem<I> item = ITEMS.register(en, it);
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    private static DeferredItem<Item> register(final String en, final String zh) {
        return register(en, zh, () -> new Item(new Item.Properties()));
    }
}
