package org.confluence.delight.common.block.food;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.confluence.delight.common.init.CDFoodItems;
import org.confluence.delight.util.CDEffectData;
import org.confluence.mod.common.init.ModEffects;

import java.util.Arrays;

public class BlackCurrantDarkChocolatePieBlock extends BaseFoodBlock {
    public static final IntegerProperty BLOCK = IntegerProperty.create("block", 0, 4);

    public BlackCurrantDarkChocolatePieBlock() {
        super(12, 0.5f,
            CDEffectData.of(vectorwing.farmersdelight.common.registry.ModEffects.COMFORT, 600),
            CDEffectData.of(MobEffects.LUCK, 600),
            CDEffectData.of(ModEffects.MAGIC_POWER, 600));
        this.registerDefaultState(this.stateDefinition.any().setValue(BLOCK, 0));
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState clickedBlockState = context.getLevel().getBlockState(context.getClickedPos());
        if (clickedBlockState.is(this)) {
            int currentBlock = clickedBlockState.getValue(BLOCK);
            if (currentBlock < 4) {
                return clickedBlockState.cycle(BLOCK);
            }
        }
        return this.defaultBlockState();
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!stack.is(CDFoodItems.BLACKCURRANT_DARK_CHOCOLATE_PIE)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        } else {
            int currentPiece = state.getValue(BLOCK);
            if (currentPiece >= 4) {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            } else {
                if (!player.isCreative()) {
                    stack.shrink(1);
                }
                level.playSound(null, pos, SoundEvents.SLIME_BLOCK_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.setBlockAndUpdate(pos, state.setValue(BLOCK, currentPiece + 1));
                level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
                return ItemInteractionResult.SUCCESS;
            }
        }
    }

    @Override
    protected InteractionResult eat(LevelAccessor level, BlockPos pos, BlockState state, Player player) {
        ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
        int blockCount = state.getValue(BLOCK);
        if (!player.canEat(false)) return InteractionResult.PASS;
        player.getFoodData().eat(nutrition, calcSaturationModifier(nutrition, rawSaturation));
        RandomSource random = player.getRandom();
        Arrays.stream(effects).forEach(e -> {
            if (random.nextFloat() < e.probability()) {
                MobEffectInstance instance = new MobEffectInstance(e.effect(), e.duration(), e.level());
                player.addEffect(instance);
            }
        });
        player.playSound(SoundEvents.GENERIC_EAT);
        level.gameEvent(player, GameEvent.EAT, pos);
        if (!itemStack.is(CDFoodItems.BLACKCURRANT_DARK_CHOCOLATE_PIE.get())) {
            if (blockCount > 1) {
                level.setBlock(pos, state.setValue(BLOCK, blockCount - 1), 3);
            } else {
                level.removeBlock(pos, false);
                level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }
        }
        return InteractionResult.SUCCESS;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BLOCK);
    }
}
