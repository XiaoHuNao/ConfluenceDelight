package org.confluence.delight.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import org.confluence.delight.common.food.DelightFoodProperties;
import org.confluence.delight.common.init.CDJukeboxSongs;
import org.confluence.delight.util.CDEffectData;
import org.confluence.mod.common.init.ModEffects;

import java.util.List;

public class HotStarChickenItem extends Item {
    public HotStarChickenItem() {
        super(new Properties().food(
                        DelightFoodProperties.hasEffectProperties(8, 12.0f,
                                CDEffectData.of(ModEffects.EXQUISITELY_STUFFED, 2400)))
                .jukeboxPlayable(CDJukeboxSongs.HOT_STAR_CHICKEN));
    }

    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 15;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.EAT;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.item.confluence_delight.hot_star_chicken"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
