package org.confluence.delight.common.data.gen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.data.gen.recipe.*;
import org.confluence.delight.common.data.gen.tag.ModBlockTagsProvider;
import org.confluence.delight.common.data.gen.tag.ModFluidTagsProvider;
import org.confluence.delight.common.data.gen.tag.ModItemTagsProvider;
import org.confluence.lib.common.data.gen.CollectRecipeProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;


@EventBusSubscriber(modid = ConfluenceDelight.MODID)
public class CDDataGenerator {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();
        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(output, lookup, helper);
        ModFluidTagsProvider fluidTagsProvider = new ModFluidTagsProvider(output, lookup, helper);

        boolean client = event.includeClient();
        generator.addProvider(client, new ModItemModelProvider(output, helper));
        generator.addProvider(client, new ModLanguageProvider(output, "en_us"));
        generator.addProvider(client, new ModLanguageProvider(output, "zh_cn"));

        boolean server = event.includeServer();
        lookup = generator.addProvider(server, new DatapackBuiltinEntriesProvider(output, lookup, CDDataProvider.DATA_BUILDER, Set.of(ConfluenceDelight.MODID))).getRegistryProvider();
        generator.addProvider(server, blockTagsProvider);
        generator.addProvider(server, fluidTagsProvider);
        generator.addProvider(server, new ModItemTagsProvider(output, lookup, blockTagsProvider.contentsGetter(), helper));
        generator.addProvider(server, new CollectRecipeProvider(ConfluenceDelight.asPlainId("server"), output, lookup,
            CDRecipeProvider::new,
            ConfluenceRecipeProvider::new,
            HeavyWorkBenchProvider::new,
            FDRecipeProvider::new,
            VanillaRecipeProvider::new));
        generator.addProvider(server, new CDMusicProvider(output, lookup));
        generator.addProvider(server, new ModLootTableProvider(output, lookup));
        generator.addProvider(server, new CDLootModifiersProvider(output, lookup));
    }
}