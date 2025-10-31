package org.lilbrocodes.composer_reloaded.client.duped_binds;

import net.minecraft.client.option.KeyBinding;
import org.lilbrocodes.composer_reloaded.client.config.ComposerConfig;

import java.util.HashSet;
import java.util.Set;

public class BindTracker {
    public static final Set<KeyBinding> MC_CM_BINDS = new HashSet<>();

    public static boolean bindAllowed(KeyBinding keyBinding) {
        if (keyBinding == null) return false;
        return switch (ComposerConfig.INSTANCE.allowDuplicateKeybinds) {
            case NONE -> false;
            case MC_AND_CM -> MC_CM_BINDS.contains(keyBinding);
            case ALL -> true;
        };
    }
}
