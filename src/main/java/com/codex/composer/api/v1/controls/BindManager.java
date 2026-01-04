package com.codex.composer.api.v1.controls;

import net.minecraft.client.option.KeyBinding;
import com.codex.composer.internal.client.duped_binds.BindTracker;

public class BindManager {
    public static void addDuplicateAllowedKeybind(KeyBinding bind) {
        BindTracker.MC_CM_BINDS.add(bind);
    }
}
