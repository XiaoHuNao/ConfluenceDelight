package com.xiaohunao.confluencedelight.common.item;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.registries.DeferredHolder;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BaseFoodItem extends Item {
    public BaseFoodItem(Properties properties) {
        super(properties);
    }

    public BaseFoodItem(FoodBuilder foodBuilder) {
        super(new Item.Properties()
                .food(foodBuilder.buildFood())
                .craftRemainder(foodBuilder.keeper)
        );
    }

    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        TextUtils.addFoodEffectTooltip(stack, tooltip::add, 1.0F, context.tickRate());
    }

    public static class FoodBuilder {
        public List<Consumer<FoodProperties.Builder>> effects = new ArrayList<>();
        public Item keeper = Items.AIR;


        public FoodProperties buildFood() {
            FoodProperties.Builder builder = new FoodProperties.Builder();
            effects.forEach(effectAdder -> effectAdder.accept(builder));
            return builder.build();
        }

        public FoodBuilder setKeeper(Item keeper) {
            this.keeper = keeper;
            return this;
        }
        public FoodBuilder setKeeper(DeferredHolder<Item,Item> keeper) {
            this.keeper = keeper.get();
            return this;
        }
        public FoodBuilder addEffect(Holder<MobEffect> effect, int time) {
            effects.add(builder -> builder.effect(()->new MobEffectInstance(effect, time),1));
            return this;
        }

    }
}