package org.confluence.delight.util;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;

public record CDEffectData(Holder<MobEffect> effect, int duration, int level, float probability) {

    public static CDEffectData of(Holder<MobEffect> effect, int duration) {
        return new CDEffectData(effect, duration, 0, 1.0f);
    }

    public static CDEffectData of(Holder<MobEffect> effect, int duration, int level) {
        return new CDEffectData(effect, duration, level, 1.0f);
    }

    public static CDEffectData of(Holder<MobEffect> effect, int duration, float probability) {
        return new CDEffectData(effect, duration, 0, probability);
    }

    public static CDEffectData of(Holder<MobEffect> effect, int duration, int level, float probability) {
        return new CDEffectData(effect, duration, level, probability);
    }
}
