package net.kottenpark_mod.screen.custom;

import net.kottenpark_mod.block.custom.WhiteboardBlock;
import net.kottenpark_mod.block.entity.custom.WhiteboardBlockEntity;
import net.kottenpark_mod.block.enums.QandA;
import net.kottenpark_mod.screen.ModScreenHandlers;
import net.kottenpark_mod.state.modproperty.ModProperties;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import org.jspecify.annotations.Nullable;

import java.util.Arrays;

public class CustomScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final String subjectName;
    private final String[] questionArray;
    private final int correctAnswer;
    private final boolean succeeded;

    public CustomScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.getEntityWorld().getBlockEntity(pos));

    }

    public CustomScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity) {
        super(ModScreenHandlers.CUSTOM_SCREEN_HANDLER, syncId);
        this.inventory = ((Inventory) blockEntity);

        BlockState blockState = ((WhiteboardBlockEntity) blockEntity).getBlockState();
        this.subjectName = blockState.get(ModProperties.Q_AND_A).asString();
        this.questionArray = blockState.get(ModProperties.Q_AND_A).getQuestionArray();
        this.correctAnswer = blockState.get(ModProperties.Q_AND_A).getCorrectIndex();
        this.succeeded = blockState.get(ModProperties.SUCCEEDED).booleanValue();
    }


    public Inventory getInventory() {return this.inventory;}
    public String getSubject() {return this.subjectName;}
    public String[] getQuestionArray() {return this.questionArray;}
    public int getCorrectAnswer() {return this.correctAnswer;}

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}
