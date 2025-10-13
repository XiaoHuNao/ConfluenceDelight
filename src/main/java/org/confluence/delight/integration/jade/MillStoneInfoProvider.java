package org.confluence.delight.integration.jade;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec2;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.block.function.crafting.MillStoneBlockEntity;
import org.confluence.mod.Confluence;
import org.jetbrains.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.StreamServerDataProvider;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElementHelper;

import java.util.List;

public class MillStoneInfoProvider implements IBlockComponentProvider, StreamServerDataProvider<BlockAccessor, MillStoneInfoProvider.Data> {
    public static final MillStoneInfoProvider INSTANCE = new MillStoneInfoProvider();
    public static final ResourceLocation UID = ConfluenceDelight.asResource("jade_millstone_info");

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor blockAccessor, IPluginConfig config) {
        this.decodeFromData(blockAccessor).ifPresent(data -> {
            IElementHelper helper = IElementHelper.get();
            tooltip.add(helper.item(data.inventory.get(0)));
            tooltip.append(helper.item(data.inventory.get(1)));
            tooltip.append(helper.spacer(4, 0));
            tooltip.append(helper.progress(data.total > 0 ? (float) data.progress / data.total : 0f).translate(new Vec2(-2.0F, 0.0F)));
            tooltip.append(helper.item(data.inventory.get(2)));
        });
    }


    @Override
    public @Nullable MillStoneInfoProvider.Data streamData(BlockAccessor blockAccessor) {
        if (!(blockAccessor.getBlockEntity() instanceof MillStoneBlockEntity millStoneBlockEntity)) {return null;}
        float progress = Math.max(0, millStoneBlockEntity.craftProgress);
        int total = Math.max(1, millStoneBlockEntity.craftTotalTime);
        return new Data(progress, total, List.of(
                        millStoneBlockEntity.getItem(0),
                        millStoneBlockEntity.getItem(1),
                        millStoneBlockEntity.getItem(2)));
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, Data> streamCodec() {
        return Data.STREAM_CODEC;
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    public record Data(float progress, int total, List<ItemStack> inventory) {
        public static final StreamCodec<RegistryFriendlyByteBuf, MillStoneInfoProvider.Data> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.FLOAT, MillStoneInfoProvider.Data::progress,
                ByteBufCodecs.VAR_INT, MillStoneInfoProvider.Data::total,
                ItemStack.OPTIONAL_LIST_STREAM_CODEC,
                MillStoneInfoProvider.Data::inventory,
                MillStoneInfoProvider.Data::new);

        public float progress() {
            return this.progress;
        }

        public int total() {
            return this.total;
        }

        public List<ItemStack> inventory() {
            return this.inventory;
        }

    }
}
