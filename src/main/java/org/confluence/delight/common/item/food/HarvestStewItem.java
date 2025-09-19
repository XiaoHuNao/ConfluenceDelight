package org.confluence.delight.common.item.food;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.confluence.delight.common.CDCommonConfigs;
import org.confluence.delight.common.init.CDEffects;
import org.confluence.delight.util.CDEffectData;
import org.confluence.delight.util.CDTextUtils;

import java.util.List;

public class HarvestStewItem extends CDBaseFoodItem {
    public static final MobEffectInstance EFFECT = new MobEffectInstance(CDEffects.HARVEST, 6000);
    private static final List<EntityType<?>> ALLOW_ENTITY = List.of(
            EntityType.CHICKEN,
            EntityType.SHEEP
    );

    public HarvestStewItem() {
        super(new Properties().food(DelightFoodProperties.hasEffectProperties(5, 8.0f, CDEffectData.of(CDEffects.HARVEST, 6000))));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (target instanceof Animal animal) {
            EntityType<?> entityType = animal.getType();
            if (ALLOW_ENTITY.contains(entityType)) {
                Level level = player.level();
                if (!level.isClientSide() && !animal.isBaby()) {
                    animal.addEffect(EFFECT);
                    if (level instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(
                                ParticleTypes.HAPPY_VILLAGER,
                                animal.getX(), animal.getY() + 1, animal.getZ(),
                                5, 0.2, 0.2, 0.2, 0.1
                        );
                    }
                    stack.shrink(1);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    protected void addCustomTooltip(ItemStack stack, List<Component> tooltip) {
        tooltip.add(CDTextUtils.getTranslation("harvest_stew.tooltip").withStyle(ChatFormatting.GRAY));
        if (CDCommonConfigs.ENABLE_TOOLTIP.get()) {
            MobEffect effect = EFFECT.getEffect().value();
            MutableComponent effectDescription = Component.literal(" ");
            effectDescription.append(Component.translatable(EFFECT.getDescriptionId()));
            if (EFFECT.getAmplifier() > 0) {
                effectDescription.append(" ")
                        .append(Component.translatable("potion.potency." + EFFECT.getAmplifier()));
            }
            if (EFFECT.getDuration() > 20) {
                effectDescription.append(" (")
                        .append(MobEffectUtil.formatDuration(EFFECT, 1.0F, 20.0F))
                        .append(")");
            }
            tooltip.add(effectDescription.withStyle(effect.getCategory().getTooltipFormatting()));
        }
    }
}
