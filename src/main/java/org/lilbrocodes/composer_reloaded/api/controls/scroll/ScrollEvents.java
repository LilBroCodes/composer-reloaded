package org.lilbrocodes.composer_reloaded.api.controls.scroll;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Nullable;

public class ScrollEvents {
    public static final Event<ScrollAction> HIGH_PRIORITY = createEvent();
    public static final Event<ScrollAction> MEDIUM_PRIORITY = createEvent();
    public static final Event<ScrollAction> LOW_PRIORITY = createEvent();

    private static Event<ScrollAction> createEvent() {
        return EventFactory.createArrayBacked(ScrollAction.class,
                callbacks -> (client, world, player, scrollAmount) -> callActions(callbacks, client, world, player, scrollAmount)
        );
    }

    @FunctionalInterface
    public interface ScrollAction {
        boolean onScroll(MinecraftClient client, @Nullable ClientWorld world, @Nullable ClientPlayerEntity player, double scrollAmount);
    }

    private static boolean callActions(ScrollAction[] callbacks, MinecraftClient client, @Nullable ClientWorld world, @Nullable ClientPlayerEntity player, double scrollAmount) {
        for (ScrollAction action : callbacks) if (action.onScroll(client, world, player, scrollAmount)) return true;
        return false;
    }
}
