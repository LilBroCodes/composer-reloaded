package org.lilbrocodes.composer_reloaded.client.duped_binds;

import net.minecraft.client.option.KeyBinding;
import org.jetbrains.annotations.ApiStatus;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.client.config.ComposerConfig;

import java.util.Collection;
import java.util.HashSet;

public class BindTracker {
    @ApiStatus.Internal
    public static final Collection<KeyBinding> MC_CM_BINDS = new HashSet<>();

    @ApiStatus.Internal
    public static boolean bindAllowed(KeyBinding keyBinding) {
        if (!ComposerReloaded.dupedBinds()) return false;
        if (keyBinding == null) return false;
        return switch (ComposerConfig.INSTANCE.allowDuplicateKeybinds) {
            case NONE -> false;
            case MC_AND_CM -> MC_CM_BINDS.contains(keyBinding);
            case ALL -> true;
        };
    }
}
