package org.confluence.delight.common.data.gen.loot;

import com.google.common.collect.Iterables;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.common.block.natural.CoconutBlock;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDFoodBlocks;
import org.confluence.delight.common.init.CDNaturalBlocks;
import org.confluence.mod.common.init.item.FoodItems;

import java.util.Set;

@SuppressWarnings("all")
public final class BlockSubProvider extends BlockLootSubProvider {
    public BlockSubProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        dropSelf(CDBlocks.PICKLE_JARS_BLOCK.get());
        dropSelf(CDBlocks.SAP_COLLECTORS_BLOCK.get());
        dropSelf(CDBlocks.MILLSTONE_BLOCK.get());
        dropSelf(CDBlocks.JUICER_BLOCK.get());
        dropOther(CDBlocks.WINE_CAULDRON.get(), Items.CAULDRON);
        dropOther(CDBlocks.BRINE_CAULDRON.get(), Items.CAULDRON);

        dropSelf(CDFoodBlocks.WHITE_CHOCOLATE_BLOCK.get());
        dropSelf(CDFoodBlocks.BLACK_CHOCOLATE_BLOCK.get());
        dropSelf(CDFoodBlocks.FLAVORED_WHITE_CHOCOLATE_BLOCK.get());
        dropSelf(CDFoodBlocks.CHICKEN_HOT_POT_BLOCK.get());
        dropSelf(CDFoodBlocks.BRAISED_BEEF_RICE_BLOCK.get());
        dropSelf(CDFoodBlocks.BRAISED_BEEF_NOODLES_BLOCK.get());

        add(CDNaturalBlocks.COCONUT_BLOCK.get(), loot -> {
            LootPool.Builder pool = LootPool.lootPool().setRolls(ConstantValue.exactly(1));
            for (int piece = 1; piece <= 3; piece++) {
                pool.add(LootItem.lootTableItem(FoodItems.COCONUT)
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(CDNaturalBlocks.COCONUT_BLOCK.get())
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(CoconutBlock.PIECE, piece)))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(piece))));
            }
            return LootTable.lootTable().withPool(pool);
        });
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Iterables.concat(
                getIterableFromRegister(CDBlocks.BLOCKS),
                getIterableFromRegister(CDFoodBlocks.BLOCKS),
                getIterableFromRegister(CDNaturalBlocks.BLOCKS)
        );
    }

    private Iterable<Block> getIterableFromRegister(DeferredRegister<Block> register) {
        return register.getEntries().stream().map(holder -> (Block) holder.get()).filter(block -> map.containsKey(block.getLootTable())).toList();
    }
}
