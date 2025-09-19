package org.confluence.delight.common.item.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.ItemLike;
import org.confluence.delight.util.CDEffectData;

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

    public static FoodProperties noEffectProperties(int nutrition, float rawSaturation, ItemLike item) {
        return new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(calcSaturationModifier(nutrition, rawSaturation))
                .fast()
                .usingConvertsTo(item)
                .alwaysEdible()
                .build();
    }

    public static FoodProperties hasEffectProperties(int nutrition, float rawSaturation, CDEffectData... effects) {
        FoodProperties.Builder builder = new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(calcSaturationModifier(nutrition, rawSaturation))
                .fast()
                .alwaysEdible();
        Arrays.stream(effects).forEach(e -> builder.effect(() -> new MobEffectInstance(e.effect(), e.duration(), e.level()), e.probability()));
        return builder.build();
    }

    public static FoodProperties hasEffectProperties(int nutrition, float rawSaturation, ItemLike item, CDEffectData... effects) {
        FoodProperties.Builder builder = new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(calcSaturationModifier(nutrition, rawSaturation))
                .fast()
                .usingConvertsTo(item)
                .alwaysEdible();
        Arrays.stream(effects).forEach(e -> builder.effect(() -> new MobEffectInstance(e.effect(), e.duration(), e.level()), e.probability()));
        return builder.build();
    }


}
