package org.confluence.delight.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;

import java.util.Optional;

public class CDFeature {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, ConfluenceDelight.MODID);
    public static final DeferredRegister<PlacementModifierType<?>> MODIFIER_TYPES = DeferredRegister.create(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, ConfluenceDelight.MODID);

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
        MODIFIER_TYPES.register(eventBus);
    }

    public static class Configured {}
    public static final class TreeGrowers {
        private static TreeGrower registerSmallTree(String name, ResourceKey<ConfiguredFeature<?, ?>> tree) {
            return new TreeGrower(ConfluenceDelight.MODID + ":" + name, Optional.empty(), Optional.of(tree), Optional.empty());
        }

        private static TreeGrower registerBigTree(String name, ResourceKey<ConfiguredFeature<?, ?>> tree) {
            return new TreeGrower(ConfluenceDelight.MODID + ":" + name, Optional.of(tree), Optional.empty(), Optional.empty());
        }
    }
}
