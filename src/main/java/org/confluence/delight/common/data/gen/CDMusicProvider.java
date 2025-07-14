package org.confluence.delight.common.data.gen;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.init.CDJukeboxSongs;
import org.confluence.delight.common.init.CDSoundEvents;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class CDMusicProvider extends DatapackBuiltinEntriesProvider {
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.JUKEBOX_SONG, CDMusicProvider::bootstrap);

    public CDMusicProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ConfluenceDelight.MODID));
    }

    private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, Holder<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
        context.register(key, new JukeboxSong(soundEvent, Component.translatable(Util.makeDescriptionId("jukebox_song", key.location())), (float) lengthInSeconds, comparatorOutput));
    }

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        register(context, CDJukeboxSongs.HOT_STAR_CHICKEN, CDSoundEvents.HOT_STAR_CHICKEN, 240, 1);
    }

    @Override
    public String getName() {
        return "ConfluenceDelight Jukebox Data";
    }
}
