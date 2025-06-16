package org.confluence.delight.common.effect.beneficial;

import it.unimi.dsi.fastutil.ints.Int2DoubleFunction;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.confluence.delight.ConfluenceDelight;

public class LuckCoinEffect extends MobEffect {
    public static final ResourceLocation ID = ConfluenceDelight.asResource("luck_coin");

    public LuckCoinEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFD700);
        Int2DoubleFunction baseLuckBonus = amplifier -> switch (amplifier) {
            case 0 -> 1;
            case 1 -> 2;
            case 2 -> 3;
            case 3 -> 6;
            case 4 -> 10;
            default -> 0;
        };
        Int2DoubleFunction luckMultiplier = amplifier -> switch (amplifier) {
            case 0, 1 -> 0.10;
            case 2 -> 0.20;
            case 3 -> 0.50;
            case 4 -> 1.00;
            default -> 0;
        };
        this.addAttributeModifier(Attributes.LUCK, ID, AttributeModifier.Operation.ADD_VALUE, baseLuckBonus);
        this.addAttributeModifier(Attributes.LUCK, ID, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, luckMultiplier);
    }

    public static void onRemove(LivingEntity living, Holder<MobEffect> mobEffect, int amplifier) {
        if (amplifier > 0 && mobEffect.is(ID)) {
            living.addEffect(new MobEffectInstance(mobEffect, 6000, amplifier - 1));
        }
    }
}
