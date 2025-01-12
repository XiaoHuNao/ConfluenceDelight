package com.xiaohunao.confluencedelight.common.init;

import com.xiaohunao.confluencedelight.ConfluenceDelight;
import com.xiaohunao.confluencedelight.common.item.BaseFoodItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Map;
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

    public static final DeferredHolder<Item, Item> Dragonfish_Glowingsoup = register("dragonfish_glowingsoup", "金汤龙鱼尾");
    public static final DeferredHolder<Item, Item> Dragonfish_Beacon_Sandwich = register("dragonfish_beacon_sandwich", "龙鱼培根三明治");
    public static final DeferredHolder<Item, Item> Confluence_Feast = register("confluence_feast", "汇流盛宴");
    public static final DeferredHolder<Item, Item> Evil_Salad = register("evil_salad", "邪恶沙拉");
    public static final DeferredHolder<Item, Item> Blaze_Ice_Cream = register("blaze_ice_cream", "椒盐冰淇淋");
    public static final DeferredHolder<Item, Item> Ezfical_Fish = register("ezfical_fish", "清汤坠星鱼");
    public static final DeferredHolder<Item, Item> Nature_Gift_Salad = register("nature_gift_salad", "凉拌大自然的恩赐");
    public static final DeferredHolder<Item, Item> Sperm_Stew = register("sperm_stew", "发光蘑菇奶油炖菜");
    public static final DeferredHolder<Item, Item> Preasure_Pancake = register("pressure_pancake", "茴香牡蛎烙饼");
    public static final DeferredHolder<Item, Item> Plate_of_Oyster = register("plate_of_oyster", "一盘烤生蚝");
    public static final DeferredHolder<Item, Item> Crystapple = register("crystapple", "晶洞果");
    public static final DeferredHolder<Item, Item> Hard_Schawama = register("hard_schawama", "肉山沙威玛");
    public static final DeferredHolder<Item, Item> Ezfic_Salad = register("ezfic_salad", "凉拌落星");
    public static final DeferredHolder<Item, Item> Blutkuchen = register("blutkuchen", "血肉蛋糕");
    public static final DeferredHolder<Item, Item> Desire_Cake = register("desire_cake", "飞灵蛋糕");
    public static final DeferredHolder<Item, Item> Fungi_Soup = register("fungi_soup", "杂菌汤");
    public static final DeferredHolder<Item, Item> Terraria_Fungirice = register("terraria_fungirice", "泰拉蘑菇饭");
    public static final DeferredHolder<Item, Item> Ichor_Pie = register("ichor_pie", "灵液派");
    public static final DeferredHolder<Item, Item> Corrupt_Sharkfin_Jelly = register("corrupt_sharkfin_jelly", "黑檀鱼翅冻");
    public static final DeferredHolder<Item, Item> Aether_Pasta = register("aether_pasta", "发光蘑菇云朵意面");
    public static final DeferredHolder<Item, Item> FEE_Soup = register("fee", "椎骨煲胡萝卜甘蔗汤");
    public static final DeferredHolder<Item, Item> Confluence_Memory = register("confluence_memory", "汇流记忆");


    // 饮料
    public static final DeferredHolder<Item, Item> EXCM = register("excm", "??");
    public static final DeferredHolder<Item, Item> Bloody_Wine = register("bloody_wine", "血肉酒");


    //使用建造者
    public static final DeferredHolder<Item, Item> TEST_FOOD = register("test_food", "测试食物1",1, food -> food
            .modify(pro->pro
                    .effect(()->new MobEffectInstance(MobEffects.GLOWING, 100, 0),0.5f)
                    .nutrition(10)
                    .usingConvertsTo(Items.BOWL)
            )
            .addSpecialEffect((stack,liv)->liv.hurt(liv.damageSources().magic(),1))
    );
    //使用预制体
    public static final DeferredHolder<Item, Item> TEST_FOOD2 = register("test_food2", "测试食物2",1,
            EFFECTIVE_FOOD.apply(MobEffects.GLOWING, LONG_DURATION)
    );
    //在预制体上修改参数
    public static final DeferredHolder<Item, Item> TEST_FOOD3 = register("test_food3", "测试食物3",1,
            ()->EFFECTIVE_FOOD.apply(MobEffects.GLOWING, LONG_DURATION).get()
                    .addSpecialEffect((stack,liv)->liv.hurt(liv.damageSources().magic(),1))
    );



    /**使用预制体*/
    private static DeferredHolder<Item, Item> register(final String en, final String zh,int nutrition, Supplier<BaseFoodItem.FoodBuilder> foodSupplier) {
        DeferredHolder<Item, Item> item = ITEMS.register(en, () -> new BaseFoodItem(foodSupplier.get().modify(pro->pro.nutrition(nutrition))));
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    /**使用建造者*/
    private static DeferredHolder<Item, Item> register(final String en, final String zh,int nutrition, Function<BaseFoodItem.FoodBuilder, BaseFoodItem.FoodBuilder> foodBuilder) {
        return register(en, zh, nutrition, ()->foodBuilder.apply(new BaseFoodItem.FoodBuilder()));
    }

    /**无效果*/
    private static DeferredHolder<Item, Item> register(final String en, final String zh, int nutrition) {
        return register(en, zh,nutrition, food -> food);
    }

    /**占位符*/
    private static DeferredHolder<Item, Item> register(final String en, final String zh) {
        return register(en, zh,1);
    }

    public static Map<Item, Double> consumptionProbabilityMap;
}
