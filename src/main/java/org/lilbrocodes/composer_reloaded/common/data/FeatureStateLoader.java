package org.lilbrocodes.composer_reloaded.common.data;

import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.api.runtime.ServerHolder;

public class FeatureStateLoader implements SimpleSynchronousResourceReloadListener {
    private static final Identifier ID = ComposerReloaded.identify("feature_state");

    @Override
    public Identifier getFabricId() {
        return ID;
    }

    @Override
    public void reload(ResourceManager manager) {
        if (ServerHolder.has()) ServerHolder.reloadFeatures();
    }
}
