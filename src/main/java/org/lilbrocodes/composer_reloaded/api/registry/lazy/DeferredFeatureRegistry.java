package org.lilbrocodes.composer_reloaded.api.registry.lazy;

import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.feature.Feature;
import org.lilbrocodes.composer_reloaded.api.feature.Features;

public class DeferredFeatureRegistry extends EmptyDeferredRegistry {
    public DeferredFeatureRegistry(String modId) {
        super(modId);
    }

    public Feature register(String id, boolean def) {
        return Features.register(Identifier.of(modId, id), def);
    }

    public Feature register(String id) {
        return register(id, true);
    }

    public Feature get(String id, boolean def) {
        return new Feature(Identifier.of(modId, id), def);
    }

    public Feature get(String id) {
        return get(id, true);
    }
}
