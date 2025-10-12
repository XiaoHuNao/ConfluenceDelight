package org.confluence.delight.common.item.food;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.attachment.CDEverBeneficial;
import org.confluence.terra_curio.common.init.TCAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CDEverBeneficialItem extends CDFoodItem {
    public static final Post DO_NOTHING = (id, player, everBeneficial, isRespawn) -> {
    };

    public static final Beneficial UTILITY_APPLE = new Beneficial(ConfluenceDelight.asResource("utility_apple"), CDEverBeneficial::setUtilityAppleUsed, (id, player, data, isRespawn) -> {
        AttributeInstance miningEfficiency = player.getAttributes().getInstance(Attributes.MINING_EFFICIENCY);
        if (miningEfficiency == null) return;
        miningEfficiency.addOrReplacePermanentModifier(new AttributeModifier(id, 5.0, AttributeModifier.Operation.ADD_VALUE));
    });
    public static final Beneficial SPEEDY_COKE = new Beneficial(ConfluenceDelight.asResource("speedy_coke"), CDEverBeneficial::setSpeedyCokeUsed, (id, player, data, isRespawn) -> {
        AttributeInstance sneakingSpeed = player.getAttributes().getInstance(Attributes.SNEAKING_SPEED);
        AttributeInstance movementEfficiency = player.getAttributes().getInstance(Attributes.MOVEMENT_EFFICIENCY);
        if (sneakingSpeed == null || movementEfficiency == null) return;
        sneakingSpeed.addOrReplacePermanentModifier(new AttributeModifier(id, 0.4, AttributeModifier.Operation.ADD_VALUE));
        movementEfficiency.addOrReplacePermanentModifier(new AttributeModifier(id, 0.5, AttributeModifier.Operation.ADD_VALUE));
    });
    public static final Beneficial EZ_CONSTANT = new Beneficial(ConfluenceDelight.asResource("ez_constant"), CDEverBeneficial::setEzConstantUsed, (id, player, data, isRespawn) -> {
        AttributeInstance rangeDamage = player.getAttributes().getInstance(TCAttributes.getRangedDamage());
        if (rangeDamage == null) return;
        rangeDamage.addOrReplacePermanentModifier(new AttributeModifier(id, 0.5, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    });

    private final Beneficial beneficial;
    private final List<Component> tooltips;
    private final SoundEvent drinkSound;
    private final UseAnim drinkAnimation;

    public CDEverBeneficialItem(Beneficial beneficial, Supplier<FoodProperties> foodProperties) {
        super(new Properties().food(foodProperties.get()));
        this.beneficial = beneficial;
        this.drinkSound = null;
        this.drinkAnimation = null;
        this.tooltips = new ArrayList<>();
    }

    public CDEverBeneficialItem(SoundEvent drinkSound, UseAnim drinkAnimation, Beneficial beneficial, Supplier<FoodProperties> foodProperties) {
        super(new Properties().food(foodProperties.get()));
        this.beneficial = beneficial;
        this.drinkSound = drinkSound;
        this.drinkAnimation = drinkAnimation;
        this.tooltips = new ArrayList<>();
    }

    public CDEverBeneficialItem(Beneficial beneficial, Supplier<FoodProperties> foodProperties, List<Component> tooltips) {
        super(new Properties().food(foodProperties.get()));
        this.beneficial = beneficial;
        this.tooltips = tooltips;
        this.drinkSound = null;
        this.drinkAnimation = null;
    }

    public CDEverBeneficialItem(SoundEvent drinkSound, UseAnim drinkAnimation, Beneficial beneficial, Supplier<FoodProperties> foodProperties, List<Component> tooltips) {
        super(new Properties().food(foodProperties.get()));
        this.beneficial = beneficial;
        this.tooltips = tooltips;
        this.drinkSound = drinkSound;
        this.drinkAnimation = drinkAnimation;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return drinkAnimation != null ? drinkAnimation : UseAnim.EAT;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return drinkSound;
    }

    @Override
    public SoundEvent getEatingSound() {
        return drinkSound != null ? SoundEvents.EMPTY : SoundEvents.GENERIC_EAT;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        super.finishUsingItem(stack, level, livingEntity);
        if (livingEntity instanceof ServerPlayer player) {
            CDEverBeneficial data = CDEverBeneficial.of(player);
            if (beneficial.pre.test(data)) {
                beneficial.post.accept(beneficial.id, player, data, false);
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.addAll(tooltips);
    }

    public static List<Component> getTooltipsFromString(String id, int lineCount, ChatFormatting chatFormatting) {
        if (lineCount == 1) {
            return Collections.singletonList(Component.translatable("tooltip.item.confluence_delight." + id + ".0").withStyle(chatFormatting));
        }
        List<Component> components = new ArrayList<>();
        for (int i = 0; i < lineCount; i++) {
            components.add(Component.translatable("tooltip.item.confluence_delight." + id + "." + i).withStyle(chatFormatting));
        }
        return components;
    }

    public record Beneficial(ResourceLocation id, Predicate<CDEverBeneficial> pre, Post post) {
        public void recovery(CDEverBeneficial everBeneficial, Predicate<CDEverBeneficial> condition, ServerPlayer player) {
            if (condition.test(everBeneficial)) {
                post.accept(id, player, everBeneficial, true);
            }
        }
    }

    @FunctionalInterface
    public interface Post {
        void accept(ResourceLocation id, ServerPlayer player, CDEverBeneficial everBeneficial, boolean isRespawn);
    }
}
