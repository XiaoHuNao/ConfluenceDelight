package org.confluence.delight.common.item.food;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.confluence.mod.common.effect.harmful.PotionSicknessEffect;
import org.confluence.mod.common.init.ModEffects;

import java.util.List;

import static org.confluence.mod.util.PlayerUtils.receiveMana;

public class SpecialMushroomSoup extends CDBaseFoodItem {
    public SpecialMushroomSoup() {
        super(new Properties().food(DelightFoodProperties.noEffectProperties(6, 6, Items.BOWL)));
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 16;
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
            player.heal(10);
            receiveMana(player, () -> 50);
            PotionSicknessEffect.addTo(player, 160);
        }
        return itemStack;
    }

    @Override
    protected void addCustomTooltip(ItemStack stack, List<Component> tooltipComponents) {
        tooltipComponents.add(Component.translatable("tooltip.item.confluence_delight.special_mushroom_soup").withStyle(ChatFormatting.GRAY));
    }
}
