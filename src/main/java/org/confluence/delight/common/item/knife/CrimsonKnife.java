package org.confluence.delight.common.item.knife;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import org.confluence.mod.common.init.ModTiers;
import org.confluence.mod.common.init.item.ModItems;
import vectorwing.farmersdelight.common.item.KnifeItem;

public class CrimsonKnife extends KnifeItem {
    public CrimsonKnife() {
        super(ModTiers.CRIMTANE, new Properties()
                .attributes(KnifeItem.createAttributes(ModTiers.CRIMTANE, -1, -3))
                .component(DataComponents.UNBREAKABLE, ModItems.UNBREAKABLE));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean success = super.hurtEnemy(stack, target, attacker);
        if (success && attacker instanceof Player player) {
            FoodData foodData = player.getFoodData();
            if (foodData.getFoodLevel() < 20 && target.isDeadOrDying()) {
                foodData.eat(1, 1.5f);
            }
        }
        return success;
    }

    @Override
    public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
        if (enchantment.is(Enchantments.SWEEPING_EDGE) || enchantment.is(Enchantments.UNBREAKING)) {
            return false;
        }
        return super.isPrimaryItemFor(stack, enchantment);
    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        if (enchantment.is(Enchantments.SWEEPING_EDGE) || enchantment.is(Enchantments.UNBREAKING)) {
            return false;
        }
        return super.supportsEnchantment(stack, enchantment);
    }
}
