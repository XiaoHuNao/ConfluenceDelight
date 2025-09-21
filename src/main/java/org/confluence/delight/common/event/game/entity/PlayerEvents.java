package org.confluence.delight.common.event.game.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.attachment.CDEverBeneficial;
import org.confluence.delight.common.init.CDEffects;
import org.confluence.delight.common.init.CDRecipes;
import org.confluence.delight.common.item.food.CDEverBeneficialItem;
import org.confluence.delight.common.recipe.BlockInteractionRecipe;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, modid = ConfluenceDelight.MODID)
public class PlayerEvents {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        if (level.isClientSide()) return;
        Player player = event.getEntity();
        ItemStack heldItem = player.getItemInHand(event.getHand());
        BlockPos targetPos = event.getPos();
        BlockState clickedState = level.getBlockState(targetPos);
        for (RecipeHolder<BlockInteractionRecipe> recipe : level.getRecipeManager().getAllRecipesFor(CDRecipes.BLOCK_INTERACTION_TYPE.get())) {
            if (recipe.value().matchesItem(heldItem) && recipe.value().matchesBlock(clickedState)) {
                if (recipe.value().transformBlock(level, targetPos, heldItem, player)) {
                    event.setCanceled(true);
                    event.setCancellationResult(InteractionResult.SUCCESS);
                    return;
                }
            }
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

    @SubscribeEvent
    public static void respawn(PlayerEvent.PlayerRespawnEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        CDEverBeneficial cdEverBeneficial = CDEverBeneficial.of(player);
        CDEverBeneficialItem.UTILITY_APPLE.recovery(cdEverBeneficial, CDEverBeneficial::isAegisAppleUsed, player);
        CDEverBeneficialItem.SPEEDY_COKE.recovery(cdEverBeneficial, CDEverBeneficial::isSpeedyCokeUsed, player);
    }
}
