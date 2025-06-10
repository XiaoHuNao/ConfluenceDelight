package org.confluence.delight.common.food;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

import java.util.Arrays;

public class DelightFoodProperties {

    public static FoodProperties noEffectProperties(int nutrition, float saturation) {
        return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).fast().alwaysEdible().build();
    }

    public static FoodProperties hasEffectProperties(int nutrition, float saturation, EffectData... effects) {
        FoodProperties.Builder builder = new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).fast().alwaysEdible();
        Arrays.stream(effects).forEach(e -> builder.effect(() -> new MobEffectInstance(e.effect, e.duration, e.level), 1.0f));
        return builder.build();
    }

    public record EffectData(Holder<MobEffect> effect, int duration, int level) {

        public static EffectData of(Holder<MobEffect> effect, int duration) {
            return new EffectData(effect, duration, 0);
        }
    }
}
