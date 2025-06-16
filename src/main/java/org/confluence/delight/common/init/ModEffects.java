package org.confluence.delight.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.effect.beneficial.LuckCoinEffect;
import org.confluence.lib.common.effect.PublicMobEffect;

import java.util.function.Supplier;

import static org.confluence.delight.ConfluenceDelight.chineseProviders;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, ConfluenceDelight.MODID);

    public static final DeferredHolder<MobEffect, MobEffect> LUCK_COIN = register("luck_coin", "幸运币", LuckCoinEffect::new);
    public static final DeferredHolder<MobEffect, MobEffect> MASTER_TRADER = register("master_trader", "交易大师", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0x7FFFAA));
    public static final DeferredHolder<MobEffect, MobEffect> FABULOUSLY_RICH = register("fabulously_rich", "富可敌国", () -> new PublicMobEffect(MobEffectCategory.BENEFICIAL, 0xFFD700));

    public static DeferredHolder<MobEffect, MobEffect> register(final String en, final String zh, Supplier<? extends MobEffect> it) {
        DeferredHolder<MobEffect, MobEffect> mobEffect = EFFECTS.register(en, it);
        chineseProviders.add(l -> l.addEffect(mobEffect, zh));
        return mobEffect;
    }
}
