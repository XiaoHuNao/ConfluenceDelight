package org.confluence.delight.common.data.gen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.data.gen.loot.modifiers.AddBlockLootCDSubProvider;
import org.confluence.delight.common.data.gen.loot.modifiers.AddTerraEntityLootCDSubProvider;

import java.util.concurrent.CompletableFuture;

public class CDLootModifiersProvider extends GlobalLootModifierProvider {
    public CDLootModifiersProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, ConfluenceDelight.MODID);
    }

    @Override
    protected void start() {
        var provider = new AddTerraEntityLootCDSubProvider(registries);
        for (var entry : provider.getAddedEntitiesLoot()) {
            EntityType<?> type = entry.entityType();
            String path = type.getDefaultLootTable().location().getPath();
            this.add(
                path,
                new AddTableLootModifier(
                    new LootItemCondition[] {
                        LootTableIdCondition.builder(ResourceLocation.fromNamespaceAndPath("confluence", "entities/terra_entity/" + path.replace("entities/", ""))).build()
                    },
                    ResourceKey.create(Registries.LOOT_TABLE, ConfluenceDelight.asResource(provider.getPath(type)))
                )
            );
        }

        var blockProvider = new AddBlockLootCDSubProvider(registries);
        var blockEntries = blockProvider.getAddedBlocksLoot();
        for (var entry : blockEntries) {
            var block = entry.block();
            ResourceLocation location = block.getLootTable().location();
            this.add(
                location.getPath(),
                new AddTableLootModifier(new LootTableIdCondition[] {
                    (LootTableIdCondition) LootTableIdCondition.builder(location).build()
                }, ResourceKey.create(Registries.LOOT_TABLE, ConfluenceDelight.asResource(blockProvider.getPath(location))))
            );
        }
    }
}

