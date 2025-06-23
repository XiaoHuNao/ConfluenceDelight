package org.confluence.delight.common.event.game;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.CDNaturalBlocks;
import org.confluence.mod.common.init.item.FoodItems;

import java.util.Map;
import java.util.function.Supplier;

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
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        if (level.isClientSide()) return;
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        ItemStack heldItem = player.getItemInHand(hand);
        BlockPos pos = event.getPos();
        BlockState clickedState = level.getBlockState(pos);
        Block clickedBlock = clickedState.getBlock();
        Pair<Block, Supplier<? extends Block>> pair = ITEM_TO_BLOCK_MAP.get(heldItem.getItem());
        if (pair != null && clickedBlock.equals(pair.getLeft())) {
            Block newBlock = pair.getRight().get();
            level.setBlockAndUpdate(pos, newBlock.defaultBlockState());
            if (!player.isCreative()) {
                heldItem.shrink(1);
            }
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
        if (heldItem.is(FoodItems.BLACKCURRANT) && clickedState.is(Blocks.AZALEA) || clickedState.is(Blocks.FLOWERING_AZALEA)) {
            level.setBlockAndUpdate(pos, CDNaturalBlocks.BLACKCURRANT_SHRUB_BLOCK.get().defaultBlockState());
        } else if (heldItem.is(FoodItems.ELDERBERRY) && clickedState.is(Blocks.AZALEA) || clickedState.is(Blocks.FLOWERING_AZALEA)) {
            level.setBlockAndUpdate(pos, CDNaturalBlocks.ELDERBERRY_SHRUB_BLOCK.get().defaultBlockState());
        }
    }
}
