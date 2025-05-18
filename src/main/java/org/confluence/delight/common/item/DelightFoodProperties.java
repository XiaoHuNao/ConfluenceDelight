package org.confluence.delight.common.item;

public class DelightFoodProperties {
    //无效果食物
    public static net.minecraft.world.food.FoodProperties noEffectProperties(int nutrition, float saturation) {
        return new net.minecraft.world.food.FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).fast().alwaysEdible().build();
    }
}
