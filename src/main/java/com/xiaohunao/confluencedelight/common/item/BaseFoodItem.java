package com.xiaohunao.confluencedelight.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class BaseFoodItem extends Item {
    public FoodBuilder foodBuilder;
    public BaseFoodItem(Properties properties) {
        super(properties);
        this.foodBuilder = new FoodBuilder();
    }

    public BaseFoodItem(FoodBuilder foodBuilder) {
        super(new Item.Properties()
                .food(foodBuilder.build())
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

        List<BiConsumer<ItemStack, LivingEntity>> specialEffects = new ArrayList<>();
        FoodProperties.Builder builder = new FoodProperties.Builder();

        public FoodBuilder modify(Consumer<FoodProperties.Builder> modify) {
            modify.accept(builder);
            return this;
        }

        public FoodBuilder addSpecialEffect(BiConsumer<ItemStack, LivingEntity> specialEffect) {
            specialEffects.add(specialEffect);
            return this;
        }

        public FoodProperties build() {
            return builder.build();
        }
    }

}