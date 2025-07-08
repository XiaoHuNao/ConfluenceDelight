package org.confluence.delight.common.effect.beneficial;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ImmunityEffect extends MobEffect {
    public ImmunityEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x98D982);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.tickCount % 20 == 0) {
            float healAmount = 0.5F * (amplifier + 1);
            if (entity.getHealth() < entity.getMaxHealth()) {
                entity.heal(healAmount);
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}
