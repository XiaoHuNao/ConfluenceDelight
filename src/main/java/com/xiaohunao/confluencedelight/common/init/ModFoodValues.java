package com.xiaohunao.confluencedelight.common.init;

import net.minecraft.world.food.FoodProperties;

public class ModFoodValues {
    public static final int BRIEF_DURATION = 600;    // 30 seconds
    public static final int SHORT_DURATION = 1200;    // 1 minute
    public static final int MEDIUM_DURATION = 3600;    // 3 minutes
    public static final int LONG_DURATION = 6000;    // 5 minutes


    public static final FoodProperties EMPTY = new FoodProperties.Builder().build();
}
