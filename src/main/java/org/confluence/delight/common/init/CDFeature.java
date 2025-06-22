package org.confluence.delight.common.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.worldgen.feature.FruitTreeFeature;

import java.util.Optional;
import java.util.function.Supplier;

public class CDFeature {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, ConfluenceDelight.MODID);
    public static final DeferredRegister<PlacementModifierType<?>> MODIFIER_TYPES = DeferredRegister.create(BuiltInRegistries.PLACEMENT_MODIFIER_TYPE, ConfluenceDelight.MODID);

    public static final Supplier<FruitTreeFeature> FRUIT_TREE = FEATURES.register("fruit_tree", () -> new FruitTreeFeature(FruitTreeFeature.Config.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
        MODIFIER_TYPES.register(eventBus);
    }

    public static class Configured {
        public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ConfluenceDelight.asResource("apple_tree"));
        public static final ResourceKey<ConfiguredFeature<?, ?>> APRICOT = ResourceKey.create(Registries.CONFIGURED_FEATURE, ConfluenceDelight.asResource("apricot_tree"));
        public static final ResourceKey<ConfiguredFeature<?, ?>> GRAPEFRUIT = ResourceKey.create(Registries.CONFIGURED_FEATURE, ConfluenceDelight.asResource("grapefruit_tree"));
        public static final ResourceKey<ConfiguredFeature<?, ?>> LEMON = ResourceKey.create(Registries.CONFIGURED_FEATURE, ConfluenceDelight.asResource("lemon_tree"));
        public static final ResourceKey<ConfiguredFeature<?, ?>> PEACH = ResourceKey.create(Registries.CONFIGURED_FEATURE, ConfluenceDelight.asResource("peach_tree"));
        public static final ResourceKey<ConfiguredFeature<?, ?>> CHERRY = ResourceKey.create(Registries.CONFIGURED_FEATURE, ConfluenceDelight.asResource("cherry_tree"));
        public static final ResourceKey<ConfiguredFeature<?, ?>> PLUM = ResourceKey.create(Registries.CONFIGURED_FEATURE, ConfluenceDelight.asResource("plum_tree"));
        public static final ResourceKey<ConfiguredFeature<?, ?>> BLOOD_ORANGE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ConfluenceDelight.asResource("blood_orange_tree"));
        public static final ResourceKey<ConfiguredFeature<?, ?>> RAMBUTAN = ResourceKey.create(Registries.CONFIGURED_FEATURE, ConfluenceDelight.asResource("rambutan_tree"));
        public static final ResourceKey<ConfiguredFeature<?, ?>> MANGO = ResourceKey.create(Registries.CONFIGURED_FEATURE, ConfluenceDelight.asResource("mango_tree"));
        public static final ResourceKey<ConfiguredFeature<?, ?>> BANANA = ResourceKey.create(Registries.CONFIGURED_FEATURE, ConfluenceDelight.asResource("banana_tree"));
        public static final ResourceKey<ConfiguredFeature<?, ?>> COCONUT = ResourceKey.create(Registries.CONFIGURED_FEATURE, ConfluenceDelight.asResource("coconut_tree"));
        public static final ResourceKey<ConfiguredFeature<?, ?>> STAR_FRUIT = ResourceKey.create(Registries.CONFIGURED_FEATURE, ConfluenceDelight.asResource("star_fruit_tree"));
        public static final ResourceKey<ConfiguredFeature<?, ?>> POMEGRANATE = ResourceKey.create(Registries.CONFIGURED_FEATURE, ConfluenceDelight.asResource("pomegranate_tree"));
    }

    public static final class TreeGrowers {
        public static final TreeGrower APPLE_GROWER = registerSmallTree("apple", Configured.APPLE);
        public static final TreeGrower APRICOT_GROWER = registerSmallTree("apricot", Configured.APRICOT);
        public static final TreeGrower GRAPEFRUIT_GROWER = registerSmallTree("grapefruit", Configured.GRAPEFRUIT);
        public static final TreeGrower LEMON_GROWER = registerSmallTree("lemon", Configured.LEMON);
        public static final TreeGrower PEACH_GROWER = registerSmallTree("peach", Configured.PEACH);
        public static final TreeGrower CHERRY_GROWER = registerSmallTree("cherry", Configured.CHERRY);
        public static final TreeGrower PLUM_GROWER = registerSmallTree("plum", Configured.PLUM);
        public static final TreeGrower BLOOD_ORANGE_GROWER = registerSmallTree("blood_orange", Configured.BLOOD_ORANGE);
        public static final TreeGrower RAMBUTAN_GROWER = registerSmallTree("rambutan", Configured.RAMBUTAN);
        public static final TreeGrower MANGO_GROWER = registerSmallTree("mango", Configured.MANGO);
        public static final TreeGrower BANANA_GROWER = registerSmallTree("banana", Configured.BANANA);
        public static final TreeGrower COCONUT_GROWER = registerSmallTree("coconut", Configured.COCONUT);
        public static final TreeGrower STAR_FRUIT_GROWER = registerSmallTree("star_fruit", Configured.STAR_FRUIT);
        public static final TreeGrower POMEGRANATE_GROWER = registerSmallTree("pomegranate", Configured.POMEGRANATE);

        private static TreeGrower registerSmallTree(String name, ResourceKey<ConfiguredFeature<?, ?>> tree) {
            return new TreeGrower(ConfluenceDelight.MODID + ":" + name, Optional.empty(), Optional.of(tree), Optional.empty());
        }

        private static TreeGrower registerBigTree(String name, ResourceKey<ConfiguredFeature<?, ?>> tree) {
            return new TreeGrower(ConfluenceDelight.MODID + ":" + name, Optional.of(tree), Optional.empty(), Optional.empty());
        }
    }
}
