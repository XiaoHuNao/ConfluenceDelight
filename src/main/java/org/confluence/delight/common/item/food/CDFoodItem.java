package org.confluence.delight.common.item.food;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import org.confluence.delight.common.CDCommonConfigs;
import org.confluence.delight.util.CDTextUtils;

import java.util.List;

public class CDFoodItem extends Item {
    public CDFoodItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        addCustomTooltip(stack, tooltipComponents);
        if (CDCommonConfigs.ENABLE_TOOLTIP.get()) {
            CDTextUtils.addFoodEffectTooltip(stack, tooltipComponents::add, 1.0f, context.tickRate());
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    protected void addCustomTooltip(ItemStack stack, List<Component> tooltipComponents) {
    }

    public static class BItem extends BlockItem {
        public BItem(Block block, Properties properties) {
            super(block, properties);
        }

        @Override
        public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
            addCustomTooltip(stack, tooltipComponents);
            if (CDCommonConfigs.ENABLE_TOOLTIP.get()) {
                CDTextUtils.addFoodEffectTooltip(stack, tooltipComponents::add, 1.0f, context.tickRate());
            }
            super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        }

        protected void addCustomTooltip(ItemStack stack, List<Component> tooltipComponents) {
        }
    }
}