package com.xiaohunao.confluencedelight.common.init;

import com.xiaohunao.confluencedelight.common.item.BaseFoodItem;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ModFoodUtils {
    public static final int BRIEF_DURATION = 600;    // 30 seconds
    public static final int SHORT_DURATION = 1200;    // 1 minute
    public static final int MEDIUM_DURATION = 3600;    // 3 minutes
    public static final int LONG_DURATION = 6000;    // 5 minutes


    public static final FoodProperties EMPTY = new FoodProperties.Builder().build();

    /**单一效果的食物*/
    public static final BiFunction<Holder<MobEffect>,Integer,Supplier<BaseFoodItem.FoodBuilder>> EFFECTIVE_FOOD = (effect, time) ->
            ()->new BaseFoodItem.FoodBuilder().modify(pro->pro.effect(()->new MobEffectInstance(effect,time,0), 1.0f ));

}
