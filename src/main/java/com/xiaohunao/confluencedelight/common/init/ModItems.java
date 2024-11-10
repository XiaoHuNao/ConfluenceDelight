package com.xiaohunao.confluencedelight.common.init;

import com.xiaohunao.confluencedelight.ConfluenceDelight;
import com.xiaohunao.confluencedelight.common.item.BaseFoodItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.*;

import static com.xiaohunao.confluencedelight.ConfluenceDelight.chineseProviders;
import static com.xiaohunao.confluencedelight.common.init.ModFoodUtils.*;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = net.neoforged.neoforge.registries.DeferredRegister.create(BuiltInRegistries.ITEM, ConfluenceDelight.MODID);

    public static final DeferredHolder<Item, Item> MARSHMALLOW = register("marshmallow", "棉花糖");
    public static final DeferredHolder<Item, Item> COOKED_MARSHMALLOW = register("cooked_marshmallow", "烤棉花糖");
    public static final DeferredHolder<Item, Item> FROGGLE_BUNWICH = register("froggle_bunwich", "青蛙三明治");
    public static final DeferredHolder<Item, Item> MONSTER_LASAGNA = register("monster_lasagna", "怪物千层面");
    public static final DeferredHolder<Item, Item> SAUTEED_FROG_LEGS = register("sauteed_frog_legs", "炒蛙腿");
    public static final DeferredHolder<Item, Item> SEAFOOD_DINNER = register("seafood_dinner", "海鲜大餐");
    public static final DeferredHolder<Item, Item> SMOOTHIE_OF_DARKNESS = register("smoothie_of_darkness", "黑暗奶昔");
    public static final DeferredHolder<Item, Item> SASHIMI = register("sashimi", "生鱼片");
    public static final DeferredHolder<Item, Item> BBQ_RIBS = register("bbq_ribs", "烧烤肋排");
    public static final DeferredHolder<Item, Item> FRIES = register("fries", "薯条");
    public static final DeferredHolder<Item, Item> POTATO_CHIPS = register("potato_chips", "薯片");
    public static final DeferredHolder<Item, Item> CHRISTMAS_PUDDING = register("christmas_pudding", "圣诞布丁");
    public static final DeferredHolder<Item, Item> SUGAR_COOKIE = register("sugar_cookie", "蜜糖饼干");
    public static final DeferredHolder<Item, Item> PAD_THAI = register("pad_thai", "泰式炒面");
    public static final DeferredHolder<Item, Item> PHO = register("pho", "越南河粉");
    public static final DeferredHolder<Item, Item> ICE_CREAM = register("ice_cream", "冰淇淋");
    public static final DeferredHolder<Item, Item> BANANA_SPLIT = register("banana_split", "香蕉船");
    public static final DeferredHolder<Item, Item> MILKSHAKE = register("milkshake", "奶昔");

    public static final DeferredHolder<Item, Item> SUN_PLATE_BOWL = registerItem("sun_plate_bowl", "日盘碗");


    /**使用建造者*/
    public static final DeferredHolder<Item, Item> TEST_FOOD = register("test_food", "测试食物1", food -> food
            .addEffect(MobEffects.GLOWING, LONG_DURATION)
            .addEffect(MobEffects.HEALTH_BOOST, SHORT_DURATION)
            .setKeeper(Items.WHEAT)
            .addSpecialEffect((stack,liv)->liv.hurt(liv.damageSources().magic(),1))
    );
    /**使用预制体*/
    public static final DeferredHolder<Item, Item> TEST_FOOD2 = register("test_food2", "测试食物2",
            EFFECTIVE_FOOD.apply(MobEffects.GLOWING, LONG_DURATION)
    );
    /**在预制体上修改参数*/
    public static final DeferredHolder<Item, Item> TEST_FOOD3 = register("test_food3", "测试食物3",
            ()->EFFECTIVE_FOOD.apply(MobEffects.GLOWING, LONG_DURATION).get()
                    .addSpecialEffect((stack,liv)->liv.hurt(liv.damageSources().magic(),1))
    );


    public static DeferredHolder<Item, Item> registerItem(final String en, final String zh) {
        DeferredHolder<Item, Item> item = ITEMS.register(en, () -> new Item(new Item.Properties()));
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    public static DeferredHolder<Item, Item> registerItem(final String en, final String zh, Supplier<? extends Item> it) {
        DeferredHolder<Item, Item> item = ITEMS.register(en, it);
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    /**使用预制体*/
    public static DeferredHolder<Item, Item> register(final String en, final String zh, Supplier<BaseFoodItem.FoodBuilder> foodSupplier) {
        DeferredHolder<Item, Item> item = ITEMS.register(en, () -> new BaseFoodItem(foodSupplier.get()));
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }
    /**使用建造者*/
    public static DeferredHolder<Item, Item> register(final String en, final String zh, Function<BaseFoodItem.FoodBuilder, BaseFoodItem.FoodBuilder> foodBuilder) {
        DeferredHolder<Item, Item> item = ITEMS.register(en, () -> new BaseFoodItem(foodBuilder.apply(new BaseFoodItem.FoodBuilder())));
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }
    /**无效果*/
    public static DeferredHolder<Item, Item> register(final String en, final String zh) {
        return register(en, zh, food -> food);
    }


}
