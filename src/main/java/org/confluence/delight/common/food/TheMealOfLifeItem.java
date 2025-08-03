package org.confluence.delight.common.food;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.confluence.delight.common.init.CDFoodBlocks;
import org.confluence.mod.common.effect.harmful.PotionSicknessEffect;
import org.confluence.mod.common.init.ModEffects;

import java.util.List;

public class TheMealOfLifeItem extends BlockItem {
    public TheMealOfLifeItem() {
        super(CDFoodBlocks.THE_MEAL_OF_LIFE_BLOCK.get(), new Properties().food(DelightFoodProperties.noEffectProperties(18, 36.0f, Items.BOWL)));
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
        if (!level.isClientSide) {
            living.heal(50);
            living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1));
            living.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 500, 4));
            living.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 2400));
            living.addEffect(new MobEffectInstance(ModEffects.EXQUISITELY_STUFFED, 18000));
            PotionSicknessEffect.addTo(living, 900);
        }
        return itemStack;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.item.confluence_delight.the_meal_of_life").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
