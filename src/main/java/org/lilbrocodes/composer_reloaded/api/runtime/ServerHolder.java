package org.lilbrocodes.composer_reloaded.api.runtime;

import net.minecraft.server.MinecraftServer;
import org.lilbrocodes.composer_reloaded.api.feature.state.FeatureState;

public class ServerHolder {
    private static MinecraftServer server;

    public static void accept(MinecraftServer s) {
        server = s;
    }

    public static MinecraftServer getServer() {
        return server;
    }

    public static boolean has() {
        return server != null;
    }

    public static FeatureState features() {
        return FeatureState.get(server);
    }
}

