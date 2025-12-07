package org.lilbrocodes.composer_reloaded.api.runtime;

import net.minecraft.server.MinecraftServer;
import org.lilbrocodes.composer_reloaded.api.feature.state.FeatureState;

public class ServerHolder {
    private static MinecraftServer server;
    private static FeatureState cachedFeatures;

    public static void accept(MinecraftServer s) {
        server = s;
        reloadFeatures();
    }

    public static MinecraftServer getServer() {
        return server;
    }

    public static boolean has() {
        return server != null;
    }

    public static FeatureState features() {
        if (server == null) throw new IllegalStateException("Server not initialized");

        if (cachedFeatures == null) {
            cachedFeatures = FeatureState.get(server);
        }
        return cachedFeatures;
    }

    public static void reloadFeatures() {
        if (server != null) {
            cachedFeatures = FeatureState.get(server);
        }
    }
}
