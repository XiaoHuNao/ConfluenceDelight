package org.confluence.delight.common.block.function.crafting;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.lib.util.LibUtils;
import org.jetbrains.annotations.Nullable;

public class MillStoneBlock extends BaseEntityBlock {
    public static final MapCodec<MillStoneBlock> CODEC = simpleCodec(MillStoneBlock::new);

    public MillStoneBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<MillStoneBlock> codec() {
        return CODEC;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pLevel.getBlockEntity(pPos) instanceof MillStoneBlockEntity entity) {
            Containers.dropContents(pLevel, pPos, entity.itemHandler.getItems());
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof MillStoneBlockEntity millStone)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        ItemStack handItem = player.getItemInHand(hand);
        if (handItem.isEmpty() && player.isShiftKeyDown()) {
            ItemStack extracted = millStone.takeItem(-1);
            if (!extracted.isEmpty()) {
                if (!level.isClientSide) {
                    if (!player.getInventory().add(extracted)) {
                        player.drop(extracted, false);
                    }
                }
                millStone.setChanged();
                return success(level);
            }
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        } else {
            ItemStack remainder = millStone.addItem(handItem.copy());
            if (remainder.getCount() != handItem.getCount()) {
                if (!level.isClientSide) {
                    player.setItemInHand(hand, remainder);
                }
                millStone.setChanged();
                return success(level);
            }
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
    }

    private ItemInteractionResult success(Level level) {
        return ItemInteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }


    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof MillStoneBlockEntity millStone) {
                if (millStone.useCooldown > 0) {
                    return InteractionResult.PASS;
                }
                MillStoneBlockEntity.onEntityWork(level, pos, state, millStone);
                player.swing(player.getUsedItemHand(), true);
                millStone.setChanged();
                millStone.useCooldown = 20;
                return InteractionResult.sidedSuccess(false);
            }
        }
        return InteractionResult.PASS;
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MillStoneBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : LibUtils.getTicker(blockEntityType, CDBlocks.MILLSTONE_BLOCK_ENTITY.get(), MillStoneBlockEntity::tick);
    }
}
