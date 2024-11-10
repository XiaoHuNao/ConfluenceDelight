package com.xiaohunao.confluencedelight.common.data.gen;

import com.xiaohunao.confluencedelight.ConfluenceDelight;
import com.xiaohunao.confluencedelight.common.init.ModItems;
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
        /*
        this.simpleItem(ModItems.BANANA_SPLIT.get());
        this.simpleItem(ModItems.BBQ_RIBS.get());
        this.simpleItem(ModItems.FRIES.get());
        this.simpleItem(ModItems.PAD_THAI.get());
        this.simpleItem(ModItems.PHO.get());
        this.simpleItem(ModItems.POTATO_CHIPS.get());
        this.simpleItem(ModItems.SAUTEED_FROG_LEGS.get());
        this.simpleItem(ModItems.SEAFOOD_DINNER.get());
        this.simpleItem(ModItems.SMOOTHIE_OF_DARKNESS.get());
        this.simpleItem(ModItems.SUGAR_COOKIE.get());
        this.simpleItem(ModItems.ICE_CREAM.get());
        this.simpleItem(ModItems.MILKSHAKE.get());
        this.simpleItem(ModItems.SASHIMI.get());
        this.simpleItem(ModItems.CHRISTMAS_PUDDING.get());
        this.simpleItem(ModItems.MONSTER_LASAGNA.get());
        this.simpleItem(ModItems.MARSHMALLOW.get());
        this.simpleItem(ModItems.COOKED_MARSHMALLOW.get());
        this.simpleItem(ModItems.FROGGLE_BUNWICH.get());
        */
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
