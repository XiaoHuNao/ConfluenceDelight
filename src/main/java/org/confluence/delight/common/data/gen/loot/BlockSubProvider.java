package org.confluence.delight.common.data.gen.loot;

import com.google.common.collect.Iterables;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.common.block.natural.CoconutBlock;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDFoodBlocks;
import org.confluence.delight.common.init.CDNaturalBlocks;
import org.confluence.delight.common.init.CDNaturalItems;
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

        dropSelf(CDNaturalBlocks.APPLE_SAPLING.get());
        dropSelf(CDNaturalBlocks.APRICOT_SAPLING.get());
        dropSelf(CDNaturalBlocks.GRAPEFRUIT_SAPLING.get());
        dropSelf(CDNaturalBlocks.LEMON_SAPLING.get());
        dropSelf(CDNaturalBlocks.PEACH_SAPLING.get());
        dropSelf(CDNaturalBlocks.CHERRY_SAPLING.get());
        dropSelf(CDNaturalBlocks.PLUM_SAPLING.get());
        dropSelf(CDNaturalBlocks.BLOOD_ORANGE_SAPLING.get());
        dropSelf(CDNaturalBlocks.RAMBUTAN_SAPLING.get());
        dropSelf(CDNaturalBlocks.MANGO_SAPLING.get());
        dropSelf(CDNaturalBlocks.STAR_FRUIT_SAPLING.get());
        dropSelf(CDNaturalBlocks.POMEGRANATE_SAPLING.get());
        dropSelf(CDNaturalBlocks.COCONUT_SAPLING.get());
        dropSelf(CDNaturalBlocks.COCONUT_TREE_TOP_BLOCK.get());
        dropSelf(CDNaturalBlocks.STAR_FRUIT_SAPLING.get());
        dropSelf(CDNaturalBlocks.POMEGRANATE_SAPLING.get());
        dropSelf(CDNaturalBlocks.BLACKCURRANT_SHRUB_BLOCK.get());
        dropSelf(CDNaturalBlocks.ELDERBERRY_SHRUB_BLOCK.get());

        createLeavesDrops(CDNaturalBlocks.APPLE_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.APPLE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES);
        createLeavesDrops(CDNaturalBlocks.APRICOT_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.APRICOT_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES);
        createLeavesDrops(CDNaturalBlocks.GRAPEFRUIT_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.GRAPEFRUIT_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES);
        createLeavesDrops(CDNaturalBlocks.LEMON_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.LEMON_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES);
        createLeavesDrops(CDNaturalBlocks.PEACH_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.PEACH_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES);
        createLeavesDrops(CDNaturalBlocks.CHERRY_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.CHERRY_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES);
        createLeavesDrops(CDNaturalBlocks.PLUM_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.PLUM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES);
        createLeavesDrops(CDNaturalBlocks.BLOOD_ORANGE_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.BLOOD_ORANGE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES);
        createLeavesDrops(CDNaturalBlocks.RAMBUTAN_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.RAMBUTAN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES);
        createLeavesDrops(CDNaturalBlocks.MANGO_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.MANGO_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES);
        createLeavesDrops(CDNaturalBlocks.COCONUT_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.COCONUT_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES);
        createLeavesDrops(CDNaturalBlocks.STAR_FRUIT_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.STAR_FRUIT_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES);
        createLeavesDrops(CDNaturalBlocks.POMEGRANATE_TREE_LEAVES_BLOCK.get(), CDNaturalBlocks.POMEGRANATE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES);

        createCropDrops(CDNaturalBlocks.PINEAPPLE_CROP.get(), FoodItems.PINEAPPLE.asItem(), CDNaturalItems.PINEAPPLE_SEEDS.asItem(), LootItemBlockStatePropertyCondition.hasBlockStateProperties(CDNaturalBlocks.PINEAPPLE_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7)));
        createCropDrops(CDNaturalBlocks.SPICY_PEPPER_CROP.get(), FoodItems.SPICY_PEPPER.asItem(), CDNaturalItems.SPICY_PEPPER_SEEDS.asItem(), LootItemBlockStatePropertyCondition.hasBlockStateProperties(CDNaturalBlocks.SPICY_PEPPER_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7)));

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
