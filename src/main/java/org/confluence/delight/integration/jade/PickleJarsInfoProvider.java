package org.confluence.delight.integration.jade;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.block.crafting.PickleJarsBlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public class PickleJarsInfoProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    public static final PickleJarsInfoProvider INSTANCE = new PickleJarsInfoProvider();
    public static final ResourceLocation UID = ConfluenceDelight.asResource("jade_picklejars_info");

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        CompoundTag serverData = accessor.getServerData();
        if (serverData.contains("fluid")) {
            String fluidNameKey = serverData.getString("fluid");
            int amountMb = serverData.getInt("amount");
            int capacityMb = serverData.getInt("capacity");
            int amountBuckets = amountMb / 1000;
            int capacityBuckets = capacityMb / 1000;
            if (amountMb == 0) {
                Component text = Component.translatable("info.confluence_delight.fluid_empty", capacityBuckets);
                tooltip.add(text);
            } else {
                if (Screen.hasShiftDown()) {
                    Component text = Component.translatable("info.confluence_delight.fluid_capacity",
                            fluidNameKey, amountBuckets, capacityBuckets).withStyle(ChatFormatting.GRAY);
                    tooltip.add(text);
                } else {
                    Component text = Component.translatable("info.confluence_delight.fluid_amount",
                            fluidNameKey, amountBuckets).withStyle(ChatFormatting.GRAY);
                    tooltip.add(text);
                }
            }
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor accessor) {
        if (accessor.getBlockEntity() instanceof PickleJarsBlockEntity entity) {
            FluidTank tank = entity.fluidTank;
            if (tank != null) {
                FluidStack fluidStack = tank.getFluid();
                if (!fluidStack.isEmpty()) {
                    compoundTag.putString("fluid", fluidStack.getHoverName().getString());
                    compoundTag.putInt("amount", fluidStack.getAmount());
                    compoundTag.putInt("capacity", tank.getCapacity());
                } else {
                    compoundTag.putString("fluid", "");
                    compoundTag.putInt("amount", 0);
                    compoundTag.putInt("capacity", tank.getCapacity());
                }
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }
}

