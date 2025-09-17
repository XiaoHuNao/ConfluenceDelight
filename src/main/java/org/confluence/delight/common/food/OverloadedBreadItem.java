package org.confluence.delight.common.food;

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
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.confluence.delight.util.CDEffectData;
import org.confluence.mod.common.effect.harmful.PotionSicknessEffect;
import org.confluence.mod.common.init.ModAttachmentTypes;
import org.confluence.mod.common.init.ModEffects;

import java.util.List;

import static org.confluence.mod.util.PlayerUtils.receiveMana;

public class OverloadedBreadItem extends CDBaseFoodItem {
    public OverloadedBreadItem() {
        super(new Properties().food(
                DelightFoodProperties.hasEffectProperties(60, 100,
                        CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 36000, 2),
                        CDEffectData.of(MobEffects.ABSORPTION, 100, 127))));
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 60;
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
            player.heal(player.getMaxHealth() * 20);
            receiveMana(player, () -> player.getData(ModAttachmentTypes.MANA_STORAGE).getMaxMana() * 20);
            PotionSicknessEffect.addTo(player, 4000);
        }
        return itemStack;
    }

    @Override
    protected void addCustomTooltip(ItemStack stack, List<Component> tooltipComponents) {
        tooltipComponents.add(Component.translatable("tooltip.item.confluence_delight.overloaded_bread").withStyle(ChatFormatting.GRAY));
    }
}
