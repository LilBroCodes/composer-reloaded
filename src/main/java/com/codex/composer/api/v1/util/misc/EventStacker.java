package com.codex.composer.api.v1.util.misc;

import net.fabricmc.fabric.api.event.Event;

public class EventStacker {
    @SafeVarargs
    public static <T> void registerAll(Event<T> eventRegistrar, T... handlers) {
        for (T handler : handlers) {
            eventRegistrar.register(handler);
        }
    }
}
