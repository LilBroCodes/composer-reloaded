package org.lilbrocodes.composer_reloaded.api.registry.lazy.feature;

import org.lilbrocodes.composer_reloaded.api.feature.FeatureHandle;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class FinalizedFeatureGroup {
    private final Map<String, FeatureHandle> children = new LinkedHashMap<>();
    private FeatureHandle groupHandle;

    public FeatureHandle add(FeatureHandle handle) {
        children.put(handle.id().getPath(), handle);
        return handle;
    }

    public FeatureHandle get(String id) { return children.get(id); }

    public void setGroupHandle(FeatureHandle handle) { this.groupHandle = handle; }
    public FeatureHandle getGroupHandle() { return groupHandle; }

    public Collection<FeatureHandle> all() { return children.values(); }

    public String getRootPath() {
        if (children.isEmpty()) return "empty";
        return children.values().iterator().next().id().getPath().split("/")[0];
    }
}