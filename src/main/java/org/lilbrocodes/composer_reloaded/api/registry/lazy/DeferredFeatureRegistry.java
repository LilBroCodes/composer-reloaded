package org.lilbrocodes.composer_reloaded.api.registry.lazy;

import org.lilbrocodes.composer_reloaded.api.feature.ComposerFeatures;
import org.lilbrocodes.composer_reloaded.api.feature.FeatureBuilder;
import org.lilbrocodes.composer_reloaded.api.feature.FeatureHandle;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.feature.Feature;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.feature.FeatureGroup;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.feature.FinalizedFeatureGroup;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.feature.Hanging;

import java.util.function.Consumer;

public class DeferredFeatureRegistry extends EmptyDeferredRegistry {
    public DeferredFeatureRegistry(String modId) {
        super(modId);
    }

    public Feature hang(String name, Consumer<FeatureBuilder> builder) {
        return new Feature(modId, name, builder);
    }

    public Feature hang(String name, boolean def) {
        return hang(name, b -> b.defaultEnabled(def));
    }

    public FeatureGroup hangGroup(Hanging... groups) {
        FeatureGroup group = new FeatureGroup();
        for (Hanging g : groups) group.addAll(g);
        return group;
    }

    public FinalizedFeatureGroup grab(FeatureGroup group, String groupPath) {
        FinalizedFeatureGroup newGroup = new FinalizedFeatureGroup();

        for (Hanging h : group.values()) {
            for (Feature f : h.flatten()) {
                FeatureHandle registered = ComposerFeatures.register(
                        modId,
                        groupPath.isEmpty() ? f.name : groupPath + "/" + f.name,
                        f.builder
                );
                f.setHandle(registered);
                newGroup.add(registered);
            }
        }

        return newGroup;
    }

    public FeatureHandle bunch(FinalizedFeatureGroup group) {
        String rootPath = group.getRootPath();
        FeatureHandle groupHandle = ComposerFeatures.register(modId, rootPath, b -> {
            // Optionally you could propagate child configs here if needed
        });
        group.setGroupHandle(groupHandle);
        return groupHandle;
    }

    public FeatureHandle register(String path, Consumer<FeatureBuilder> builder) {
        return ComposerFeatures.register(modId, path, builder);
    }

    public FeatureHandle register(String path, boolean def) {
        return register(path, b -> b.defaultEnabled(def));
    }

    private String getLeafName(String path) {
        String[] parts = path.split("/");
        return parts[parts.length - 1];
    }
}
