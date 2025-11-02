package org.confluence.delight.common.item.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.ItemLike;
import org.confluence.delight.util.CDEffectData;
import org.confluence.mod.common.item.food.ModFoodPropertiesBuilder;

import java.util.Arrays;

public class DelightFoodProperties {

    public static FoodProperties noEffectProperties(int nutrition, float saturation) {
        return new ModFoodPropertiesBuilder()
            .nutrition(nutrition)
            .saturation(saturation)
            .fast()
            .alwaysEdible()
            .build();
    }

    public static FoodProperties noEffectProperties(int nutrition, float saturation, ItemLike item) {
        return new ModFoodPropertiesBuilder()
            .nutrition(nutrition)
            .saturation(saturation)
            .fast()
            .useCovertsTo(item)
            .alwaysEdible()
            .build();
    }

    public static FoodProperties hasEffectProperties(int nutrition, float saturation, CDEffectData... effects) {
        ModFoodPropertiesBuilder builder = ModFoodPropertiesBuilder.Builder()
            .nutrition(nutrition)
            .saturation(saturation)
            .fast()
            .alwaysEdible();
        Arrays.stream(effects).forEach(e -> builder.addEffect(() -> new MobEffectInstance(e.effect(), e.duration(), e.level()), e.probability()));
        return builder.build();
    }

    public static FoodProperties hasEffectProperties(int nutrition, float saturation, ItemLike item, CDEffectData... effects) {
        ModFoodPropertiesBuilder builder = ModFoodPropertiesBuilder.Builder()
            .nutrition(nutrition)
            .saturation(saturation)
            .fast()
            .useCovertsTo(item)
            .alwaysEdible();
        Arrays.stream(effects).forEach(e -> builder.addEffect(() -> new MobEffectInstance(e.effect(), e.duration(), e.level()), e.probability()));
        return builder.build();
    }


}
