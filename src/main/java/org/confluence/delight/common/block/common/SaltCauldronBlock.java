package org.confluence.delight.common.block.common;

import com.mojang.serialization.MapCodec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.confluence.delight.common.init.CDBlocks;

public class SaltCauldronBlock extends AbstractCauldronBlock {
    public static final MapCodec<SaltCauldronBlock> CODEC = simpleCodec(SaltCauldronBlock::new);
    public static final CauldronInteraction.InteractionMap DO_NOTHING = Util.make(CauldronInteraction.newInteractionMap("confluence_delight_do_nothing"), map -> {
    });
    public static final CauldronInteraction FILL_SALT = (blockState, level, blockPos, player, hand, itemStack) -> {
        if (!level.isClientSide) {
            ItemStack filledStack = CDBlocks.SALT_BLOCK.toStack();
            player.setItemInHand(hand, ItemStack.EMPTY);
            player.awardStat(Stats.FILL_CAULDRON);
            player.awardStat(Stats.ITEM_USED.get(filledStack.getItem()));
            level.setBlockAndUpdate(blockPos, CDBlocks.SALT_CAULDRON.get().defaultBlockState());
            level.gameEvent(null, GameEvent.FLUID_PLACE, blockPos);
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    };


    public SaltCauldronBlock(Properties properties) {
        super(properties, DO_NOTHING);
    }

    @Override
    protected MapCodec<SaltCauldronBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean isFull(BlockState state) {
        return true;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            ItemStack filledStack = CDBlocks.SALT_BLOCK.toStack();
            if (player.hasInfiniteMaterials()) {
                if (!player.getInventory().contains(filledStack)) {
                    player.getInventory().add(filledStack);
                }
            } else {
                if (!player.getInventory().add(filledStack)) {
                    player.drop(filledStack, false);
                }
            }
            player.awardStat(Stats.USE_CAULDRON);
            level.setBlockAndUpdate(pos, Blocks.CAULDRON.defaultBlockState());
            level.gameEvent(null, GameEvent.FLUID_PICKUP, pos);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
