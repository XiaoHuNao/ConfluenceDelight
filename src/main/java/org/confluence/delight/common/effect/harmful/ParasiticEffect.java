package org.confluence.delight.common.effect.harmful;

import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ParasiticEffect extends MobEffect {
    public ParasiticEffect() {
        super(MobEffectCategory.HARMFUL, 0xFF0000);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.isAlive()) {
            float damage = 2.0f + amplifier;
            entity.hurt(entity.damageSources().source(DamageTypes.MAGIC), damage);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}
