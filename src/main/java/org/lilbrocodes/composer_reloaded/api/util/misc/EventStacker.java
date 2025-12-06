package org.lilbrocodes.composer_reloaded.api.util.misc;

import net.fabricmc.fabric.api.event.Event;

public class EventStacker {
    @SafeVarargs
    public static <T> void registerAll(Event<T> eventRegistrar, T... handlers) {
        for (T handler : handlers) {
            eventRegistrar.register(handler);
        }
    }
}
