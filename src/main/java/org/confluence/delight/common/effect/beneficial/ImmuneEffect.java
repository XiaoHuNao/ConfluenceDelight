package org.confluence.delight.common.effect.beneficial;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class ImmuneEffect extends MobEffect {
    private static final Map<Holder<MobEffect>, Set<ImmuneEffect>> IMMUNE_CACHE = new ConcurrentHashMap<>();
    private volatile HolderSet<MobEffect> immuneEffects = HolderSet.direct();

    public ImmuneEffect(int color) {
        super(MobEffectCategory.BENEFICIAL, color);
    }

    public ImmuneEffect(int color, Consumer<Map<Holder<Attribute>, AttributeTemplate>> attributeConfigurator) {
        super(MobEffectCategory.BENEFICIAL, color);
        Map<Holder<Attribute>, AttributeTemplate> modifiers = new HashMap<>();
        attributeConfigurator.accept(modifiers);
        modifiers.forEach((attribute, template) -> this.addAttributeModifier(attribute, template.id(), template.amount(), template.operation()));
    }

    public ImmuneEffect setImmuneEffects(HolderSet<MobEffect> immuneEffects) {
        if (immuneEffects.size() == 0) {
            throw new IllegalArgumentException("Immune effects set cannot be null or empty");
        }
        synchronized (this) {
            clearCaches();
            this.immuneEffects = HolderSet.direct(immuneEffects.stream().toList());
            this.immuneEffects.stream()
                .peek(effect -> Objects.requireNonNull(effect, "Effect holder cannot be null"))
                .forEach(effect -> IMMUNE_CACHE
                    .computeIfAbsent(effect, k -> Collections.newSetFromMap(new ConcurrentHashMap<>()))
                    .add(this)
                );
        }
        return this;
    }

    public static void removeImmuneEffects(LivingEntity entity) {
        List<Holder<MobEffect>> toRemove = new ArrayList<>();
        for (MobEffectInstance effectInstance : entity.getActiveEffects()) {
            Holder<MobEffect> effectHolder = effectInstance.getEffect();
            if (!getImmuneEffects(effectHolder).isEmpty()) {
                toRemove.add(effectHolder);
            }
        }
        for (Holder<MobEffect> effectHolder : toRemove) {
            entity.removeEffect(effectHolder);
        }
    }

    private void clearCaches() {
        if (this.immuneEffects == null) return;
        synchronized (this) {
            this.immuneEffects.stream()
                .filter(Objects::nonNull)
                .forEach(effect -> IMMUNE_CACHE.computeIfPresent(effect, (k, set) -> {
                    set.remove(this);
                    return set.isEmpty() ? null : set;
                }));
        }
    }

    public static void onRemove(Holder<MobEffect> effectHolder) {
        if (!(effectHolder.value() instanceof ImmuneEffect effect)) {
            return;
        }
        effect.clearCaches();
    }

    public boolean isImmuneTo(Holder<MobEffect> effect) {
        return immuneEffects.contains(effect);
    }

    public static boolean isEffectImmune(LivingEntity entity, Holder<MobEffect> effect) {
        return entity.getActiveEffects().stream()
            .map(MobEffectInstance::getEffect)
            .filter(holder -> holder.value() instanceof ImmuneEffect)
            .map(holder -> (ImmuneEffect) holder.value())
            .anyMatch(immune -> immune.isImmuneTo(effect));
    }

    public static Set<ImmuneEffect> getImmuneEffects(Holder<MobEffect> effect) {
        return IMMUNE_CACHE.getOrDefault(effect, Collections.emptySet());
    }
}