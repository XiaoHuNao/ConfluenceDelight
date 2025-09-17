package org.confluence.delight.common;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;

public class CDCommonConfigs {
    public static ModConfigSpec.BooleanValue ENABLE_TOOLTIP;

    public static void onLoad() {
    }

    public static void register(ModContainer container) {
        Builder BUILDER = new ModConfigSpec.Builder();
        {
            BUILDER.push("misc");
            ENABLE_TOOLTIP = BUILDER.define("enable_tooltip", true);
            BUILDER.pop();
        }
        container.registerConfig(ModConfig.Type.COMMON, BUILDER.build());
    }
}
