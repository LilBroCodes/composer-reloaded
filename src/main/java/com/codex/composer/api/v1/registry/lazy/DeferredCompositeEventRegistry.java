package com.codex.composer.api.v1.registry.lazy;

import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;
import com.codex.composer.api.v1.events.composite.CompositeEvent;
import com.codex.composer.api.v1.events.composite.CompositeEventRegistry;

import java.util.function.Function;

public class DeferredCompositeEventRegistry extends EmptyDeferredRegistry {
    public DeferredCompositeEventRegistry(String modId) {
        super(modId);
    }

    public Identifier register(String path, Function<JsonObject, CompositeEvent> reader) {
        Identifier identifier = Identifier.of(modId, path);
        CompositeEventRegistry.getInstance().register(identifier, reader);
        return identifier;
    }
}
