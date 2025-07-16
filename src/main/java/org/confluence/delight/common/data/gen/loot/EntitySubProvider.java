package org.confluence.delight.common.data.gen.loot;

import com.google.common.collect.Streams;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.confluence.delight.common.init.CDFoodItems;
import org.confluence.mod.mixin.accessor.EntityLootSubProviderAccessor;
import org.confluence.terraentity.init.TEEntities;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntitySubProvider extends EntityLootSubProvider {

    public EntitySubProvider(HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void generate() {
        EnchantedCountIncreaseFunction.Builder random0To1 = EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0.0F, 1.0F));
        LootItemConditionalFunction.Builder<?> count1To3 = SetItemCountFunction.setCount(UniformGenerator.between(1, 3));
        LootItemConditionalFunction.Builder<?> count2To5 = SetItemCountFunction.setCount(UniformGenerator.between(2, 5));
        LootItemConditionalFunction.Builder<?> count2To6 = SetItemCountFunction.setCount(UniformGenerator.between(2, 6));

    }

    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        return Streams.concat(
                TEEntities.ENTITIES.getEntries().stream().map(DeferredHolder::get)
        );
    }


    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        generate();
        EntityLootSubProviderAccessor accessor = (EntityLootSubProviderAccessor) this;
        Set<ResourceKey<LootTable>> set = new HashSet<>();
        getKnownEntityTypes().map(EntityType::builtInRegistryHolder).forEach(holder -> {
            EntityType<?> entityType = holder.value();
            if (entityType.isEnabled(accessor.getAllowed())) {
                if (canHaveLootTable(entityType)) {
                    Map<ResourceKey<LootTable>, LootTable.Builder> map = accessor.getMap().remove(entityType);
                    if (map != null) {
                        map.forEach((key, builder) -> {
                            if (!set.add(key)) {
                                throw new IllegalStateException(String.format(Locale.ROOT, "Duplicate loottable '%s' for '%s'", key, holder.key().location()));
                            } else {
                                output.accept(key, builder);
                            }
                        });
                    }
                } else {
                    Map<ResourceKey<LootTable>, LootTable.Builder> map1 = accessor.getMap().remove(entityType);
                    if (map1 != null) {
                        throw new IllegalStateException(String.format(
                                Locale.ROOT,
                                "Weird loottables '%s' for '%s', not a LivingEntity so should not have loot",
                                map1.keySet().stream().map(p_335190_ -> p_335190_.location().toString()).collect(Collectors.joining(",")),
                                holder.key().location()
                        ));
                    }
                }
            }
        });
        if (!accessor.getMap().isEmpty()) {
            throw new IllegalStateException("Created loot tables for entities not supported by datapack: " + accessor.getMap().keySet());
        }
    }
}
