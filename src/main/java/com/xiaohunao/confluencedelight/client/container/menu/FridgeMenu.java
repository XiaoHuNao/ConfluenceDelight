package com.xiaohunao.confluencedelight.client.container.menu;

import com.mojang.datafixers.util.Pair;
import com.xiaohunao.confluencedelight.ConfluenceDelight;
import com.xiaohunao.confluencedelight.client.ModClient;
import com.xiaohunao.confluencedelight.common.block.crafting.FridgeBlock;
import com.xiaohunao.confluencedelight.common.init.ModBlocks;
import com.xiaohunao.confluencedelight.common.init.ModTags;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;

import java.util.Objects;

public class FridgeMenu extends AbstractContainerMenu {
    private ItemStackHandler inventory;
    private FridgeBlock.Entity entity;
    private ContainerData data;
    private final ContainerLevelAccess access;
    public static final ResourceLocation EMPTY_CONTAINER_SLOT_ICE =
            ResourceLocation.fromNamespaceAndPath(ConfluenceDelight.MODID, "item/empty_container_slot_ice");

    public FridgeMenu(int windowId, Inventory playerInventory, FriendlyByteBuf data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data), new SimpleContainerData(4), ContainerLevelAccess.NULL);
    }

    public FridgeMenu(int containerId, Inventory inventory, FridgeBlock.Entity entity, ContainerData data, ContainerLevelAccess access) {
        super(ModClient.ModMenuTypes.FRIDGE.get(), containerId);
        this.inventory = entity.getInventory();
        this.entity = entity;
        this.data = data;
        this.access = access;

        int inputStartX = 30;
        int inputStartY = 17;
        int borderSlotSize = 18;
        final int OFFSET = 13;

        for(int startPlayerInvY = 0; startPlayerInvY < 2; ++startPlayerInvY) {
            for(int column = 0; column < 3; ++column) {
                this.addSlot(new SlotItemHandler(this.inventory, startPlayerInvY * 3 + column,
                        inputStartX + column * borderSlotSize + OFFSET,
                        inputStartY + startPlayerInvY * borderSlotSize));
            }
        }

        this.addSlot(new SlotItemHandler(this.inventory, 6, 124 + OFFSET, 25));
        this.addSlot(new SlotItemHandler(this.inventory, 7, 92 + OFFSET, 55) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.CONTAINER);
            }

            @Override
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, CookingPotMenu.EMPTY_CONTAINER_SLOT_BOWL);
            }
        });
        this.addSlot(new SlotItemHandler(this.inventory, 8, 124 + OFFSET, 55));
        this.addSlot(new SlotItemHandler(this.inventory, 9, 22, 55){
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(ModTags.Items.ICE);
            }

            @Override
            public @Nullable Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, EMPTY_CONTAINER_SLOT_ICE);
            }
        });


        int l;
        int i1;

        for(l = 0; l < 3; ++l) {
            for(i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(inventory, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }

        for(l = 0; l < 9; ++l) {
            this.addSlot(new Slot(inventory, l, 8 + l * 18, 142));
        }

        addDataSlots(data);
    }

    public void slotsChanged(Container container) {
        super.slotsChanged(container);

    }
    @Override
    public boolean clickMenuButton(@NotNull Player pPlayer, int pId) {
        if(!pPlayer.level().isClientSide){
            broadcastChanges();
        }
        return true;
    }
    private static FridgeBlock.Entity getTileEntity(Inventory playerInventory, FriendlyByteBuf data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        BlockEntity tileAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());
        if (tileAtPos instanceof FridgeBlock.Entity) {
            return (FridgeBlock.Entity)tileAtPos;
        } else {
            throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
        }
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.FRIDGE.get());
    }

    public ContainerData getData() {
        return data;
    }

    public int getIceTemplate(){
        return data.get(0);
    }

    public float getIceTemplateProgress(){
        int i = this.data.get(0);
        int j = this.data.get(1);
        return j != 0 && i != 0 ? Mth.clamp((float)i / (float)j, 0.0F, 1.0F) : 0.0F;
    }
}
