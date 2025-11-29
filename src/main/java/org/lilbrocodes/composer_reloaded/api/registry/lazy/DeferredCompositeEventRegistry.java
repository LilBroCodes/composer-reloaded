package org.lilbrocodes.composer_reloaded.api.registry.lazy;

import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.events.composite.CompositeEvent;
import org.lilbrocodes.composer_reloaded.api.events.composite.CompositeEventRegistry;

import java.util.function.Function;

@SuppressWarnings("ClassCanBeRecord")
public class DeferredCompositeEventRegistry {
    private final String modId;

    public DeferredCompositeEventRegistry(String modId) {
        this.modId = modId;
    }

    public Identifier register(String path, Function<JsonObject, CompositeEvent> reader) {
        Identifier identifier = new Identifier(modId, path);
        CompositeEventRegistry.getInstance().register(identifier, reader);
        return identifier;
    }
}
