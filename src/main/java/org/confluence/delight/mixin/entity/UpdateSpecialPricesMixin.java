package org.confluence.delight.mixin.entity;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffer;
import org.confluence.delight.common.init.CDEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public class UpdateSpecialPricesMixin {
    @Inject(method = "updateSpecialPrices", at = @At("TAIL"))
    private void updateSpecialPrices(Player player, CallbackInfo ci) {
        if (player.hasEffect(CDEffects.MASTER_TRADER)) {
            MobEffectInstance effect = player.getEffect(CDEffects.MASTER_TRADER);
            for (MerchantOffer merchantoffer : ((Villager) (Object) this).getOffers()) {
                double discountRatio = 0.5;
                int baseCount = merchantoffer.getBaseCostA().getCount();
                int discountAmount = (int) Math.floor(discountRatio * baseCount);
                merchantoffer.addToSpecialPriceDiff(-Math.max(discountAmount, 1));
            }
        }
    }
}
