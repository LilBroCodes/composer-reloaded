package org.lilbrocodes.composer_reloaded.api.events.composite;

import com.google.gson.JsonObject;
import org.lilbrocodes.composer_reloaded.api.util.misc.AbstractPseudoRegistry;

import java.util.function.Function;

public class CompositeEventRegistry extends AbstractPseudoRegistry<Function<JsonObject, CompositeEvent>> {
    private static CompositeEventRegistry INSTANCE;

    private CompositeEventRegistry() {

    }

    public static CompositeEventRegistry getInstance() {
        if (INSTANCE == null) INSTANCE = new CompositeEventRegistry();
        return INSTANCE;
    }
}
