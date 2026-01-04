package com.codex.composer.api.v1.events.composite;

import com.google.gson.JsonObject;
import com.codex.composer.api.v1.util.misc.AbstractPseudoRegistry;
import com.codex.composer.internal.Composer;

import java.util.function.Function;

public class CompositeEventRegistry extends AbstractPseudoRegistry<Function<JsonObject, CompositeEvent>> {
    private static CompositeEventRegistry INSTANCE;

    private CompositeEventRegistry() {

    }

    @Override
    protected void bootstrap() {
        AbstractPseudoRegistry.identify(Composer.identify("composite_events"), this);
    }

    public static CompositeEventRegistry getInstance() {
        if (INSTANCE == null) INSTANCE = new CompositeEventRegistry();
        return INSTANCE;
    }
}
