package org.confluence.delight.common.item.food;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.confluence.delight.common.init.CDJukeboxSongs;
import org.confluence.delight.util.CDEffectData;
import org.confluence.mod.common.init.ModEffects;

import java.util.List;

public class HotStarChickenItem extends CDFoodItem {
    public HotStarChickenItem() {
        super(new Properties().food(
                DelightFoodProperties.hasEffectProperties(4, 4,
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
    protected void addCustomTooltip(ItemStack stack, List<Component> tooltipComponents) {
        tooltipComponents.add(Component.translatable("tooltip.item.confluence_delight.hot_star_chicken"));
    }
}
