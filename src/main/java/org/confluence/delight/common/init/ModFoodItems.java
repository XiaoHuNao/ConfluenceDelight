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
import org.confluence.delight.common.item.DelightFoodProperties;
import org.confluence.mod.common.init.ModEffects;
import org.confluence.mod.common.item.food.BaseFoodItem;
import org.confluence.mod.common.item.food.ModFoodProperties;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.confluence.delight.ConfluenceDelight.chineseProviders;


public class ModFoodItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ConfluenceDelight.MODID);

    //食材
    public static final DeferredItem<BaseFoodItem> POTATO_PIECE = registerNormalFood("potato_piece", "马铃薯块", DelightFoodProperties.noEffectProperties(1, 0.3f));
    public static final DeferredItem<BaseFoodItem> CRUSHED_CHILLI = registerNormalFood("crushed_chilli", "辣椒碎", DelightFoodProperties.noEffectProperties(1, 0.5f));
    public static final DeferredItem<BaseFoodItem> JAR_CHILI_PEPPERS = registerNormalFood("jar_chili_peppers", "泡椒", DelightFoodProperties.noEffectProperties(1, 0.5f));
    public static final DeferredItem<BaseFoodItem> DONKEY_MEAT = registerNormalFood("donkey_meat", "驴肉", DelightFoodProperties.noEffectProperties(8, 0.625f));
    public static final DeferredItem<BaseFoodItem> HORSE_MEAT = registerNormalFood("horse_meat", "马肉", DelightFoodProperties.noEffectProperties(8, 0.625f));

    //成品
    public static final DeferredItem<BaseFoodItem.BlockItem> CHICKEN_HOT_POT = registerBlockItemFood("chicken_hot_pot", "鸡公煲", builder -> builder.stackTo(1).food(ModFoodProperties.PlentySatisfiedProperties(6000, 20, 40.0f)).duration(d -> 15).useAnim(u -> UseAnim.EAT).eatingSound(s -> SoundEvents.GENERIC_EAT), ModBlocks.CHICKEN_HOT_POT);
    public static final DeferredItem<BaseFoodItem> CRISPY_RICE_WITH_POTATOES = registerNormalFood("crispy_rice_with_potatoes", "锅巴土豆", DelightFoodProperties.noEffectProperties(3, 0.3f));
    public static final DeferredItem<BaseFoodItem> ROYAL_GUMMY = registerNormalFood("royal_gummy", "皇家软糖", DelightFoodProperties.RoyalGummy);
    public static final DeferredItem<BaseFoodItem> ATLANTIS_TSUNAMI = registerToolTipFood("atlantis_tsunami", "大西洋冲击波", builder -> builder.food(DelightFoodProperties.AtlantisTsunami), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> DONKEY_MEAT_FIRE = registerNormalFood("donkey_meat_fire", "驴肉火烧", DelightFoodProperties.hasEffectProperties(16, 0.625f, ModEffects.HUNGER_DELAYED, 3600, 0));
    public static final DeferredItem<BaseFoodItem> HORSE_MEAT_SASHIMI = registerNormalFood("horse_meat_sashimi", "马肉刺身", DelightFoodProperties.hasEffectProperties(8, 0.625f, MobEffects.MOVEMENT_SPEED, 3600, 1));
    public static final DeferredItem<BaseFoodItem> BLACK_LUCK = registerToolTipFood("black_luck", "黑色幸运", builder -> builder.food(DelightFoodProperties.hasEffectProperties(4, 2.0f, MobEffects.LUCK, 1800, 0)).duration(d -> 15).useAnim(u -> UseAnim.DRINK).eatingSound(s -> SoundEvents.GENERIC_DRINK), 1, ChatFormatting.GRAY);
    public static final DeferredItem<BaseFoodItem> WHITE_DAWN = registerToolTipFood("white_dawn", "白色曙光", builder -> builder.food(DelightFoodProperties.hasEffectProperties(4, 2.0f, ModEffects.MANA_REGENERATION, 1800, 0)).duration(d -> 15).useAnim(u -> UseAnim.DRINK).eatingSound(s -> SoundEvents.GENERIC_DRINK), 1, ChatFormatting.GRAY);


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
