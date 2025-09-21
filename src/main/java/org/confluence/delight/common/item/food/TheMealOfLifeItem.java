package org.confluence.delight.common.item.food;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.confluence.delight.common.init.CDFoodBlocks;
import org.confluence.delight.util.CDEffectData;
import org.confluence.mod.common.init.ModEffects;

import java.util.List;

public class TheMealOfLifeItem extends CDBaseFoodItem.BItem {
    public TheMealOfLifeItem() {
        super(CDFoodBlocks.THE_MEAL_OF_LIFE_BLOCK.get(), new Properties().food(
                DelightFoodProperties.hasEffectProperties(18, 36.0f, Items.BOWL,
                        CDEffectData.of(MobEffects.REGENERATION, 600, 1),
                        CDEffectData.of(MobEffects.HEALTH_BOOST, 500, 4),
                        CDEffectData.of(MobEffects.ABSORPTION, 2400),
                        CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 18000),
                        CDEffectData.of(ModEffects.POTION_SICKNESS, 900))));
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
        if (!level.isClientSide && living instanceof ServerPlayer player) {
            player.heal(50);
        }
        return super.finishUsingItem(itemStack, level, living);
    }

    @Override
    protected void addCustomTooltip(ItemStack stack, List<Component> tooltipComponents) {
        tooltipComponents.add(Component.translatable("tooltip.item.confluence_delight.the_meal_of_life").withStyle(ChatFormatting.GRAY));
    }
}
