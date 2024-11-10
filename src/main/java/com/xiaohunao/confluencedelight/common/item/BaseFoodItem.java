package com.xiaohunao.confluencedelight.common.item;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BaseFoodItem extends Item {
    public FoodBuilder foodBuilder;
    public BaseFoodItem(Properties properties) {
        super(properties);
        this.foodBuilder = new FoodBuilder();
    }

    public BaseFoodItem(FoodBuilder foodBuilder) {
        super(new Item.Properties()
                .food(foodBuilder.buildFood())
                .craftRemainder(foodBuilder.keeper)
        );
        this.foodBuilder = foodBuilder;
    }


    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
        TextUtils.addFoodEffectTooltip(stack, tooltip::add, 1.0F, context.tickRate());
    }

    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack stack1 = super.finishUsingItem(stack, level, livingEntity);
        foodBuilder.specialEffects.forEach(effect -> effect.accept(stack, livingEntity));
       return stack1;
    }


    public static class FoodBuilder {
        public List<Consumer<FoodProperties.Builder>> effects = new ArrayList<>();
        public List<BiConsumer<ItemStack, LivingEntity>> specialEffects = new ArrayList<>();
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
        public FoodBuilder addSpecialEffect(BiConsumer<ItemStack, LivingEntity> specialEffect) {
            specialEffects.add(specialEffect);
            return this;
        }
    }

    BiFunction<Holder<MobEffect>,Integer,FoodBuilder> EFFECTIVE_FOOD = (effect, time) -> new FoodBuilder().addEffect(effect, time);
}