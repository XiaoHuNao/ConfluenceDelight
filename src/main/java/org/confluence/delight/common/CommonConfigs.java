package org.confluence.delight.common;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;

public class CommonConfigs {

    public static void onLoad() {}

    public static void register(ModContainer container) {
        Builder BUILDER = new ModConfigSpec.Builder();
        {
            BUILDER.push("machines");
            BUILDER.pop();
        }
        container.registerConfig(ModConfig.Type.COMMON, BUILDER.build());
    }
}
