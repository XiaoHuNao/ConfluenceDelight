package org.confluence.delight.common.event.game.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.CDEffects;
import org.confluence.delight.common.init.CDNaturalBlocks;
import org.confluence.mod.common.init.item.FoodItems;

import java.util.Map;
import java.util.function.Supplier;

import static net.minecraft.world.item.Item.getPlayerPOVHitResult;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, modid = ConfluenceDelight.MODID)
public class PlayerEvents {

    private static final Map<ItemLike, Pair<Block, Supplier<? extends Block>>> ITEM_TO_BLOCK_MAP = Map.ofEntries(
            Map.entry(Items.APPLE, Pair.of(Blocks.OAK_SAPLING, CDNaturalBlocks.APPLE_SAPLING)),
            Map.entry(FoodItems.APRICOT, Pair.of(Blocks.BIRCH_SAPLING, CDNaturalBlocks.APRICOT_SAPLING)),
            Map.entry(FoodItems.GRAPE_FRUIT, Pair.of(Blocks.JUNGLE_SAPLING, CDNaturalBlocks.GRAPEFRUIT_SAPLING)),
            Map.entry(FoodItems.LEMON, Pair.of(Blocks.SPRUCE_SAPLING, CDNaturalBlocks.LEMON_SAPLING)),
            Map.entry(FoodItems.PEACH, Pair.of(Blocks.OAK_SAPLING, CDNaturalBlocks.PEACH_SAPLING)),
            Map.entry(FoodItems.CHERRY, Pair.of(Blocks.SPRUCE_SAPLING, CDNaturalBlocks.CHERRY_SAPLING)),
            Map.entry(FoodItems.PLUM, Pair.of(Blocks.BIRCH_SAPLING, CDNaturalBlocks.PLUM_SAPLING)),
            Map.entry(FoodItems.BLOOD_ORANGE, Pair.of(Blocks.JUNGLE_SAPLING, CDNaturalBlocks.BLOOD_ORANGE_SAPLING)),
            Map.entry(FoodItems.RAMBUTAN, Pair.of(Blocks.JUNGLE_SAPLING, CDNaturalBlocks.RAMBUTAN_SAPLING)),
            Map.entry(FoodItems.MANGO, Pair.of(Blocks.JUNGLE_SAPLING, CDNaturalBlocks.MANGO_SAPLING)),
            Map.entry(FoodItems.BANANA, Pair.of(Blocks.JUNGLE_SAPLING, CDNaturalBlocks.BANANA_SAPLING)),
            Map.entry(FoodItems.COCONUT, Pair.of(Blocks.JUNGLE_SAPLING, CDNaturalBlocks.COCONUT_SAPLING)),
            Map.entry(FoodItems.STAR_FRUIT, Pair.of(Blocks.OAK_SAPLING, CDNaturalBlocks.STAR_FRUIT_SAPLING)),
            Map.entry(FoodItems.POMEGRANATE, Pair.of(Blocks.BIRCH_SAPLING, CDNaturalBlocks.POMEGRANATE_SAPLING))
    );

    @SubscribeEvent
    public static void Player$RightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        if (level.isClientSide()) return;
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        ItemStack heldItem = player.getItemInHand(hand);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        if (blockhitresult.getType() != HitResult.Type.BLOCK) return;
        BlockPos targetPos = blockhitresult.getBlockPos();
        BlockState clickedState = level.getBlockState(targetPos);
        FluidState clickedFluidState = level.getFluidState(targetPos);
        Pair<Block, Supplier<? extends Block>> pair = ITEM_TO_BLOCK_MAP.get(heldItem.getItem());
        if (pair != null && clickedState.getBlock().equals(pair.getLeft())) {
            Block newBlock = pair.getRight().get();
            level.setBlockAndUpdate(targetPos, newBlock.defaultBlockState());
            if (!player.isCreative()) {
                heldItem.shrink(1);
            }
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
            return;
        }
        if (heldItem.is(FoodItems.BLACKCURRANT) && (clickedState.is(Blocks.AZALEA) || clickedState.is(Blocks.FLOWERING_AZALEA))) {
            level.setBlockAndUpdate(targetPos, CDNaturalBlocks.BLACKCURRANT_SHRUB_BLOCK.get().defaultBlockState());
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        } else if (heldItem.is(FoodItems.ELDERBERRY) && (clickedState.is(Blocks.AZALEA) || clickedState.is(Blocks.FLOWERING_AZALEA))) {
            level.setBlockAndUpdate(targetPos, CDNaturalBlocks.ELDERBERRY_SHRUB_BLOCK.get().defaultBlockState());
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
    }

    @SubscribeEvent
    public static void PlayerTick$Post(PlayerTickEvent.Post event) {
        int GROWTH_RADIUS = 3;
        Player player = event.getEntity();
        Level level = player.level();
        if (player.hasEffect(CDEffects.HARVEST) && level instanceof ServerLevel serverLevel) {
            BlockPos center = player.blockPosition();
            for (BlockPos pos : BlockPos.withinManhattan(center, GROWTH_RADIUS, GROWTH_RADIUS, GROWTH_RADIUS)) {
                Block block = level.getBlockState(pos).getBlock();
                if (block instanceof CropBlock) {
                    level.getBlockState(pos).randomTick(serverLevel, pos, level.random);
                }
            }
        }
    }
}
