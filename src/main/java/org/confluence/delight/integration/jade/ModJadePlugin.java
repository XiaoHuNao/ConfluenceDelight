package org.confluence.delight.integration.jade;

import org.confluence.delight.common.block.function.crafting.MillStoneBlock;
import org.confluence.delight.common.block.function.crafting.MillStoneBlockEntity;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class ModJadePlugin implements IWailaPlugin {
    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(MillStoneInfoProvider.INSTANCE, MillStoneBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(MillStoneInfoProvider.INSTANCE, MillStoneBlock.class);
    }
}
