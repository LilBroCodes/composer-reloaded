package com.codex.composer.api.v1.registry.lazy.feature;

import java.util.ArrayList;
import java.util.List;

public class FeatureGroup implements Hanging {
    private final List<Hanging> features = new ArrayList<>();

    public void add(Feature f) { features.add(f); }
    public void addAll(Hanging g) { features.addAll(g.flattenAll()); }

    @Override
    public List<Hanging> values() {
        return features;
    }

    public List<Feature> flattenAll() {
        List<Feature> flat = new ArrayList<>();
        for (Hanging h : features) {
            if (h instanceof Feature f) flat.add(f);
            else if (h instanceof FeatureGroup g) flat.addAll(g.flattenAll());
        }
        return flat;
    }
}