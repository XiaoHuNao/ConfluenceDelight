package org.confluence.delight.util;

import net.neoforged.fml.ModList;

public class ModLoadUtil {

    public static boolean isCreateLoaded() {
        return ModList.get().isLoaded("create");
    }

}
