package org.confluence.delight.common.data.gen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.confluence.delight.common.data.gen.loot.BlockSubProvider;
import org.confluence.delight.common.data.gen.loot.EntitySubProvider;
import org.confluence.delight.common.data.gen.loot.modifiers.AddBlockLootCDSubProvider;
import org.confluence.delight.common.data.gen.loot.modifiers.AddTerraEntityLootCDSubProvider;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, Set.of(), List.of(
            new SubProviderEntry(BlockSubProvider::new, LootContextParamSets.BLOCK),
            new SubProviderEntry(EntitySubProvider::new, LootContextParamSets.ENTITY),
            new SubProviderEntry(AddBlockLootCDSubProvider::new, LootContextParamSets.BLOCK),
            new SubProviderEntry(AddTerraEntityLootCDSubProvider::new, LootContextParamSets.ENTITY)
        ), lookup);
    }
}
