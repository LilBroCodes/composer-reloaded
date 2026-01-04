package com.codex.composer.api.v1.registry.lazy.feature;

import net.minecraft.util.Identifier;
import com.codex.composer.api.v1.feature.FeatureBuilder;
import com.codex.composer.api.v1.feature.FeatureHandle;
import com.codex.composer.api.v1.feature.FeatureNode;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class Feature implements Hanging {
    public final String name;
    public final Consumer<FeatureBuilder> builder;
    private FeatureHandle handle;

    public Feature(String modId, String name, Consumer<FeatureBuilder> builder) {
        this.name = name;
        this.builder = builder;
        this.handle = new FeatureHandle(new FeatureNode(
                Identifier.of(modId, name), null));
    }

    public void setHandle(FeatureHandle registeredHandle) {
        this.handle = registeredHandle;
    }

    public FeatureHandle getHandle() {
        return handle;
    }

    @Override
    public List<Hanging> values() {
        return List.of(this);
    }

    public List<Feature> flatten() {
        return List.of(this);
    }

    @Override
    public Collection<? extends Hanging> flattenAll() {
        return List.of(this);
    }
}