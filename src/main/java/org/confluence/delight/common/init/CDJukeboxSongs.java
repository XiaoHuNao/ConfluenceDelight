package org.confluence.delight.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.JukeboxSongs;
import org.confluence.delight.ConfluenceDelight;

public class CDJukeboxSongs implements JukeboxSongs {
    public static ResourceKey<JukeboxSong> HOT_STAR_CHICKEN = create("hot_star_chicken");

    private static ResourceKey<JukeboxSong> create(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ConfluenceDelight.asResource(name));
    }
}
