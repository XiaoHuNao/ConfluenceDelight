package org.confluence.delight.common.effect.beneficial;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;

public class DineEffect extends MobEffect {
    public DineEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x9ACD32);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            FoodData foodData = player.getFoodData();
            foodData.eat(1, 0.0f);
        }
        return false;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 200 == 0;
    }
}
