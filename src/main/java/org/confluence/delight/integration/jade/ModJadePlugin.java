package org.confluence.delight.integration.jade;

import org.confluence.delight.common.block.function.crafting.JuicerBlock;
import org.confluence.delight.common.block.function.crafting.JuicerBlockEntity;
import org.confluence.delight.common.block.function.crafting.PickleJarsBlock;
import org.confluence.delight.common.block.function.crafting.PickleJarsBlockEntity;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class ModJadePlugin implements IWailaPlugin {
    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(PickleJarsInfoProvider.INSTANCE, PickleJarsBlockEntity.class);
        registration.registerBlockDataProvider(JuicerInfoProvider.INSTANCE, JuicerBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(PickleJarsInfoProvider.INSTANCE, PickleJarsBlock.class);
        registration.registerBlockComponent(JuicerInfoProvider.INSTANCE, JuicerBlock.class);
    }
}
