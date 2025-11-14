package org.lilbrocodes.composer_reloaded.api.events.builtin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.Nullable;
import org.lilbrocodes.composer_reloaded.api.events.ScrollEvents;

public abstract class ItemFilterScrollEvent implements ScrollEvents.ScrollAction {
    private final Hand hand;
    private final Item item;

    protected ItemFilterScrollEvent(Hand hand, Item item) {
        this.hand = hand;
        this.item = item;
    }

    @Override
    public boolean onScroll(MinecraftClient client, @Nullable ClientWorld world, @Nullable ClientPlayerEntity player, double scrollAmount) {
        if (player == null) return false;

        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(item)) return onScroll(client, stack, world, player, scrollAmount);

        return false;
    }

    public abstract boolean onScroll(MinecraftClient client, ItemStack stack, @Nullable ClientWorld world, @Nullable ClientPlayerEntity player, double scrollAmount);
}
