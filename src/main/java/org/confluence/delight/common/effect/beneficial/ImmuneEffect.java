package org.confluence.delight.common.effect.beneficial;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

import java.util.Set;

public class ImmuneEffect extends MobEffect {
    private Set<Holder<MobEffect>> immuneEffects = Set.of();

    public ImmuneEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x7fbd80);
    }

    public ImmuneEffect setImmuneEffects(Set<Holder<MobEffect>> immuneEffects) {
        this.immuneEffects = immuneEffects;
        return this;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        for (Holder<MobEffect> effect : immuneEffects) {
            if (entity.hasEffect(effect)) {
                entity.removeEffect(effect);
            }
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    public boolean isImmuneTo(Holder<MobEffect> effect) {
        return immuneEffects.contains(effect);
    }
}
