package org.confluence.delight.common.init;

import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.food.DelightFoodProperties;
import org.confluence.delight.common.food.DelightFoodProperties.EffectData;
import org.confluence.mod.common.init.ModEffects;
import org.confluence.mod.common.item.food.BaseFoodItem;
import org.confluence.mod.common.item.food.ModFoodProperties;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.confluence.delight.ConfluenceDelight.chineseProviders;


public class ModFoodItems {
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
            DelightFoodProperties.hasEffectProperties(2, 0.5f,
                    EffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.NOURISHMENT, 200)));
    public static final DeferredItem<BaseFoodItem> BLACK_CHOCOLATE = registerNormalFood("black_chocolate", "黑巧克力",
            DelightFoodProperties.hasEffectProperties(2, 0.5f,
                    EffectData.of(ModEffects.RAGE, 200),
                    EffectData.of(MobEffects.WEAKNESS, 200, 0.5f)));

    //成品
    public static final DeferredItem<BaseFoodItem.BlockItem> CHICKEN_HOT_POT = registerBlockItemFood("chicken_hot_pot", "鸡公煲", builder -> builder.stackTo(1).food(ModFoodProperties.PlentySatisfiedProperties(6000, 20, 40.0f)).duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), ModBlocks.CHICKEN_HOT_POT);
    public static final DeferredItem<BaseFoodItem> CRISPY_RICE_WITH_POTATOES = registerNormalFood("crispy_rice_with_potatoes", "锅巴土豆", DelightFoodProperties.noEffectProperties(3, 1.8f));
    public static final DeferredItem<BaseFoodItem> ROYAL_GUMMY = registerNormalFood("royal_gummy", "皇家软糖",
            DelightFoodProperties.hasEffectProperties(1, 2.0f,
                    EffectData.of(MobEffects.HEALTH_BOOST, 1200),
                    EffectData.of(MobEffects.REGENERATION, 5400)));
    public static final DeferredItem<BaseFoodItem> ATLANTIS_TSUNAMI = registerToolTipFood("atlantis_tsunami", "大西洋冲击波", builder -> builder.food(
            DelightFoodProperties.hasEffectProperties(1, 1.5f,
            EffectData.of(MobEffects.MOVEMENT_SPEED, 1200, 1),
            EffectData.of(MobEffects.CONFUSION, 400))), 1, ChatFormatting.GRAY);
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
