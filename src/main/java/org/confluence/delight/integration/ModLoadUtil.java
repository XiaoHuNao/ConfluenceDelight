package org.confluence.delight.integration;

import net.neoforged.fml.ModList;

public class ModLoadUtil {

    public static boolean isCreateLoaded() {
        return ModList.get().isLoaded("create");
    }

    public static boolean isFruitDelightLoaded() {
        return ModList.get().isLoaded("fruitsdelight");
    }

}
