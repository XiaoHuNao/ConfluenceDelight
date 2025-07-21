package org.confluence.delight.common.data.gen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDNaturalBlocks;
import org.confluence.delight.common.init.CDTags;
import org.confluence.mod.common.init.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModBlocks;

import java.util.concurrent.CompletableFuture;

import static org.confluence.delight.ConfluenceDelight.MODID;
import static org.confluence.mod.common.init.block.OreBlocks.TIN_BLOCK;


public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                CDBlocks.PICKLE_JARS_BLOCK.get(),
                CDBlocks.MILLSTONE_BLOCK.get(),
                CDBlocks.JUICER_BLOCK.get()
        );
        tag(ModTags.Blocks.MINEABLE_WITH_PICKAXE_AXE).add(
                CDBlocks.PICKLE_JARS_BLOCK.get(),
                CDBlocks.SAP_COLLECTORS_BLOCK.get(),
                CDBlocks.MILLSTONE_BLOCK.get(),
                CDBlocks.JUICER_BLOCK.get()
        );
        tag(BlockTags.MINEABLE_WITH_AXE).add(
                CDBlocks.SAP_COLLECTORS_BLOCK.get()
        );
        tag(BlockTags.CAULDRONS).add(
                CDBlocks.WINE_CAULDRON.get(),
                CDBlocks.BRINE_CAULDRON.get()
        );
        tag(BlockTags.SAPLINGS).add(sapling);
        tag(ModTags.Blocks.MINEABLE_WITH_HOE_SHOVEL).add(leaves);
        tag(BlockTags.LEAVES).add(leaves);
        tag(BlockTags.MINEABLE_WITH_HOE).add(leaves);

        tag(Tags.Blocks.STORAGE_BLOCKS).add(
                CDNaturalBlocks.BLACKCURRANT_CRATE.get(),
                CDNaturalBlocks.BLOOD_ORANGE_CRATE.get(),
                CDNaturalBlocks.BLOODY_MOSCATO_CRATE.get(),
                CDNaturalBlocks.ELDERBERRY_CRATE.get(),
                CDNaturalBlocks.APRICOT_CRATE.get(),
                CDNaturalBlocks.BANANA_CRATE.get(),
                CDNaturalBlocks.CHERRY_CRATE.get(),
                CDNaturalBlocks.COCONUT_CRATE.get(),
                CDNaturalBlocks.DRAGON_FRUIT_CRATE.get(),
                CDNaturalBlocks.GRAPE_FRUIT_CRATE.get(),
                CDNaturalBlocks.LEMON_CRATE.get(),
                CDNaturalBlocks.MANGO_CRATE.get(),
                CDNaturalBlocks.PEACH_CRATE.get(),
                CDNaturalBlocks.PINEAPPLE_CRATE.get(),
                CDNaturalBlocks.PLUM_CRATE.get(),
                CDNaturalBlocks.SPICY_PEPPER_CRATE.get(),
                CDNaturalBlocks.STAR_FRUIT_CRATE.get(),
                CDNaturalBlocks.POMEGRANATE_CRATE.get(),
                CDNaturalBlocks.RAMBUTAN_CRATE.get()
        );

        tag(CDTags.Blocks.BLACKCURRANT_CRATE).add(CDNaturalBlocks.BLACKCURRANT_CRATE.get());
        tag(CDTags.Blocks.BLOOD_ORANGE_CRATE).add(CDNaturalBlocks.BLOOD_ORANGE_CRATE.get());
        tag(CDTags.Blocks.BLOODY_MOSCATO_CRATE).add(CDNaturalBlocks.BLOODY_MOSCATO_CRATE.get());
        tag(CDTags.Blocks.ELDERBERRY_CRATE).add(CDNaturalBlocks.ELDERBERRY_CRATE.get());
        tag(CDTags.Blocks.APRICOT_CRATE).add(CDNaturalBlocks.APRICOT_CRATE.get());
        tag(CDTags.Blocks.BANANA_CRATE).add(CDNaturalBlocks.BANANA_CRATE.get());
        tag(CDTags.Blocks.CHERRY_CRATE).add(CDNaturalBlocks.CHERRY_CRATE.get());
        tag(CDTags.Blocks.COCONUT_CRATE).add(CDNaturalBlocks.COCONUT_CRATE.get());
        tag(CDTags.Blocks.DRAGON_FRUIT_CRATE).add(CDNaturalBlocks.DRAGON_FRUIT_CRATE.get());
        tag(CDTags.Blocks.GRAPE_FRUIT_CRATE).add(CDNaturalBlocks.GRAPE_FRUIT_CRATE.get());
        tag(CDTags.Blocks.LEMON_CRATE).add(CDNaturalBlocks.LEMON_CRATE.get());
        tag(CDTags.Blocks.MANGO_CRATE).add(CDNaturalBlocks.MANGO_CRATE.get());
        tag(CDTags.Blocks.PEACH_CRATE).add(CDNaturalBlocks.PEACH_CRATE.get());
        tag(CDTags.Blocks.PINEAPPLE_CRATE).add(CDNaturalBlocks.PINEAPPLE_CRATE.get());
        tag(CDTags.Blocks.PLUM_CRATE).add(CDNaturalBlocks.PLUM_CRATE.get());
        tag(CDTags.Blocks.SPICY_PEPPER_CRATE).add(CDNaturalBlocks.SPICY_PEPPER_CRATE.get());
        tag(CDTags.Blocks.STAR_FRUIT_CRATE).add(CDNaturalBlocks.STAR_FRUIT_CRATE.get());
        tag(CDTags.Blocks.POMEGRANATE_CRATE).add(CDNaturalBlocks.POMEGRANATE_CRATE.get());
        tag(CDTags.Blocks.RAMBUTAN_CRATE).add(CDNaturalBlocks.RAMBUTAN_CRATE.get());
    }

    Block[] leaves = new Block[]{
            CDNaturalBlocks.APPLE_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.APRICOT_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.GRAPEFRUIT_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.LEMON_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.PEACH_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.CHERRY_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.PLUM_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.BLOOD_ORANGE_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.RAMBUTAN_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.MANGO_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.BANANA_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.COCONUT_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.STAR_FRUIT_TREE_LEAVES_BLOCK.get(),
            CDNaturalBlocks.POMEGRANATE_TREE_LEAVES_BLOCK.get()
    };

    Block[] sapling = new Block[]{
            CDNaturalBlocks.APPLE_SAPLING.get(),
            CDNaturalBlocks.APRICOT_SAPLING.get(),
            CDNaturalBlocks.GRAPEFRUIT_SAPLING.get(),
            CDNaturalBlocks.LEMON_SAPLING.get(),
            CDNaturalBlocks.PEACH_SAPLING.get(),
            CDNaturalBlocks.CHERRY_SAPLING.get(),
            CDNaturalBlocks.PLUM_SAPLING.get(),
            CDNaturalBlocks.BLOOD_ORANGE_SAPLING.get(),
            CDNaturalBlocks.RAMBUTAN_SAPLING.get(),
            CDNaturalBlocks.MANGO_SAPLING.get(),
            CDNaturalBlocks.BANANA_SAPLING.get(),
            CDNaturalBlocks.COCONUT_SAPLING.get(),
            CDNaturalBlocks.STAR_FRUIT_SAPLING.get(),
            CDNaturalBlocks.POMEGRANATE_SAPLING.get()
    };

    @Override
    public @NotNull IntrinsicTagAppender<Block> tag(@NotNull TagKey<Block> tag) {
        return super.tag(tag);
    }
}
