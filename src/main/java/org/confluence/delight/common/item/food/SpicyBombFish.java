package org.confluence.delight.common.item.food;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.confluence.lib.ConfluenceMagicLib;
import org.confluence.lib.common.component.ModRarity;

public class SpicyBombFish extends CDFoodItem {
    public SpicyBombFish() {
        super(new Properties().food(DelightFoodProperties.noEffectProperties(10, 12)).component(ConfluenceMagicLib.MOD_RARITY, ModRarity.BLUE));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.explode(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 2.5F, false, Level.ExplosionInteraction.MOB);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
