package org.confluence.delight.common.food;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

import java.util.Arrays;

public class DelightFoodProperties {

    public static float calcSaturationModifier(int nutrition, float rawSaturation) {
        return rawSaturation / nutrition / 2;
    }

    public static FoodProperties noEffectProperties(int nutrition, float rawSaturation) {
        return new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(calcSaturationModifier(nutrition, rawSaturation))
                .fast()
                .alwaysEdible()
                .build();
    }

    public static FoodProperties hasEffectProperties(int nutrition, float rawSaturation, EffectData... effects) {
        FoodProperties.Builder builder = new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(calcSaturationModifier(nutrition, rawSaturation))
                .fast()
                .alwaysEdible();
        Arrays.stream(effects).forEach(e -> builder.effect(() -> new MobEffectInstance(e.effect, e.duration, e.level), e.probability));
        return builder.build();
    }

    public record EffectData(Holder<MobEffect> effect, int duration, int level, float probability) {

        public static EffectData of(Holder<MobEffect> effect, int duration) {
            return new EffectData(effect, duration, 0, 1.0f);
        }

        public static EffectData of(Holder<MobEffect> effect, int duration, int level) {
            return new EffectData(effect, duration, level, 1.0f);
        }

        public static EffectData of(Holder<MobEffect> effect, int duration, float probability) {
            return new EffectData(effect, duration, 0, probability);
        }

        public static EffectData of(Holder<MobEffect> effect, int duration, int level, float probability) {
            return new EffectData(effect, duration, level, probability);
        }
    }
}
