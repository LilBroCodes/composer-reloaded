package org.lilbrocodes.composer_reloaded.api.v1.registry.lazy.feature;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface Hanging {
    Collection<? extends Hanging> flattenAll();
    List<Hanging> values();

    default List<Feature> flatten() {
        List<Feature> flat = new ArrayList<>();
        for (Hanging h : values()) {
            if (h instanceof Feature f) flat.add(f);
            else if (h instanceof FeatureGroup g) flat.addAll(g.flattenAll());
        }
        return flat;
    }
}