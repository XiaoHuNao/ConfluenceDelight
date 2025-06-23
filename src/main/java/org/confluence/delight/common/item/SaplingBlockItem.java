package org.confluence.delight.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class SaplingBlockItem extends BlockItem {
    private final Component tooltipText;

    public SaplingBlockItem(Block block, Component tooltipText) {
        super(block, new Item.Properties());
        this.tooltipText = tooltipText;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(tooltipText);
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
