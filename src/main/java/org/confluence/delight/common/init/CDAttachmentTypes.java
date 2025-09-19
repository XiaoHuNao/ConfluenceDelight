package org.confluence.delight.common.init;

import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.confluence.delight.ConfluenceDelight;
import org.confluence.delight.common.attachment.CDEverBeneficial;

import java.util.function.Supplier;

public class CDAttachmentTypes {
    public static final DeferredRegister<AttachmentType<?>> TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, ConfluenceDelight.MODID);

    public static final Supplier<AttachmentType<CDEverBeneficial>> EVER_BENEFICIAL = TYPES.register("ever_beneficial", () -> AttachmentType.serializable(CDEverBeneficial::new).copyOnDeath().build());
}
