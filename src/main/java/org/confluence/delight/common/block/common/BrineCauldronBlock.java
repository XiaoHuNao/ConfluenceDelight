package org.confluence.delight.common.block.common;

import com.mojang.serialization.MapCodec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDItems;
import org.confluence.lib.util.LibUtils;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.Map;

public class BrineCauldronBlock extends AbstractCauldronBlock implements EntityBlock {
    public static final MapCodec<BrineCauldronBlock> CODEC = simpleCodec(BrineCauldronBlock::new);
    public static final CauldronInteraction.InteractionMap BRINE = Util.make(CauldronInteraction.newInteractionMap("confluence_delight_brine"), map -> {
        Map<Item, CauldronInteraction> interactionMap = map.map();
        interactionMap.put(Items.BUCKET, (blockState, level, blockPos, player, hand, itemStack) -> CauldronInteraction.fillBucket(
                blockState, level, blockPos, player, hand, itemStack,
                CDItems.BRINE_BUCKET.get().getDefaultInstance(),
                blockState1 -> true,
                SoundEvents.BUCKET_FILL
        ));
        CauldronInteraction.addDefaultInteractions(interactionMap);
    });
    public static final CauldronInteraction FILL_BRINE = (blockState, level, blockPos, player, hand, itemStack) -> CauldronInteraction.emptyBucket(
            level, blockPos, player, hand, itemStack,
            CDBlocks.BRINE_CAULDRON.get().defaultBlockState(),
            SoundEvents.BUCKET_EMPTY
    );

    public BrineCauldronBlock(Properties properties) {
        super(properties, BRINE);
    }

    @Override
    protected MapCodec<BrineCauldronBlock> codec() {
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

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new Entity(blockPos, blockState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : LibUtils.getTicker(blockEntityType, CDBlocks.BRINE_CAULDRON_ENTITY.get(), BrineCauldronBlock.Entity::tick);
    }

    public static class Entity extends BlockEntity {
        private static int heatTime = 0;

        public Entity(BlockPos pos, BlockState blockState) {
            super(CDBlocks.BRINE_CAULDRON_ENTITY.get(), pos, blockState);
        }

        public static void tick(Level level, BlockPos pos, BlockState state, Entity entity) {
            BlockPos below = pos.below();
            BlockState belowState = level.getBlockState(below);
            if (belowState.is(ModTags.HEAT_SOURCES)) {
                if (++heatTime >= 200) {
                    level.setBlockAndUpdate(pos, CDBlocks.SALT_CAULDRON.get().defaultBlockState());
                    heatTime = 0;
                }
            }
        }

        @Override
        public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
            super.loadAdditional(nbt, registries);
            heatTime = nbt.getInt("heatTime");
        }

        @Override
        protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
            super.saveAdditional(nbt, registries);
            nbt.putInt("heatTime", heatTime);
        }
    }
}
