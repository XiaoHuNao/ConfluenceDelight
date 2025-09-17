package org.confluence.delight.common.food;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.confluence.delight.common.init.CDFoodBlocks;
import org.confluence.delight.util.CDEffectData;
import org.confluence.delight.util.CDTextUtils;
import org.confluence.mod.common.effect.harmful.PotionSicknessEffect;
import org.confluence.mod.common.init.ModEffects;

import java.util.List;

public class TheMealOfLifeItem extends BlockItem {
    public TheMealOfLifeItem() {
        super(CDFoodBlocks.THE_MEAL_OF_LIFE_BLOCK.get(), new Properties().food(
                DelightFoodProperties.hasEffectProperties(18, 36.0f, Items.BOWL,
                        CDEffectData.of(MobEffects.REGENERATION, 600, 1),
                        CDEffectData.of(MobEffects.HEALTH_BOOST, 500, 4),
                        CDEffectData.of(MobEffects.ABSORPTION, 2400),
                        CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 18000))));
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 15;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return !player.hasEffect(ModEffects.POTION_SICKNESS) ? ItemUtils.startUsingInstantly(level, player, hand) : InteractionResultHolder.fail(player.getItemInHand(hand));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity living) {
        super.finishUsingItem(itemStack, level, living);
        if (!level.isClientSide && living instanceof ServerPlayer player) {
            player.heal(50);
            PotionSicknessEffect.addTo(player, 900);
        }
        return itemStack;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.item.confluence_delight.the_meal_of_life").withStyle(ChatFormatting.GRAY));
        CDTextUtils.addFoodEffectTooltip(stack, tooltipComponents::add, 1.0f, context.tickRate());
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
