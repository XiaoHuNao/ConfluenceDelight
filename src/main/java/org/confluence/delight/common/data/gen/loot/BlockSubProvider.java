package org.confluence.delight.common.data.gen.loot;

import com.google.common.collect.Iterables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDFoodBlocks;

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
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Iterables.concat(
                getIterableFromRegister(CDBlocks.BLOCKS),
                getIterableFromRegister(CDFoodBlocks.BLOCKS)
        );
    }

    private Iterable<Block> getIterableFromRegister(DeferredRegister<Block> register) {
        return register.getEntries().stream().map(holder -> (Block) holder.get()).filter(block -> map.containsKey(block.getLootTable())).toList();
    }
}
