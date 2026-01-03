package org.lilbrocodes.composer_reloaded.api.v1.events.composite;

import com.google.gson.JsonObject;
import org.lilbrocodes.composer_reloaded.api.v1.util.misc.AbstractPseudoRegistry;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

import java.util.function.Function;

public class CompositeEventRegistry extends AbstractPseudoRegistry<Function<JsonObject, CompositeEvent>> {
    private static CompositeEventRegistry INSTANCE;

    private CompositeEventRegistry() {

    }

    @Override
    protected void bootstrap() {
        AbstractPseudoRegistry.identify(ComposerReloaded.identify("composite_events"), this);
    }

    public static CompositeEventRegistry getInstance() {
        if (INSTANCE == null) INSTANCE = new CompositeEventRegistry();
        return INSTANCE;
    }
}
