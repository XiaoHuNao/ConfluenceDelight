package org.confluence.delight.common.data.gen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.confluence.delight.common.init.CDBlocks;
import org.confluence.delight.common.init.CDNaturalBlocks;
import org.confluence.mod.common.init.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static org.confluence.delight.ConfluenceDelight.MODID;


public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                CDBlocks.PICKLE_JARS_BLOCK.get()
        );
        tag(ModTags.Blocks.MINEABLE_WITH_PICKAXE_AXE).add(
                CDBlocks.PICKLE_JARS_BLOCK.get(),
                CDBlocks.SAP_COLLECTORS_BLOCK.get()
        );
        tag(BlockTags.MINEABLE_WITH_AXE).add(
                CDBlocks.SAP_COLLECTORS_BLOCK.get()
        );
        tag(BlockTags.SAPLINGS).add(sapling);
        tag(ModTags.Blocks.MINEABLE_WITH_HOE_SHOVEL).add(leaves);
        tag(BlockTags.LEAVES).add(leaves);
        tag(BlockTags.MINEABLE_WITH_HOE).add(leaves);

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
