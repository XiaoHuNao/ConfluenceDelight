package org.confluence.delight.common.data.gen.worldgen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.CDNaturalBlocks;
import org.confluence.delight.common.worldgen.feature.FruitTreeFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.confluence.delight.common.block.natural.BaseFruitTreeLeaveBlock.CAN_GROW;

public class FruitTreeFeatureConfigProvider implements DataProvider {
    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> lookupProvider;
    private final Map<ResourceLocation, FruitTreeFeature.Config> conditions = new HashMap<>();

    public FruitTreeFeatureConfigProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        this.output = output;
        this.lookupProvider = lookupProvider;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        gather();
        return lookupProvider.thenCompose(provider -> {
            Codec<FruitTreeFeature.Config> codec = FruitTreeFeature.Config.CODEC;
            DynamicOps<JsonElement> ops = JsonOps.INSTANCE;
            List<CompletableFuture<?>> futures = new ArrayList<>();
            for (Map.Entry<ResourceLocation, FruitTreeFeature.Config> entry : conditions.entrySet()) {
                ResourceLocation id = entry.getKey();
                FruitTreeFeature.Config config = entry.getValue();
                Path path = output.getOutputFolder()
                        .resolve("data")
                        .resolve(id.getNamespace())
                        .resolve("worldgen/configured_feature")
                        .resolve(id.getPath() + ".json");
                CompletableFuture<?> future = CompletableFuture.supplyAsync(() -> {
                    JsonElement configJson = codec.encodeStart(ops, config)
                            .getOrThrow(error -> new RuntimeException("Failed to encode " + path + ": " + error));
                    if (!configJson.isJsonObject()) {
                        throw new RuntimeException("Config JSON is not an object for " + path);
                    }
                    JsonObject root = new JsonObject();
                    root.addProperty("type", "confluence_delight:fruit_tree");
                    root.add("config", configJson);
                    return root;
                }).thenComposeAsync(json -> {
                    try {
                        Files.createDirectories(path.getParent());
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to create directories for " + path, e);
                    }
                    return DataProvider.saveStable(cache, json, path);
                });

                futures.add(future);
            }
            return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        });
    }


    protected void fruitTree(ResourceLocation id, Block trunk, Block leaves, Block fruitedLeaves, int height, int heightMore, float fruitedPercent) {
        FruitTreeFeature.Config config = new FruitTreeFeature.Config(
                BlockStateProvider.simple(trunk.defaultBlockState()),
                BlockStateProvider.simple(leaves.defaultBlockState()),
                BlockStateProvider.simple(fruitedLeaves.defaultBlockState().setValue(CAN_GROW, true)),
                height,
                heightMore,
                fruitedPercent
        );
        add(id, config);
    }

    protected void add(ResourceLocation id, FruitTreeFeature.Config config) {
        conditions.put(id, config);
    }

    protected void gather() {
        fruitTree(ConfluenceDelight.asResource("apple_tree"), Blocks.OAK_LOG, CDNaturalBlocks.APPLE_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.APPLE_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
        fruitTree(ConfluenceDelight.asResource("apricot_tree"), Blocks.BIRCH_LOG, CDNaturalBlocks.APRICOT_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.APRICOT_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
        fruitTree(ConfluenceDelight.asResource("grapefruit_tree"), Blocks.JUNGLE_LOG, CDNaturalBlocks.GRAPEFRUIT_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.GRAPEFRUIT_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
        fruitTree(ConfluenceDelight.asResource("lemon_tree"), Blocks.SPRUCE_LOG, CDNaturalBlocks.LEMON_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.LEMON_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
        fruitTree(ConfluenceDelight.asResource("peach_tree"), Blocks.OAK_LOG, CDNaturalBlocks.PEACH_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.PEACH_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
        fruitTree(ConfluenceDelight.asResource("cherry_tree"), Blocks.SPRUCE_LOG, CDNaturalBlocks.CHERRY_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.CHERRY_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
        fruitTree(ConfluenceDelight.asResource("plum_tree"), Blocks.BIRCH_LOG, CDNaturalBlocks.PLUM_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.PLUM_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
        fruitTree(ConfluenceDelight.asResource("blood_orange_tree"), Blocks.JUNGLE_LOG, CDNaturalBlocks.BLOOD_ORANGE_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.BLOOD_ORANGE_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
        fruitTree(ConfluenceDelight.asResource("rambutan_tree"), Blocks.JUNGLE_LOG, Blocks.JUNGLE_LEAVES, CDNaturalBlocks.RAMBUTAN_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
        fruitTree(ConfluenceDelight.asResource("mango_tree"), Blocks.JUNGLE_LOG, CDNaturalBlocks.RAMBUTAN_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.MANGO_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
        fruitTree(ConfluenceDelight.asResource("banana_tree"), Blocks.JUNGLE_LOG, CDNaturalBlocks.BANANA_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.BANANA_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
        fruitTree(ConfluenceDelight.asResource("coconut_tree"), Blocks.JUNGLE_LOG, CDNaturalBlocks.COCONUT_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.COCONUT_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
        fruitTree(ConfluenceDelight.asResource("star_fruit_tree"), Blocks.OAK_LOG, CDNaturalBlocks.STAR_FRUIT_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.STAR_FRUIT_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
        fruitTree(ConfluenceDelight.asResource("pomegranate_tree"), Blocks.BIRCH_LOG, CDNaturalBlocks.POMEGRANATE_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.POMEGRANATE_TREE_LEAVES_BLOCK.get(), 3, 2, 0.3f);
    }

    @Override
    public String getName() {
        return "FruitTreeFeatureConfigProvider";
    }
}


