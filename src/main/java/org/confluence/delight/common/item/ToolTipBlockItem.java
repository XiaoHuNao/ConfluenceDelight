package org.confluence.delight.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class ToolTipBlockItem extends BlockItem {
    private final Component tooltipText;

    public ToolTipBlockItem(Block block, Component tooltipText) {
        super(block, new Item.Properties());
        this.tooltipText = tooltipText;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(tooltipText);
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
