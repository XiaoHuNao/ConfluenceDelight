package org.confluence.delight.common.item.food;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.confluence.lib.ConfluenceMagicLib;
import org.confluence.lib.common.component.ModRarity;

public class SpicyPickledFishItem extends CDFoodItem{
    public SpicyPickledFishItem() {
        super(new Properties().food(DelightFoodProperties.noEffectProperties(4, 4.8f)).component(ConfluenceMagicLib.MOD_RARITY, ModRarity.BLUE));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        livingEntity.igniteForSeconds(60.0f);
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
