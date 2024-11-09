package com.xiaohunao.confluencedelight.common.init;

import com.xiaohunao.confluencedelight.ConfluenceDelight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = net.neoforged.neoforge.registries.DeferredRegister.create(BuiltInRegistries.ITEM, ConfluenceDelight.MODID);

    public static final DeferredHolder<Item,Item> MARSHMALLOW = registerSimpleFoodItems("marshmallow", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> COOKED_MARSHMALLOW = registerSimpleFoodItems("cooked_marshmallow", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> FROGGLE_BUNWICH = registerSimpleFoodItems("froggle_bunwich", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> MONSTER_LASAGNA = registerSimpleFoodItems("monster_lasagna", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> SAUTEED_FROG_LEGS = registerSimpleFoodItems("sauteed_frog_legs", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> SEAFOOD_DINNER = registerSimpleFoodItems("seafood_dinner", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> SMOOTHIE_OF_DARKNESS = registerSimpleFoodItems("smoothie_of_darkness", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> SASHIMI = registerSimpleFoodItems("sashimi", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> BBQ_RIBS = registerSimpleFoodItems("bbq_ribs", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> FRIES = registerSimpleFoodItems("fries", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> POTATO_CHIPS = registerSimpleFoodItems("potato_chips", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> CHRISTMAS_PUDDING = registerSimpleFoodItems("christmas_pudding", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> SUGAR_COOKIE = registerSimpleFoodItems("sugar_cookie", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> PAD_THAI = registerSimpleFoodItems("pad_thai", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> PHO = registerSimpleFoodItems("pho", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> ICE_CREAM = registerSimpleFoodItems("ice_cream", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> BANANA_SPLIT = registerSimpleFoodItems("banana_split", ModFoodValues.EMPTY);
    public static final DeferredHolder<Item,Item> MILKSHAKE = registerSimpleFoodItems("milkshake", ModFoodValues.EMPTY);

    public static DeferredHolder<Item,Item> registerSimpleFoodItems(final String name, final FoodProperties food) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().food(food)));
    }
    public static DeferredHolder<Item,Item> registerSimpleFoodItems(final String name, final FoodProperties food, final Item craftingRemaining) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().food(food).craftRemainder(craftingRemaining)));
    }
}
