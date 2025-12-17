package org.lilbrocodes.composer_reloaded.internal.data.loader;

import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.runtime.ServerHolder;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

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
