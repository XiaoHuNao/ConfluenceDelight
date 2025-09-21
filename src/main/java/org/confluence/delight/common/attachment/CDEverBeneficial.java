package org.confluence.delight.common.attachment;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.confluence.delight.common.init.CDAttachmentTypes;
import org.jetbrains.annotations.UnknownNullability;

public class CDEverBeneficial implements INBTSerializable<CompoundTag> {
    private boolean isAegisAppleUsed;
    private boolean isSpeedyCokeUsed;

    public CDEverBeneficial() {
        this.isAegisAppleUsed = false;
        this.isSpeedyCokeUsed = false;
    }

    public boolean isAegisAppleUsed() {
        return isAegisAppleUsed;
    }

    public boolean setUtilityAppleUsed() {
        if (isAegisAppleUsed) return false;
        return this.isAegisAppleUsed = true;
    }

    public boolean isSpeedyCokeUsed() {
        return isSpeedyCokeUsed;
    }

    public boolean setSpeedyCokeUsed() {
        if (isSpeedyCokeUsed) return false;
        return this.isSpeedyCokeUsed = true;
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putBoolean("isAegisAppleUsed", isAegisAppleUsed);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.isAegisAppleUsed = nbt.getBoolean("isAegisAppleUsed");
    }

    public static CDEverBeneficial of(LivingEntity living) {
        return living.getData(CDAttachmentTypes.EVER_BENEFICIAL.get());
    }
}