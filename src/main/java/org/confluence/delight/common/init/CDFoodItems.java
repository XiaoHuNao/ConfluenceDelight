package org.confluence.delight.common.init;

import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.food.DelightFoodProperties;
import org.confluence.delight.common.food.DelightFoodProperties.EffectData;
import org.confluence.delight.common.item.HotStarChickenItem;
import org.confluence.mod.common.init.ModEffects;
import org.confluence.mod.common.item.food.BaseFoodItem;
import org.confluence.mod.common.item.food.ModFoodProperties;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.confluence.delight.ConfluenceDelight.chineseProviders;


public class CDFoodItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ConfluenceDelight.MODID);

    //食材
    public static final DeferredItem<BaseFoodItem> POTATO_PIECE = registerNormalFood("potato_piece", "马铃薯块", DelightFoodProperties.noEffectProperties(1, 0.6f));
    public static final DeferredItem<BaseFoodItem> CRUSHED_CHILLI = registerNormalFood("crushed_chilli", "辣椒碎", DelightFoodProperties.noEffectProperties(1, 1f));
    public static final DeferredItem<BaseFoodItem> JAR_CHILI_PEPPERS = registerNormalFood("jar_chili_peppers", "泡椒", DelightFoodProperties.noEffectProperties(1, 1f));
    public static final DeferredItem<BaseFoodItem> RAW_DONKEY_MEAT = registerNormalFood("raw_donkey_meat", "生驴肉", DelightFoodProperties.noEffectProperties(3, 1.8f));
    public static final DeferredItem<BaseFoodItem> RAW_HORSE_MEAT = registerNormalFood("raw_horse_meat", "生马肉", DelightFoodProperties.noEffectProperties(3, 1.8f));
    public static final DeferredItem<BaseFoodItem> COOKED_DONKEY_MEAT = registerNormalFood("cooked_donkey_meat", "熟驴肉", DelightFoodProperties.noEffectProperties(8, 12.8f));
    public static final DeferredItem<BaseFoodItem> COOKED_HORSE_MEAT = registerNormalFood("cooked_horse_meat", "熟马肉", DelightFoodProperties.noEffectProperties(8, 12.8f));
    public static final DeferredItem<BaseFoodItem> BUTTER = registerNormalFood("butter", "黄油",
            DelightFoodProperties.hasEffectProperties(3, 4.5f,
                    EffectData.of(MobEffects.CONFUSION, 400)));
    public static final DeferredItem<BaseFoodItem> CARROT_CUBES = registerNormalFood("carrot_cubes", "胡萝卜丁", DelightFoodProperties.noEffectProperties(1, 0.6f));
    public static final DeferredItem<BaseFoodItem> SWEET_CARROT_CUBES = registerNormalFood("sweet_carrot_cubes", "甜胡萝卜丁", DelightFoodProperties.noEffectProperties(3, 3f));//TODO 温暖效果
    public static final DeferredItem<BaseFoodItem> SPICY_PICKLED_FISH = registerNormalFood("spicy_pickled_fish", "泡鱼辣子", DelightFoodProperties.noEffectProperties(4, 4.8f));
    public static final DeferredItem<BaseFoodItem> BLACKCURRANT_JAM = registerNormalFood("blackcurrant_jam", "黑醋栗果酱", DelightFoodProperties.noEffectProperties(1, 1f));
    public static final DeferredItem<BaseFoodItem> CLOUD_BREAD_SLICE = registerNormalFood("cloud_bread_slice", "云朵面包片",
            DelightFoodProperties.hasEffectProperties(2, 1.5f,
                    EffectData.of(MobEffects.LEVITATION, 140),
                    EffectData.of(MobEffects.SLOW_FALLING, 300),
                    EffectData.of(ModEffects.EXQUISITELY_STUFFED, 3000)));
    public static final DeferredItem<BaseFoodItem> WHITE_CHOCOLATE = registerNormalFood("white_chocolate", "白巧克力",
            DelightFoodProperties.hasEffectProperties(2, 4,
                    EffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.NOURISHMENT, 200)));
    public static final DeferredItem<BaseFoodItem> BLACK_CHOCOLATE = registerNormalFood("black_chocolate", "黑巧克力",
            DelightFoodProperties.hasEffectProperties(2, 4,
                    EffectData.of(ModEffects.RAGE, 200),
                    EffectData.of(MobEffects.WEAKNESS, 200, 0.5f)));
    public static final DeferredItem<BaseFoodItem> FLAVORED_WHITE_CHOCOLATE = registerNormalFood("flavored_white_chocolate", "调味白巧克力",
            DelightFoodProperties.hasEffectProperties(4, 6,
                    EffectData.of(MobEffects.LUCK, 200),
                    EffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.COMFORT, 200)));

    //成品
    public static final DeferredItem<BaseFoodItem.BlockItem> CHICKEN_HOT_POT = registerBlockItemFood("chicken_hot_pot", "鸡公煲", builder -> builder.stackTo(1).food(
            ModFoodProperties.PlentySatisfiedProperties(6000, 20, 40.0f)).duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), CDFoodBlocks.CHICKEN_HOT_POT);
    public static final DeferredItem<BaseFoodItem> CRISPY_RICE_WITH_POTATOES = registerNormalFood("crispy_rice_with_potatoes", "锅巴土豆", DelightFoodProperties.noEffectProperties(3, 1.8f));
    public static final DeferredItem<BaseFoodItem> ROYAL_GUMMY = registerNormalFood("royal_gummy", "皇家软糖",
            DelightFoodProperties.hasEffectProperties(1, 2.0f,
                    EffectData.of(MobEffects.HEALTH_BOOST, 1200),
                    EffectData.of(MobEffects.REGENERATION, 5400)));
    public static final DeferredItem<BaseFoodItem> ATLANTIS_TSUNAMI = registerToolTipFood("atlantis_tsunami", "大西洋冲击波", builder -> builder.food(
            DelightFoodProperties.hasEffectProperties(1, 1.5f,
                    EffectData.of(MobEffects.MOVEMENT_SPEED, 1200, 1),
                    EffectData.of(MobEffects.CONFUSION, 400)))
            .duration(d -> 15).useAnim(u -> UseAnim.DRINK).eatingSound(s -> SoundEvents.GENERIC_DRINK), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> DONKEY_MEAT_FIRE = registerNormalFood("donkey_meat_fire", "驴肉火烧",
            DelightFoodProperties.hasEffectProperties(10, 12f,
                    EffectData.of(ModEffects.HUNGER_DELAYED, 3600)));
    public static final DeferredItem<BaseFoodItem> HORSE_MEAT_SASHIMI = registerNormalFood("horse_meat_sashimi", "马肉刺身",
            DelightFoodProperties.hasEffectProperties(8, 9.2f,
                    EffectData.of(MobEffects.MOVEMENT_SPEED, 3600, 1)));
    public static final DeferredItem<BaseFoodItem> BLACK_LUCK = registerToolTipFood("black_luck", "黑色幸运", builder -> builder.food(
            DelightFoodProperties.hasEffectProperties(4, 4f, EffectData.of(MobEffects.LUCK, 1800))
    ).duration(d -> 15).useAnim(u -> UseAnim.DRINK).eatingSound(s -> SoundEvents.GENERIC_DRINK), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> WHITE_DAWN = registerToolTipFood("white_dawn", "白色曙光", builder -> builder.food(
            DelightFoodProperties.hasEffectProperties(4, 4f, EffectData.of(ModEffects.MANA_REGENERATION, 1800))
    ).duration(d -> 15).useAnim(u -> UseAnim.DRINK).eatingSound(s -> SoundEvents.GENERIC_DRINK), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> BUTTER_FRIED_CLOUD_BREAD_SLICES = registerFood("butter_fried_cloud_bread_slices", "黄油煎云朵面包片", builder -> builder.food(
            DelightFoodProperties.hasEffectProperties(5, 10f, EffectData.of(MobEffects.SLOW_FALLING, 300))
    ).duration(d -> 25).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT));
    public static final DeferredItem<BaseFoodItem> CLOUD_BACON_SANDWICH = registerNormalFood("cloud_bacon_sandwich", "云朵培根三明治",
            DelightFoodProperties.hasEffectProperties(10, 12.5f,
                    EffectData.of(ModEffects.EXQUISITELY_STUFFED, 2400),
                    EffectData.of(MobEffects.SLOW_FALLING, 600)));
    public static final DeferredItem<BaseFoodItem> CLOUD_VEGETABLES_SANDWICH = registerNormalFood("cloud_vegetables_sandwich", "云朵蔬菜三明治",
            DelightFoodProperties.hasEffectProperties(8, 9.2f,
                    EffectData.of(ModEffects.EXQUISITELY_STUFFED, 1200, 1),
                    EffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.NOURISHMENT, 1200, 1)));
    public static final DeferredItem<BaseFoodItem> CLOUD_GEL_SANDWICH = registerNormalFood("cloud_gel_sandwich", "云朵凝胶三明治",
            DelightFoodProperties.hasEffectProperties(6, 8.2f,
                    EffectData.of(MobEffects.REGENERATION, 600),
                    EffectData.of(MobEffects.LEVITATION, 600),
                    EffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.NOURISHMENT, 600)));
    public static final DeferredItem<BaseFoodItem> CLOUD_FRIED_EGG_GEL_SANDWICH = registerNormalFood("cloud_fried_egg_gel_sandwich", "云朵煎蛋凝胶三明治",
            DelightFoodProperties.hasEffectProperties(8, 10.5f,
                    EffectData.of(MobEffects.REGENERATION, 600),
                    EffectData.of(MobEffects.LEVITATION, 600),
                    EffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.COMFORT, 600)));
    public static final DeferredItem<BaseFoodItem> HONEY_GLAZED_HAM = registerNormalFood("honey_glazed_ham", "蜜汁火腿",
            DelightFoodProperties.hasEffectProperties(16, 12.6f,
                    EffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.NOURISHMENT, 600),
                    EffectData.of(MobEffects.REGENERATION, 600),
                    EffectData.of(ModEffects.EXQUISITELY_STUFFED, 300, 1)));
    public static final DeferredItem<BaseFoodItem.BlockItem> BLACKCURRANT_DARK_CHOCOLATE_PIE = registerNormalBlockItemFood("blackcurrant_dark_chocolate_pie", "黑醋栗黑巧克力派", CDFoodBlocks.BLACKCURRANT_DARK_CHOCOLATE_PIE,
            DelightFoodProperties.hasEffectProperties(12, 12.0f,
                    EffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.COMFORT, 600),
                    EffectData.of(MobEffects.LUCK, 600),
                    EffectData.of(ModEffects.MAGIC_POWER, 600)));
    public static final DeferredItem<BaseFoodItem> BRAISED_BEEF_RICE = registerFood("braised_beef_rice", "红烧牛肉饭", builder -> builder.stackTo(1).food(
            DelightFoodProperties.hasEffectProperties(18, 20.0f,
                    EffectData.of(MobEffects.REGENERATION, 600),
                    EffectData.of(ModEffects.EXQUISITELY_STUFFED, 600, 1),
                    EffectData.of(MobEffects.DAMAGE_BOOST, 600, 1))
    ).duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT));
    public static final DeferredItem<BaseFoodItem> BRAISED_BEEF_NOODLES = registerFood("braised_beef_noodles", "红烧牛肉面", builder -> builder.stackTo(1).food(
            DelightFoodProperties.hasEffectProperties(18, 20.0f,
                    EffectData.of(MobEffects.REGENERATION, 600),
                    EffectData.of(ModEffects.EXQUISITELY_STUFFED, 600, 1),
                    EffectData.of(MobEffects.DAMAGE_RESISTANCE, 600))
    ).duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT));
    public static final DeferredItem<BaseFoodItem> MANGO_PUDDING = registerNormalFood("mango_pudding", "芒果布丁",
            DelightFoodProperties.hasEffectProperties(6, 8.0f,
                    EffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.COMFORT, 300)));
    public static final DeferredItem<BaseFoodItem> BANANA_PUDDING = registerNormalFood("banana_pudding", "香蕉布丁",
            DelightFoodProperties.hasEffectProperties(6, 8.0f,
                    EffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.COMFORT, 300)));
    public static final DeferredItem<BaseFoodItem> BLACKCURRANT_JAM_BREAD = registerNormalFood("blackcurrant_jam_bread", "黑醋栗果酱面包",
            DelightFoodProperties.hasEffectProperties(5, 8.0f,
                    EffectData.of(MobEffects.LUCK, 300),
                    EffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.COMFORT, 300)));
    public static final DeferredItem<BaseFoodItem> BLACKCURRANT_JAM_MANGO_PUDDING = registerNormalFood("blackcurrant_jam_mango_pudding", "黑醋栗果酱芒果布丁",
            DelightFoodProperties.hasEffectProperties(16, 20.0f,
                    EffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.COMFORT, 800),
                    EffectData.of(ModEffects.DANGER_SENSE, 800),
                    EffectData.of(ModEffects.SPELUNKER, 800)));
    public static final DeferredItem<BaseFoodItem> SPICY_BOMB_FISH = registerNormalFood("spicy_bomb_fish", "香辣炸弹鱼", DelightFoodProperties.noEffectProperties(10, 12.0f));
    public static final DeferredItem<BaseFoodItem> LUCK_CHOCOLATE_COPPER_COIN = registerToolTipFood("luck_chocolate_copper_coin", "幸运巧克力铜币", builder -> builder.food(
                    DelightFoodProperties.hasEffectProperties(4, 6.0f,
                            EffectData.of(CDEffects.LUCK_COIN, 600)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> LUCK_CHOCOLATE_SILVER_COIN = registerToolTipFood("luck_chocolate_silver_coin", "幸运巧克力银币", builder -> builder.food(
                    DelightFoodProperties.hasEffectProperties(4, 6.0f,
                            EffectData.of(CDEffects.LUCK_COIN, 900, 1)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> LUCK_CHOCOLATE_GOLDEN_COIN = registerToolTipFood("luck_chocolate_golden_coin", "幸运巧克力金币", builder -> builder.food(
                    DelightFoodProperties.hasEffectProperties(4, 6.0f,
                            EffectData.of(CDEffects.LUCK_COIN, 1200, 2)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> LUCK_CHOCOLATE_PLATINUM_COIN = registerToolTipFood("luck_chocolate_platinum_coin", "幸运巧克力铂金币", builder -> builder.food(
                    DelightFoodProperties.hasEffectProperties(4, 6.0f,
                            EffectData.of(CDEffects.LUCK_COIN, 1800, 3)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> LUCK_CHOCOLATE_EMERALD_COIN = registerToolTipFood("luck_chocolate_emerald_coin", "幸运巧克力绿宝石币", builder -> builder.food(
                    DelightFoodProperties.hasEffectProperties(4, 6.0f,
                            EffectData.of(CDEffects.LUCK_COIN, 600),
                            EffectData.of(CDEffects.MASTER_TRADER, 1800)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);//TODO 富可敌国效果
    public static final DeferredItem<BaseFoodItem> CHOCOLATE_LUCK_COIN_BOX = registerToolTipFood("chocolate_luck_coin_box", "巧克力幸运币礼盒", builder -> builder.food(
                    DelightFoodProperties.hasEffectProperties(20, 20.0f,
                            EffectData.of(CDEffects.LUCK_COIN, 2400, 4)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GOLD);
    public static final DeferredItem<BaseFoodItem> GILDED_LUXURY_CHOCOLATE_LUCK_COIN_BOX = registerToolTipFood("gilded_luxury_chocolate_luck_coin_box", "镀金奢华巧克力幸运币礼盒", builder -> builder.food(
                    DelightFoodProperties.hasEffectProperties(20, 20.0f,
                            EffectData.of(CDEffects.LUCK_COIN, 10800, 4),
                            EffectData.of(MobEffects.DAMAGE_RESISTANCE, 10800, 2),
                            EffectData.of(MobEffects.HEALTH_BOOST, 10800, 2),
                            EffectData.of(MobEffects.ABSORPTION, 10800, 2),
                            EffectData.of(MobEffects.DAMAGE_BOOST, 10800, 2),
                            EffectData.of(MobEffects.REGENERATION, 10800, 1)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GOLD);
    public static final DeferredItem<BaseFoodItem> CUMIN_FLAVORED_HORSE_MEAT_OVER_RICE = registerNormalFood("cumin_flavored_horse_meat_over_rice", "孜然马肉盖浇饭",
            DelightFoodProperties.hasEffectProperties(14, 18.0f,
                    EffectData.of(ModEffects.EXQUISITELY_STUFFED, 4000)));
    public static final DeferredItem<BaseFoodItem> CUMIN_FLAVORED_DONKEY_MEAT_OVER_RICE = registerNormalFood("cumin_flavored_donkey_meat_over_rice", "孜然驴肉盖浇饭",
            DelightFoodProperties.hasEffectProperties(14, 18.0f,
                    EffectData.of(ModEffects.EXQUISITELY_STUFFED, 4000)));
    public static final DeferredItem<BaseFoodItem> BRAISED_CHICKEN = registerToolTipFood("braised_chicken", "扒鸡", builder -> builder.food(
                    DelightFoodProperties.hasEffectProperties(16, 20.0f,
                            EffectData.of(ModEffects.EXQUISITELY_STUFFED, 4600, 1),
                            EffectData.of(ModEffects.CHOKING, 4000)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> BIG_CHICKEN_CUTLET = registerToolTipFood("big_chicken_cutlet", "大鸡排", builder -> builder.food(
                    DelightFoodProperties.hasEffectProperties(12, 16.0f,
                            EffectData.of(ModEffects.CHOKING, 2000)))
            .duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), 1, ChatFormatting.GRAY);
    public static final DeferredItem<Item> HOT_STAR_CHICKEN = normalItemRegister("hot_star_chicken", "豪大大鸡排", HotStarChickenItem::new);

    public static <I extends Item> DeferredItem<I> normalItemRegister(final String en, final String zh, Supplier<I> it) {
        DeferredItem<I> item = ITEMS.register(en, it);
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    public static DeferredItem<BaseFoodItem> registerFood(String en, String zh, Consumer<BaseFoodItem.Builder> consumer) {
        DeferredItem<BaseFoodItem> item = ITEMS.register(en, () -> {
            BaseFoodItem.Builder builder = BaseFoodItem.builder().stackTo(64);
            consumer.accept(builder);
            return builder.build();
        });
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    public static DeferredItem<BaseFoodItem> registerToolTipFood(String en, String zh, Consumer<BaseFoodItem.Builder> consumer, int line, ChatFormatting chatFormatting) {
        DeferredItem<BaseFoodItem> item = ITEMS.register(en, () -> {
            BaseFoodItem.Builder builder = BaseFoodItem.builder().stackTo(64).tooltip(en, line, chatFormatting);
            consumer.accept(builder);
            return builder.build();
        });
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    public static DeferredItem<BaseFoodItem> registerNormalFood(String en, String zh, FoodProperties foodProperties) {
        DeferredItem<BaseFoodItem> item = ITEMS.register(en, () -> {
            BaseFoodItem.Builder builder = BaseFoodItem.builder().stackTo(64).food(foodProperties).duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT);
            return builder.build();
        });
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    public static DeferredItem<BaseFoodItem.BlockItem> registerNormalBlockItemFood(String en, String zh, Supplier<? extends Block> block, FoodProperties foodProperties) {
        DeferredItem<BaseFoodItem.BlockItem> item = ITEMS.register(en, () -> {
            BaseFoodItem.Builder builder = BaseFoodItem.builder().stackTo(64).food(foodProperties).duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT);
            return new BaseFoodItem.BlockItem(block.get(), builder.getProperties());
        });
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    public static DeferredItem<BaseFoodItem.BlockItem> registerBlockItemFood(String en, String zh, Consumer<BaseFoodItem.Builder> consumer, Supplier<? extends Block> block) {
        DeferredItem<BaseFoodItem.BlockItem> item = ITEMS.register(en, () -> {
            BaseFoodItem.Builder builder = BaseFoodItem.builder().stackTo(64);
            consumer.accept(builder);
            return new BaseFoodItem.BlockItem(block.get(), builder.getProperties());
        });
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }

    public static DeferredItem<BaseFoodItem> registerDrinkingFood(String en, String zh, FoodProperties foodProperties, int duration, UseAnim useAnim, SoundEvent drinkingSoundType, SoundEvent eatingSoundType) {
        DeferredItem<BaseFoodItem> item = ITEMS.register(en, () -> {
            BaseFoodItem.Builder builder = BaseFoodItem.builder().stackTo(64).food(foodProperties).duration(d -> duration).useAnim(u -> useAnim).drinkingSound(s -> drinkingSoundType).eatingSound(e -> eatingSoundType);
            return builder.build();
        });
        chineseProviders.add(l -> l.addItem(item, zh));
        return item;
    }
}
