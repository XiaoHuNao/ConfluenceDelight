package org.confluence.delight.common.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.ModFoodItems;

@EventBusSubscriber(modid = ConfluenceDelight.MODID, bus = EventBusSubscriber.Bus.GAME)
public final class LivingEntityEvent {

    @SubscribeEvent
    public static void livingEntityUseItemFinish(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        ItemStack itemStack = event.getItem();
        RandomSource random = player.getRandom();
        if (itemStack.is(ModFoodItems.CRUSHED_CHILLI.get()) && random.nextInt(2) == 0) {
            player.igniteForTicks(40);
        } else if (itemStack.is(ModFoodItems.SPICY_PICKLED_FISH.get())) {
            player.igniteForSeconds(60.0f);
        }
    }
}
