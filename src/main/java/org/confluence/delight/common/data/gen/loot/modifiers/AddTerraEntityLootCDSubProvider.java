package org.confluence.delight.common.data.gen.loot.modifiers;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.CDFoodItems;
import org.confluence.terraentity.init.entity.TEMonsterEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class AddTerraEntityLootCDSubProvider extends EntityLootSubProvider {

    public AddTerraEntityLootCDSubProvider(HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }


    public List<AddedEntityLoot> getAddedEntitiesLoot() {
        List<AddedEntityLoot> entries = new ArrayList<>();
        List<EntityType<?>> confluenceSlime = List.of(
            TEMonsterEntities.ICE_SLIME.get(),
            TEMonsterEntities.BLUE_SLIME.get(),
            TEMonsterEntities.RED_SLIME.get(),
            TEMonsterEntities.PURPLE_SLIME.get(),
            TEMonsterEntities.JUNGLE_SLIME.get(),
            TEMonsterEntities.PINK_SLIME.get(),
            TEMonsterEntities.YELLOW_SLIME.get(),
            TEMonsterEntities.CRIMSLIME.get(),
            TEMonsterEntities.CORRUPT_SLIME.get(),
            TEMonsterEntities.DESERT_SLIME.get(),
            TEMonsterEntities.TROPIC_SLIME.get(),
            TEMonsterEntities.GREEN_SLIME.get(),
            TEMonsterEntities.BLACK_SLIME.get(),
            TEMonsterEntities.LAVA_SLIME.get(),
            TEMonsterEntities.GREEN_DUMPLING_SLIME.get(),
            TEMonsterEntities.SWAMP_SLIME.get(),
            TEMonsterEntities.DUNGEON_SLIME.get()
        );
        for (EntityType<?> type : confluenceSlime) {
            entries.add(new AddedEntityLoot(type,
                LootTable.lootTable().withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(CDFoodItems.GEL_JUICE)
                            .setWeight(2))
                        .add(EmptyLootItem.emptyItem()
                            .setWeight(98))
                )
            ));
        }

        return entries;
    }


    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        var entries = getAddedEntitiesLoot();
        List<EntityType<?>> entityTypes = new ArrayList<>();
        for (var entry : entries) {
            entityTypes.add(entry.entityType);
        }
        return entityTypes.stream();
    }

    @Override
    public void generate() {
        var entries = getAddedEntitiesLoot();
        for (var entry : entries) {
            add(entry.entityType, getResourceKey(entry.entityType), entry.lootTableBuilder);
        }
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        this.generate();
        for (var entry : getAddedEntitiesLoot()) {
            EntityType<?> entityType = entry.entityType();
            var entityLootMap = this.map.remove(entityType);
            if (entityLootMap != null) {
                entityLootMap.forEach((originalKey, builder) -> {
                    output.accept(getResourceKey(entityType), builder);
                });
            }
        }
        this.map.clear();
    }

    protected ResourceKey<LootTable> getResourceKey(EntityType<?> entityType) {
        var path = getPath(entityType);
        return ConfluenceDelight.asResourceKey(Registries.LOOT_TABLE, path);
    }

    public String getPath(EntityType<?> entityType) {
        return "with/" + entityType.getDefaultLootTable().location().getPath();
    }

    public record AddedEntityLoot(EntityType<?> entityType, LootTable.Builder lootTableBuilder) {
    }
}
