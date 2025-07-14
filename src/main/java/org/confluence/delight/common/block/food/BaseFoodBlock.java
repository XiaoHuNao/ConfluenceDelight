package org.confluence.delight.common.block.food;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import org.confluence.delight.util.CDEffectData;

import javax.annotation.Nullable;
import java.util.Arrays;

public class BaseFoodBlock extends Block {
    protected final int nutrition;
    protected final float rawSaturation;
    protected final CDEffectData[] effects;
    protected final ItemLike returnItem;

    public BaseFoodBlock(int nutrition, float rawSaturation, CDEffectData... effects) {
        this(nutrition, rawSaturation, null, effects);
    }

    public BaseFoodBlock(int nutrition, float rawSaturation, @Nullable ItemLike returnItem, CDEffectData... effects) {
        super(BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY));
        this.nutrition = nutrition;
        this.rawSaturation = rawSaturation;
        this.returnItem = returnItem;
        this.effects = effects;
    }

    public static float calcSaturationModifier(int nutrition, float rawSaturation) {
        return rawSaturation / nutrition / 2;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            InteractionResult result = eat(level, pos, state, player);
            if (result.consumesAction()) return InteractionResult.SUCCESS;
            if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) return InteractionResult.CONSUME;
        }
        return eat(level, pos, state, player);
    }

    protected InteractionResult eat(LevelAccessor level, BlockPos pos, BlockState state, Player player) {
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
        level.removeBlock(pos, false);
        level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        if (returnItem != null) {
            ItemStack returnStack = new ItemStack(returnItem);
            player.getInventory().add(returnStack);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return facing == Direction.DOWN && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid();
    }
}

