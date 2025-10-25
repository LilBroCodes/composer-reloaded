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

    protected void onProgressIncreased(ItemStack stack, PlayerEntity player, int newStep) {

    }

    public static int getStep(ItemStack stack) {
        NbtCompound nbt = stack.getOrCreateNbt();
        return nbt.getInt(STEP_KEY);
    }

    public static void setStep(ItemStack stack, int value) {
        stack.getOrCreateNbt().putInt(STEP_KEY, value);
    }

    public int getMaxSteps() {
        return this.maxSteps;
    }

    public boolean tryIncrementProgress(ItemStack stack, World world, PlayerEntity player, int amount) {
        int current = getStep(stack);
        if (current >= maxSteps) return false;

        int newStep = Math.min(current + amount, maxSteps);
        setStep(stack, newStep);
        onProgressIncreased(stack, player, newStep);

        if (newStep >= maxSteps && !world.isClient) {
            ItemStack completed = getCompletedItem(stack);
            onFinishProcess(stack, world, player);
            player.setStackInHand(player.getActiveHand(), completed);
        }

        return true;
    }

    protected void onFinishProcess(ItemStack stack, World world, PlayerEntity player) {

    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        int step = getStep(stack);
        return Math.round((float) step / maxSteps * 13);
    }
}
