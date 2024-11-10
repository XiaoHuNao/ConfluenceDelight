package com.xiaohunao.confluencedelight.common.init;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ModFoodValues {
    public static final int BRIEF_DURATION = 600;    // 30 seconds
    public static final int SHORT_DURATION = 1200;    // 1 minute
    public static final int MEDIUM_DURATION = 3600;    // 3 minutes
    public static final int LONG_DURATION = 6000;    // 5 minutes


    public static final FoodProperties EMPTY = new FoodProperties.Builder().build();


    public static final BiFunction<Holder<MobEffect>,Integer, Supplier<MobEffectInstance>> EFFECT_ADDER = (effect, time) ->
            () -> new MobEffectInstance(effect, time, 0, false, true);


    public static final BiFunction<Holder<MobEffect>,Integer, FoodProperties> EFFECTIVE_FOOD = (effect, time) ->
            new FoodProperties.Builder().effect(EFFECT_ADDER.apply(effect, time),1).build();



}
