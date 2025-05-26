package org.confluence.delight;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class StartupConfigs {
    public static ModConfigSpec.IntValue FLUID_CAPACITY;

    public static void onLoad() {}

    public static void register(ModContainer container) {
        ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
        {
            BUILDER.push("machines");
            FLUID_CAPACITY = BUILDER.defineInRange("fluid_capacity", 16000, 1, 64000);
            BUILDER.pop();
        }
        container.registerConfig(ModConfig.Type.STARTUP, BUILDER.build());
    }
}
