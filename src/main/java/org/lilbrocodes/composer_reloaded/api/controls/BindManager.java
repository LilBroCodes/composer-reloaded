package org.lilbrocodes.composer_reloaded.api.controls;

import net.minecraft.client.option.KeyBinding;
import org.lilbrocodes.composer_reloaded.internal.client.duped_binds.BindTracker;

public class BindManager {
    public static void addDuplicateAllowedKeybind(KeyBinding bind) {
        BindTracker.MC_CM_BINDS.add(bind);
    }
}
