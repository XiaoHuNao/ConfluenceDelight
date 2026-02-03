package org.confluence.delight.common.data.gen.loot.modifiers;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import org.confluence.delight.ConfluenceDelight;

import java.util.*;
import java.util.function.BiConsumer;

public class AddBlockLootCDSubProvider extends BlockLootSubProvider {
    private final HolderLookup.Provider provider;

    public AddBlockLootCDSubProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
        this.provider = provider;
    }

    public List<AddedBlockLoot> getAddedBlocksLoot() {
        List<AddedBlockLoot> entries = new ArrayList<>();
        return entries;
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        var entries = getAddedBlocksLoot();
        List<Block> blocks = new ArrayList<>();
        for (var entry : entries) {
            blocks.add(entry.block);
        }
        return blocks;
    }

    @Override
    protected void generate() {
        var entries = getAddedBlocksLoot();
        for (var entry : entries) {
            this.add(entry.block, entry.lootTableBuilder);
        }
    }

    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        this.generate();
        Set<ResourceKey<LootTable>> set = new HashSet<>();
        for (var block : this.getKnownBlocks()) {
            if (block.isEnabled(this.enabledFeatures)) {
                ResourceKey<LootTable> originalResourceKey = block.getLootTable();
                var resourceKey = getResourceKey(originalResourceKey);
                if (originalResourceKey != BuiltInLootTables.EMPTY && set.add(originalResourceKey)) {
                    LootTable.Builder loottable$builder = this.map.remove(originalResourceKey);
                    if (loottable$builder == null) {
                        throw new IllegalStateException(String.format(Locale.ROOT, "Missing loottable '%s' for '%s'", originalResourceKey.location(), BuiltInRegistries.BLOCK.getKey(block)));
                    }

                    output.accept(resourceKey, loottable$builder);
                }
            }
        }

        if (!this.map.isEmpty()) {
            throw new IllegalStateException("Created block loot tables for non-blocks: " + this.map.keySet());
        }
    }

    protected ResourceKey<LootTable> getResourceKey(ResourceKey<LootTable> originalResourceKey) {
        return ConfluenceDelight.asResourceKey(Registries.LOOT_TABLE, getPath(originalResourceKey.location()));
    }

    public String getPath(ResourceLocation location) {
        return "with/" + location.getPath();
    }

    public record AddedBlockLoot(Block block, LootTable.Builder lootTableBuilder) {
    }
}
