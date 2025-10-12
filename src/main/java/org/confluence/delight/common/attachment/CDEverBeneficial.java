package org.confluence.delight.common.attachment;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.confluence.delight.common.init.CDAttachmentTypes;
import org.jetbrains.annotations.UnknownNullability;

public class CDEverBeneficial implements INBTSerializable<CompoundTag> {
    private boolean isUtilityAppleUsed;
    private boolean isSpeedyCokeUsed;
    private boolean isEzConstantUsed;


    public CDEverBeneficial() {
        this.isUtilityAppleUsed = false;
        this.isSpeedyCokeUsed = false;
        this.isEzConstantUsed = false;
    }

    public boolean isUtilityAppleUsed() {
        return isUtilityAppleUsed;
    }

    public boolean setUtilityAppleUsed() {
        if (isUtilityAppleUsed) return false;
        return this.isUtilityAppleUsed = true;
    }

    public boolean isSpeedyCokeUsed() {
        return isSpeedyCokeUsed;
    }

    public boolean setSpeedyCokeUsed() {
        if (isSpeedyCokeUsed) return false;
        return this.isSpeedyCokeUsed = true;
    }

    public boolean isEzConstantUsed() {
        return isEzConstantUsed;
    }

    public boolean setEzConstantUsed() {
        if (isEzConstantUsed) return false;
        return this.isEzConstantUsed = true;
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();
        nbt.putBoolean("isUtilityAppleUsed", isUtilityAppleUsed);
        nbt.putBoolean("isSpeedyCokeUsed", isSpeedyCokeUsed);
        nbt.putBoolean("isEzConstantUsed", isEzConstantUsed);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.isUtilityAppleUsed = nbt.getBoolean("isUtilityAppleUsed");
        this.isSpeedyCokeUsed = nbt.getBoolean("isSpeedyCokeUsed");
        this.isEzConstantUsed = nbt.getBoolean("isEzConstantUsed");
    }

    public static CDEverBeneficial of(LivingEntity living) {
        return living.getData(CDAttachmentTypes.EVER_BENEFICIAL.get());
    }
}