package org.lilbrocodes.composer_reloaded.api.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public abstract class AbstractProgressItem extends Item {
    private static final String STEP_KEY = "Step";
    private final int maxSteps;

    public AbstractProgressItem(Settings settings, int steps) {
        super(settings);
        this.maxSteps = steps;
    }

    protected abstract ItemStack getCompletedItem(ItemStack oldStack);

    protected void onProgressIncreased(ItemStack stack, PlayerEntity player, int newStep) {}

    protected void onFinishProcess(ItemStack stack, World world, PlayerEntity player) {}

    public static int getStep(ItemStack stack) {
        return stack.getOrCreateNbt().getInt(STEP_KEY);
    }

    public static void setStep(ItemStack stack, int value) {
        stack.getOrCreateNbt().putInt(STEP_KEY, value);
    }

    public int getMaxSteps() {
        return maxSteps;
    }

    public ItemStack tryIncrementProgress(ItemStack stack, World world, PlayerEntity player, int amount) {
        int current = getStep(stack);
        if (current >= getMaxSteps()) return ItemStack.EMPTY;

        int newStep = Math.min(current + amount, getMaxSteps());
        setStep(stack, newStep);
        onProgressIncreased(stack, player, newStep);

        if (newStep >= getMaxSteps()) {
            onFinishProcess(stack, world, player);
            return getCompletedItem(stack);
        }

        return ItemStack.EMPTY;
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        int step = getStep(stack);
        return Math.round((float) step / getMaxSteps() * 13);
    }
}
