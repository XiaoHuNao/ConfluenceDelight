package org.confluence.delight.common.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import org.confluence.delight.ConfluenceDelight;


public final class CDTags {
    public static class Fluids {
        public static final TagKey<Fluid> WINE = c("wine");
        public static final TagKey<Fluid> BRINE = c("brine");

        private static TagKey<Fluid> c(String id) {
            return FluidTags.create(ResourceLocation.fromNamespaceAndPath("c", id));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> BLACKCURRANT_CRATE = c("storage_blocks/blackcurrant");
        public static final TagKey<Block> BLOOD_ORANGE_CRATE = c("storage_blocks/blood_orange");
        public static final TagKey<Block> BLOODY_MOSCATO_CRATE = c("storage_blocks/bloody_moscato");
        public static final TagKey<Block> ELDERBERRY_CRATE = c("storage_blocks/elderberry");
        public static final TagKey<Block> APRICOT_CRATE = c("storage_blocks/apricot");
        public static final TagKey<Block> BANANA_CRATE = c("storage_blocks/banana");
        public static final TagKey<Block> CHERRY_CRATE = c("storage_blocks/cherry");
        public static final TagKey<Block> COCONUT_CRATE = c("storage_blocks/coconut");
        public static final TagKey<Block> DRAGON_FRUIT_CRATE = c("storage_blocks/dragon_fruit");
        public static final TagKey<Block> GRAPE_FRUIT_CRATE = c("storage_blocks/grape_fruit");
        public static final TagKey<Block> LEMON_CRATE = c("storage_blocks/lemon");
        public static final TagKey<Block> MANGO_CRATE = c("storage_blocks/mango");
        public static final TagKey<Block> PEACH_CRATE = c("storage_blocks/peach");
        public static final TagKey<Block> PINEAPPLE_CRATE = c("storage_blocks/pineapple");
        public static final TagKey<Block> PLUM_CRATE = c("storage_blocks/plum");
        public static final TagKey<Block> SPICY_PEPPER_CRATE = c("storage_blocks/spicy_pepper");
        public static final TagKey<Block> STAR_FRUIT_CRATE = c("storage_blocks/star_fruit");
        public static final TagKey<Block> POMEGRANATE_CRATE = c("storage_blocks/pomegranate");
        public static final TagKey<Block> RAMBUTAN_CRATE = c("storage_blocks/rambutan");

        private static TagKey<Block> c(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }

        private static TagKey<Block> register(String id) {
            return BlockTags.create(ConfluenceDelight.asResource(id));
        }
    }

    public static class Items {
        public static final TagKey<Item> C_FRUIT_BLACKCURRANT = c("foods/fruits/blackcurrant");
        public static final TagKey<Item> C_FRUIT_BLOOD_ORANGE = c("foods/fruits/blood_orange");
        public static final TagKey<Item> C_FRUIT_BLOODY_MOSCATO = c("foods/fruits/bloody_moscato");
        public static final TagKey<Item> C_FRUIT_ELDERBERRY = c("foods/fruits/elderberry");
        public static final TagKey<Item> C_FRUIT_APRICOT = c("foods/fruits/apricot");
        public static final TagKey<Item> C_FRUIT_BANANA = c("foods/fruits/banana");
        public static final TagKey<Item> C_FRUIT_CHERRY = c("foods/fruits/cherry");
        public static final TagKey<Item> C_FRUIT_COCONUT = c("foods/fruits/coconut");
        public static final TagKey<Item> C_FRUIT_DRAGON_FRUIT = c("foods/fruits/dragon_fruit");
        public static final TagKey<Item> C_FRUIT_GRAPE_FRUIT = c("foods/fruits/grape_fruit");
        public static final TagKey<Item> C_FRUIT_MANGO = c("foods/fruits/mango");
        public static final TagKey<Item> C_FRUIT_PEACH = c("foods/fruits/peach");
        public static final TagKey<Item> C_FRUIT_PINEAPPLE = c("foods/fruits/pineapple");
        public static final TagKey<Item> C_FRUIT_PLUM = c("foods/fruits/plum");
        public static final TagKey<Item> C_FRUIT_STAR_FRUIT = c("foods/fruits/star_fruit");
        public static final TagKey<Item> C_FRUIT_POMEGRANATE = c("foods/fruits/pomegranate");
        public static final TagKey<Item> C_FRUIT_RAMBUTAN = c("foods/fruits/rambutan");
        public static final TagKey<Item> C_FRUIT_LEMON = c("foods/fruits/lemon");
        public static final TagKey<Item> C_VEGETABLES_SPICY_PEPPER = c("foods/vegetables/spicy_pepper");
        public static final TagKey<Item> JUICER_CONTAINER = register("juicer_container");

        public static final TagKey<Item> BLACKCURRANT_CRATE = c("storage_blocks/blackcurrant");
        public static final TagKey<Item> BLOOD_ORANGE_CRATE = c("storage_blocks/blood_orange");
        public static final TagKey<Item> BLOODY_MOSCATO_CRATE = c("storage_blocks/bloody_moscato");
        public static final TagKey<Item> ELDERBERRY_CRATE = c("storage_blocks/elderberry");
        public static final TagKey<Item> APRICOT_CRATE = c("storage_blocks/apricot");
        public static final TagKey<Item> BANANA_CRATE = c("storage_blocks/banana");
        public static final TagKey<Item> CHERRY_CRATE = c("storage_blocks/cherry");
        public static final TagKey<Item> COCONUT_CRATE = c("storage_blocks/coconut");
        public static final TagKey<Item> DRAGON_FRUIT_CRATE = c("storage_blocks/dragon_fruit");
        public static final TagKey<Item> GRAPE_FRUIT_CRATE = c("storage_blocks/grape_fruit");
        public static final TagKey<Item> LEMON_CRATE = c("storage_blocks/lemon");
        public static final TagKey<Item> MANGO_CRATE = c("storage_blocks/mango");
        public static final TagKey<Item> PEACH_CRATE = c("storage_blocks/peach");
        public static final TagKey<Item> PINEAPPLE_CRATE = c("storage_blocks/pineapple");
        public static final TagKey<Item> PLUM_CRATE = c("storage_blocks/plum");
        public static final TagKey<Item> SPICY_PEPPER_CRATE = c("storage_blocks/spicy_pepper");
        public static final TagKey<Item> STAR_FRUIT_CRATE = c("storage_blocks/star_fruit");
        public static final TagKey<Item> POMEGRANATE_CRATE = c("storage_blocks/pomegranate");
        public static final TagKey<Item> RAMBUTAN_CRATE = c("storage_blocks/rambutan");

        private static TagKey<Item> c(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
        }

        private static TagKey<Item> register(String id) {
            return ItemTags.create(ConfluenceDelight.asResource(id));
        }
    }
}
