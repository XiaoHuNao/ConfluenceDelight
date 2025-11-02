package org.confluence.delight.common.data.gen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ModItemModelProvider extends ItemModelProvider {
    private static final ResourceLocation MISSING_ITEM = ConfluenceDelight.asResource("item/item_icon");
    private static final ResourceLocation MISSING_BLOCK = ConfluenceDelight.asResource("item/blocks_icon");
    private Map<DeferredItem<? extends Item>, Consumer<ItemModelBuilder>> dispatcher;

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ConfluenceDelight.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        List<Map<DeferredRegister.Items, List<String>>> customModels = new ArrayList<>();
        customModels.add(createDir(CDFoodItems.ITEMS, "food/"));
        customModels.add(createDir(CDItems.ITEMS, "misc/"));
        customModels.add(createDir(CDMaterialItems.ITEMS, "materials/"));
        customModels.add(createDir(CDNaturalItems.ITEMS, "natural/"));

        genModels(customModels, "item/generated", true);

        // 方块物品
        List<DeferredRegister.Items> blocks = List.of(
            CDItems.BLOCK_ITEMS,
            CDNaturalBlocks.BLOCK_ITEMS
        );
        blocks.forEach(reg -> reg.getEntries().forEach(item -> {
            Item item1 = item.get();
            String path = item.getId().getPath().toLowerCase();
            try {
                if (item1 instanceof BlockItem item2) {
                    Block block = item2.getBlock();
                    if (block instanceof DoorBlock) {
                        withExistingParent(path, "item/generated").texture("layer0", ConfluenceDelight.asResource("item/" + path));
                    } else if (block instanceof TrapDoorBlock) {
                        withExistingParent(path, ConfluenceDelight.asResource("block/" + path + "_bottom"));
                    } else {
                        withExistingParent(path, ConfluenceDelight.asResource("block/" + path + (hasInventory(block) ? "_inventory" : "")));
                    }
                }
            } catch (Exception e) {
                withExistingParent(path, MISSING_BLOCK);
            }
        }));
    }

    private static boolean hasInventory(Block block) {
        return block instanceof ButtonBlock || block instanceof FenceBlock;
    }

    private Map<DeferredRegister.Items, List<String>> createDir(DeferredRegister.Items reg, String... packPaths) {
        return Map.of(reg, Arrays.stream(packPaths).toList());
    }

    private void genModels(List<Map<DeferredRegister.Items, List<String>>> list, String parent, boolean handheldAdjust) {
        list.forEach(mp -> mp.forEach((items, packPaths) -> {
            items.getEntries().forEach(item -> {
                String path = item.getId().getPath().toLowerCase();
                boolean exist = false;
                for (String packPath : packPaths) {
                    try {
                        withExistingParent(path, parent).texture("layer0", ConfluenceDelight.asResource("item/" + packPath + path));
                        if (handheldAdjust) {
                            Consumer<ItemModelBuilder> consumer = dispatcher.get(item);
                            if (consumer != null) consumer.accept(getBuilder(path));
                        }
                        exist = true;
                        break;
                    } catch (Exception ignored) {
                    }
                }
                if (!exist) withExistingParent(path, MISSING_ITEM);
            });
        }));
    }
}
