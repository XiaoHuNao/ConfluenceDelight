package org.confluence.delight.common.init;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.common.SoundActions;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.lib.common.fluid.FluidTriple;

public class ModFluids {

    public static void initialize() {
    }

    public static final FluidTriple WINE = FluidTriple.builder(ConfluenceDelight.asResource("wine"))
            .typeProperties(properties -> properties
                    .density(900)
                    .canSwim(false)
                    .viscosity(1300)
                    .motionScale(0.011)
                    .canExtinguish(false)
                    .supportsBoating(true)
                    .rarity(Rarity.UNCOMMON)
                    .fallDistanceModifier(0.0F)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
            ).baseProperties(properties -> properties
                    .block(ModBlocks.WINE)
                    .bucket(ModItems.WINE_BUCKET)
            ).build();
}
