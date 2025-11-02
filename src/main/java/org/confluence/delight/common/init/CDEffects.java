package org.confluence.delight.common.init;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.effect.beneficial.DineEffect;
import org.confluence.delight.common.effect.beneficial.ImmuneEffect;
import org.confluence.delight.common.effect.beneficial.ImmunityEffect;
import org.confluence.delight.common.effect.beneficial.LuckCoinEffect;
import org.confluence.delight.common.effect.harmful.ParasiticEffect;
import org.confluence.lib.common.effect.PublicMobEffect;
import org.confluence.mod.common.init.ModEffects;

import java.util.function.Supplier;

import static org.confluence.delight.ConfluenceDelight.chineseProviders;

public class CDEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, ConfluenceDelight.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> LUCK_COIN = register("luck_coin", "幸运币", LuckCoinEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> MASTER_TRADER = register("master_trader", "交易大师", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0x10bb13));
    public static final DeferredHolder<MobEffect, MobEffect> HARVEST = register("harvest", "丰收", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0x990000));
    public static final DeferredHolder<MobEffect, MobEffect> DINE = register("dine", "果腹", DineEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> IMMUNITY = register("immunity", "免疫力", ImmunityEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> POISON_IMMUNE = register("poison_immune", "毒素免疫", () -> new ImmuneEffect(0x4CAF50).setImmuneEffects(HolderSet.direct(MobEffects.POISON)));
    public static final DeferredHolder<MobEffect, MobEffect> MINING_FATIGUE_IMMUNE = register("mining_fatigue_immune", "挖掘疲劳免疫", () -> new ImmuneEffect(0xFF9800).setImmuneEffects(HolderSet.direct(MobEffects.DIG_SLOWDOWN)));
    public static final DeferredHolder<MobEffect, MobEffect> BLINDNESS_IMMUNE = register("blindness_immune", "失明免疫", () -> new ImmuneEffect(0x2196F3).setImmuneEffects(HolderSet.direct(MobEffects.BLINDNESS)));
    public static final DeferredHolder<MobEffect, MobEffect> BLEEDING_IMMUNE = register("bleeding_immune", "流血免疫", () -> new ImmuneEffect(0xE91E63).setImmuneEffects(HolderSet.direct(ModEffects.BLEEDING)));
    public static final DeferredHolder<MobEffect, MobEffect> SOUL_SAND_SLOWS_DOWN_IMMUNE = register("soul_sand_slows_down_immune", "灵魂沙减速免疫", () -> new ImmuneEffect(
        0x9C27B0, m -> m.put(Attributes.MOVEMENT_EFFICIENCY, new MobEffect.AttributeTemplate(ConfluenceDelight.asResource("soul_sand_slows_down_immune"), 1.0, AttributeModifier.Operation.ADD_VALUE))));

    public static final DeferredHolder<MobEffect, MobEffect> PARASITIC = register("parasitic", "寄生", ParasiticEffect::new);

    public static DeferredHolder<MobEffect, MobEffect> register(final String en, final String zh, Supplier<? extends MobEffect> it) {
        DeferredHolder<MobEffect, MobEffect> mobEffect = EFFECTS.register(en, it);
        chineseProviders.add(l -> l.addEffect(mobEffect, zh));
        return mobEffect;
    }
}
