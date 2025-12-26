package org.lilbrocodes.composer_reloaded.api.v1.events.composite;

import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public final class CompositeEventSerializer {
    public static CompositeEvent read(JsonObject json) {
        if (json == null) return null;

        Identifier id = Identifier.tryParse(json.get(CompositeEvent.ID).getAsString());
        if (id == null) return null;

        Function<JsonObject, CompositeEvent> factory = CompositeEventRegistry.getInstance().get(id);
        if (factory == null) return null;

        JsonObject data = json.has(CompositeEvent.DATA) ? json.getAsJsonObject(CompositeEvent.DATA) : new JsonObject();
        return factory.apply(data);
    }

    public static JsonObject write(CompositeEvent event) {
        return event == null ? null : event.serialize();
    }
}
