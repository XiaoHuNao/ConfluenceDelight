package org.confluence.delight.common.event.game.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.effect.beneficial.LuckCoinEffect;
import org.confluence.delight.common.init.CDEffects;
import org.confluence.delight.common.init.CDFoodItems;

@EventBusSubscriber(modid = ConfluenceDelight.MODID, bus = EventBusSubscriber.Bus.GAME)
public final class LivingEntityEvents {

    @SubscribeEvent
    public static void mobEffectAdded(MobEffectEvent.Added event) {
        LivingEntity entity = event.getEntity();
        MobEffectInstance mobEffectInstance = event.getEffectInstance();
        if (mobEffectInstance == null) return;
        if (entity.hasEffect(CDEffects.IMMUNITY) && mobEffectInstance.getEffect().value().getCategory() == MobEffectCategory.HARMFUL) {
            MobEffectInstance immunity = entity.getEffect(CDEffects.IMMUNITY);
            if (immunity == null) return;
            int immunityLevel = immunity.getAmplifier() + 1;
            float reductionRate = switch (immunityLevel) {
                case 2 -> 0.25f;
                case 3 -> 0.35f;
                default -> 0.15f;
            };
            int originalDuration = mobEffectInstance.getDuration();
            int reducedDuration = Math.max(1, (int) (originalDuration * (1 - reductionRate)));
            if (reducedDuration == originalDuration) return;
            mobEffectInstance.duration = reducedDuration;
        }
    }

    @SubscribeEvent
    public static void mobEffect$Remove(MobEffectEvent.Remove event) {
        MobEffectInstance effectInstance = event.getEffectInstance();
        if (effectInstance == null) return;
        LuckCoinEffect.onRemove(event.getEntity(), effectInstance.getEffect(), effectInstance.getAmplifier());
    }

    @SubscribeEvent
    public static void livingEntityUseItemFinish(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!(player.level() instanceof ServerLevel serverLevel)) return;
        ItemStack itemStack = event.getItem();
        RandomSource random = player.getRandom();
        if (itemStack.is(CDFoodItems.CRUSHED_CHILLI.get()) && random.nextInt(2) == 0) {
            player.igniteForTicks(40);
        } else if (itemStack.is(CDFoodItems.SPICY_PICKLED_FISH.get())) {
            player.igniteForSeconds(60.0f);
        } else if (itemStack.is(CDFoodItems.SPICY_BOMB_FISH.get())) {
            serverLevel.explode(null, player.getX(), player.getY(), player.getZ(), 2.5F, false, Level.ExplosionInteraction.MOB);
        } else if (itemStack.is(CDFoodItems.BIG_CHICKEN_CUTLET.get())) {
            player.teleportTo(player.getX(), player.getY() + 100, player.getZ());
        }
    }
}
