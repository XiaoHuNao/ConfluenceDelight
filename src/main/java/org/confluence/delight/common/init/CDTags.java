package org.confluence.delight.common.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.confluence.delight.ConfluenceDelight;


public final class CDTags {
    public static class Blocks {
        private static TagKey<Block> register(String id) {
            return BlockTags.create(ConfluenceDelight.asResource(id));
        }
    }

    public static class Items {
        public static final TagKey<Item> FRUIT_MANGO = register("mango");
        public static final TagKey<Item> FRUIT_PINEAPPLE = register("pineapple");
        public static final TagKey<Item> FRUIT_LEMON = register("lemon");

        private static TagKey<Item> register(String id) {
            return ItemTags.create(ConfluenceDelight.asResource(id));
        }
    }
}
