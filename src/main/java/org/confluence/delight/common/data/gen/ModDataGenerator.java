package org.confluence.delight.common.data.gen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.data.gen.recipe.ModRecipe;
import org.confluence.delight.common.data.gen.recipe.VanillaCraftRecipe;
import org.confluence.delight.common.data.gen.tag.ModBlockTagsProvider;
import org.confluence.delight.common.data.gen.tag.ModFluidTagsProvider;
import org.confluence.delight.common.data.gen.tag.ModItemTagsProvider;
import org.confluence.lib.common.data.gen.CollectRecipeProvider;

import java.util.concurrent.CompletableFuture;


@EventBusSubscriber(modid = ConfluenceDelight.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModDataGenerator {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();
        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(output, lookup, helper);
        ModFluidTagsProvider fluidTagsProvider = new ModFluidTagsProvider(output, lookup, helper);

        boolean server = event.includeServer();
        generator.addProvider(server, blockTagsProvider);
        generator.addProvider(server, fluidTagsProvider);
        generator.addProvider(server, new ModItemTagsProvider(output, lookup, blockTagsProvider.contentsGetter(), helper));
        generator.addProvider(server, new CollectRecipeProvider(output, lookup,
                ModRecipe::new,
                VanillaCraftRecipe::new));

        boolean client = event.includeClient();
        generator.addProvider(client, new ModItemModelProvider(output, helper));
//        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, existingFileHelper));
        generator.addProvider(client, new ModLanguageProvider(output, "en_us"));
        generator.addProvider(client, new ModLanguageProvider(output, "zh_cn"));
    }
}