package org.confluence.delight.common.event.game;

import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.CDEffects;

import java.util.List;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, modid = ConfluenceDelight.MODID)
public class PlayerEvents {

    @SubscribeEvent
    public static void onPlayerInteractEntity(PlayerInteractEvent.EntityInteractSpecific event) {
        if (!(event.getTarget() instanceof Villager villager)) return;
        Player player = event.getEntity();
        if (!player.hasEffect(CDEffects.MASTER_TRADER)) return;
        List<MerchantOffer> offers = villager.getOffers();
        for (MerchantOffer offer : offers) {
            ItemStack price = offer.getCostA();
            int originalCount = price.getCount();
            int newCount = Math.max(1, originalCount / 2);
            price.setCount(newCount);
        }
    }
}
