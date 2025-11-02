package org.confluence.delight.common.event.game.entity;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.CDCommonConfigs;
import org.confluence.delight.util.CDTextUtils;
import org.confluence.mod.common.item.food.BaseFoodItem;

@EventBusSubscriber(modid = ConfluenceDelight.MODID)
public class ItemEvents {

    @SubscribeEvent
    public static void tooltip$Event(ItemTooltipEvent event) {
        if (!CDCommonConfigs.ENABLE_TOOLTIP.get()) return;
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();
        if (item instanceof BaseFoodItem baseFoodItem) {
            FoodProperties foodProperties = baseFoodItem.getFoodProperties(new ItemStack(baseFoodItem), event.getEntity());
            if (foodProperties != null && !foodProperties.effects().isEmpty()) {
                CDTextUtils.addFoodEffectTooltip(stack, event.getToolTip()::add, 1.0f, event.getContext().tickRate());
            }
        } else if (item instanceof BaseFoodItem.BItem baseFoodItem) {
            FoodProperties foodProperties = baseFoodItem.getFoodProperties(new ItemStack(baseFoodItem), event.getEntity());
            if (foodProperties != null && !foodProperties.effects().isEmpty()) {
                CDTextUtils.addFoodEffectTooltip(stack, event.getToolTip()::add, 1.0f, event.getContext().tickRate());
            }
        }
    }
}
