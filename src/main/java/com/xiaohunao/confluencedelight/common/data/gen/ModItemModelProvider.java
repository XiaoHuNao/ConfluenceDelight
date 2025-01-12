package com.xiaohunao.confluencedelight.common.data.gen;

import com.xiaohunao.confluencedelight.ConfluenceDelight;
import com.xiaohunao.confluencedelight.common.init.ModItems;
import com.xiaohunao.confluencedelight.common.init.ModMaterialItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ConfluenceDelight.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModItems.ITEMS.getEntries().forEach(entry -> simpleItem(entry.get()));
        ModMaterialItems.ITEMS.getEntries().forEach(entry -> simpleItem(entry.get()));

    }

    private void simpleItem(Item item) {
        String path = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)).getPath();
        try{
            this.withExistingParent(path, ResourceLocation.withDefaultNamespace("item/generated"))
                .texture("layer0", ConfluenceDelight.asResource("item/" + path));
        }catch(Exception e){
            withExistingParent("item/"+path,ConfluenceDelight.asResource("item/missing"));
        }
    }
}
