package org.lilbrocodes.composer_reloaded.api.feature;

import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.runtime.ServerHolder;

public record Feature(Identifier id, Boolean defaultEnabled) {
    public static boolean isEnabled(Feature feature) {
        return isEnabled(Features.getInstance().find(feature));
    }

    public static boolean isEnabled(Identifier id) {
        return ServerHolder.has() && ServerHolder.features().states.get(id.toString()).get();
    }

    public boolean enabled() {
        return isEnabled(id);
    }
}
