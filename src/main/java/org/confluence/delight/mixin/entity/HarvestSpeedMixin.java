package org.confluence.delight.mixin.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.confluence.delight.common.init.CDEffects;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

public class HarvestSpeedMixin {

    @Mixin(Chicken.class)
    public static class ChickenMixin {
        @Unique
        private boolean confluenceDelight$harvestEffectApplied = false;

        @Inject(method = "aiStep", at = @At("HEAD"))
        private void onAiStep(CallbackInfo ci) {
            Chicken chicken = (Chicken) (Object) this;
            if (!chicken.level().isClientSide && chicken.isAlive() && !chicken.isChickenJockey()) {
                if (chicken.hasEffect(CDEffects.HARVEST) && !confluenceDelight$harvestEffectApplied) {
                    chicken.eggTime = Math.max(1, chicken.eggTime / 2);
                    confluenceDelight$harvestEffectApplied = true;
                } else if (!chicken.hasEffect(CDEffects.HARVEST)) {
                    confluenceDelight$harvestEffectApplied = false;
                }
            }
        }
    }

    @Mixin(EatBlockGoal.class)
    public static class EatBlockGoalMixin {
        @Shadow @Final private Mob mob;
        @Shadow @Final private static Predicate<BlockState> IS_TALL_GRASS;
        @Shadow @Final private Level level;
        /**
         * @author cooobird
         * @reason Increase the frequency with which adult sheep eat grass
         */
        @Overwrite
        public boolean canUse() {
            int chance = this.mob.isBaby() ? 50 : 500;
            if (this.mob.getRandom().nextInt(chance) != 0) {
                return false;
            } else {
                BlockPos blockpos = this.mob.blockPosition();
                return IS_TALL_GRASS.test(this.level.getBlockState(blockpos)) || this.level.getBlockState(blockpos.below()).is(Blocks.GRASS_BLOCK);
            }
        }
    }
}
