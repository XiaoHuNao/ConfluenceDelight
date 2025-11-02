package org.confluence.delight.common.block.common;

import com.mojang.serialization.MapCodec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDItems;

import java.util.Map;

public class WineCauldronBlock extends AbstractCauldronBlock {
    public static final MapCodec<WineCauldronBlock> CODEC = simpleCodec(WineCauldronBlock::new);
    public static final CauldronInteraction.InteractionMap WINE = Util.make(CauldronInteraction.newInteractionMap("confluence_delight_wine"), map -> {
        Map<Item, CauldronInteraction> interactionMap = map.map();
        interactionMap.put(Items.BUCKET, (blockState, level, blockPos, player, hand, itemStack) -> CauldronInteraction.fillBucket(
            blockState, level, blockPos, player, hand, itemStack,
            CDItems.WINE_BUCKET.get().getDefaultInstance(),
            blockState1 -> true,
            SoundEvents.BUCKET_FILL
        ));
        CauldronInteraction.addDefaultInteractions(interactionMap);
    });
    public static final CauldronInteraction FILL_WINE = (blockState, level, blockPos, player, hand, itemStack) -> CauldronInteraction.emptyBucket(
        level, blockPos, player, hand, itemStack,
        CDBlocks.WINE_CAULDRON.get().defaultBlockState(),
        SoundEvents.BUCKET_EMPTY
    );

    public WineCauldronBlock(Properties properties) {
        super(properties, WINE);
    }

    @Override
    protected MapCodec<WineCauldronBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean isFull(BlockState blockState) {
        return true;
    }

    @Override
    protected double getContentHeight(BlockState blockState) {
        return 0.9375;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return 3;
    }
}
