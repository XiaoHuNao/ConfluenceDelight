package org.confluence.delight.common.data.gen;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import org.apache.commons.compress.archivers.dump.DumpArchiveEntry;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDFeature;
import org.confluence.delight.common.init.CDNaturalBlocks;
import org.confluence.delight.common.worldgen.feature.CoconutTreeFeature;
import org.confluence.delight.common.worldgen.feature.FruitTreeFeature;
import org.confluence.mod.common.block.natural.PalmLeaves;
import org.confluence.mod.common.init.block.NatureBlocks;

import static org.confluence.delight.common.block.natural.BaseFruitTreeLeaveBlock.CAN_GROW;
import static org.confluence.delight.common.init.CDNaturalBlocks.COCONUT_TREE_TOP_BLOCK;

public class CDDataProvider {
    public static final RegistrySetBuilder DATA_BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ConfiguredFeatures::bootstrap);

    private static class ConfiguredFeatures {

        public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
            fruitTree(context, CDFeature.Configured.APPLE, Blocks.OAK_LOG, CDNaturalBlocks.APPLE_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.APPLE_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
            fruitTree(context, CDFeature.Configured.APRICOT, Blocks.BIRCH_LOG, CDNaturalBlocks.APRICOT_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.APRICOT_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
            fruitTree(context, CDFeature.Configured.GRAPEFRUIT, Blocks.JUNGLE_LOG, CDNaturalBlocks.GRAPEFRUIT_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.GRAPEFRUIT_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
            fruitTree(context, CDFeature.Configured.LEMON, Blocks.SPRUCE_LOG, CDNaturalBlocks.LEMON_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.LEMON_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
            fruitTree(context, CDFeature.Configured.PEACH, Blocks.OAK_LOG, CDNaturalBlocks.PEACH_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.PEACH_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
            fruitTree(context, CDFeature.Configured.CHERRY, Blocks.SPRUCE_LOG, CDNaturalBlocks.CHERRY_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.CHERRY_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
            fruitTree(context, CDFeature.Configured.PLUM, Blocks.BIRCH_LOG, CDNaturalBlocks.PLUM_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.PLUM_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
            fruitTree(context, CDFeature.Configured.BLOOD_ORANGE, Blocks.JUNGLE_LOG, CDNaturalBlocks.BLOOD_ORANGE_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.BLOOD_ORANGE_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
            fruitTree(context, CDFeature.Configured.RAMBUTAN, Blocks.JUNGLE_LOG, CDNaturalBlocks.RAMBUTAN_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.RAMBUTAN_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
            fruitTree(context, CDFeature.Configured.MANGO, Blocks.JUNGLE_LOG, CDNaturalBlocks.MANGO_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.MANGO_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
            fruitTree(context, CDFeature.Configured.STAR_FRUIT, Blocks.OAK_LOG, CDNaturalBlocks.STAR_FRUIT_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.STAR_FRUIT_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
            fruitTree(context, CDFeature.Configured.POMEGRANATE, Blocks.BIRCH_LOG, CDNaturalBlocks.POMEGRANATE_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.POMEGRANATE_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
            coconutTree(context, CDFeature.Configured.COCONUT, NatureBlocks.PALM_LOG_BLOCKS.getLog().get(), CDNaturalBlocks.COCONUT_TREE_LEAVES_BLOCK.get().defaultBlockState().trySetValue(PalmLeaves.TYPE, SlabType.BOTTOM), CDNaturalBlocks.COCONUT_TREE_LEAVES_BLOCK.get().defaultBlockState().trySetValue(PalmLeaves.TYPE, SlabType.TOP), CDNaturalBlocks.COCONUT_TREE_LEAVES_BLOCK.get().defaultBlockState().trySetValue(PalmLeaves.TYPE, SlabType.DOUBLE), COCONUT_TREE_TOP_BLOCK.get());
        }

        private static void fruitTree(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, Block log, Block leaves, Block fruitLeaves, int trunkHeight, int trunkRandomHeight, float fruitedPercent) {
            context.register(key, new ConfiguredFeature<>(CDFeature.FRUIT_TREE.get(), new FruitTreeFeature.Config(
                    BlockStateProvider.simple(log),
                    BlockStateProvider.simple(leaves),
                    BlockStateProvider.simple(fruitLeaves.defaultBlockState().setValue(CAN_GROW, true)),
                    trunkHeight,
                    trunkRandomHeight,
                    fruitedPercent
            )));
        }

        private static void coconutTree(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, Block log, BlockState leaves1, BlockState leaves2, BlockState leaves3, Block top) {
            context.register(key, new ConfiguredFeature<>(CDFeature.COCONUT_TREE.get(), new CoconutTreeFeature.Config(
                    BlockStateProvider.simple(log),
                    BlockStateProvider.simple(leaves1),
                    BlockStateProvider.simple(leaves2),
                    BlockStateProvider.simple(leaves3),
                    BlockStateProvider.simple(top)
            )));
        }

        private static ResourceKey<ConfiguredFeature<?, ?>> key(String path) {
            return ConfluenceDelight.asResourceKey(Registries.CONFIGURED_FEATURE, path);
        }
    }
}
