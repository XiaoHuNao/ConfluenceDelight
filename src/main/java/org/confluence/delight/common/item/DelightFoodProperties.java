package org.confluence.delight.common.item;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class DelightFoodProperties {

    public static FoodProperties noEffectProperties(int nutrition, float saturation) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).fast().alwaysEdible().build();
    }

    public static FoodProperties hasEffectProperties(int nutrition, float saturation, Holder<MobEffect> effects, int duration, int level) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).fast().alwaysEdible()
                .effect(() -> new MobEffectInstance(effects, duration, level), 1.0f)
                .build();
    }

    public static final FoodProperties RoyalGummy = new FoodProperties.Builder().nutrition(1).saturationModifier(2.0f).alwaysEdible().fast()
            .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 1200), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 5400), 1.0f)
            .build();

    public static final FoodProperties AtlantisTsunami = new FoodProperties.Builder().nutrition(1).saturationModifier(1.5f).alwaysEdible().fast()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 1), 1.0f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400), 1.0f)
            .build();
}
