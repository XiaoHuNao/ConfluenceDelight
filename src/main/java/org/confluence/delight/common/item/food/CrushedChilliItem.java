package org.confluence.delight.common.item.food;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.confluence.lib.ConfluenceMagicLib;
import org.confluence.lib.common.component.ModRarity;

public class CrushedChilliItem extends CDFoodItem{
    public CrushedChilliItem() {
        super(new Properties().food(DelightFoodProperties.noEffectProperties(1, 0.5f)).component(ConfluenceMagicLib.MOD_RARITY, ModRarity.BLUE));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        RandomSource random = livingEntity.getRandom();
        if (level instanceof ServerLevel serverLevel && random.nextInt(2) == 0) {
            livingEntity.igniteForTicks(40);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
