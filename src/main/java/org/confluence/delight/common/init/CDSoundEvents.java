package org.confluence.delight.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.confluence.delight.ConfluenceDelight;

public class CDSoundEvents {
    public static final DeferredRegister<SoundEvent> EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, ConfluenceDelight.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> MILLSTONE_WORK = register("millstone_work");
    public static final DeferredHolder<SoundEvent, SoundEvent> HOT_STAR_CHICKEN = register("hot_star_chicken");

    private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
        return EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ConfluenceDelight.asResource(name)));
    }

}
