package org.lilbrocodes.composer_reloaded.internal.registry;

import org.lilbrocodes.composer_reloaded.api.registry.lazy.DeferredSoundRegistry;
import org.lilbrocodes.composer_reloaded.api.util.misc.TranslatableSoundEvent;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class ModSounds {
    private static final DeferredSoundRegistry REGISTRY = new DeferredSoundRegistry(ComposerReloaded.MOD_ID);
    public static final TranslatableSoundEvent LILBRO_SQUISH = REGISTRY.register("lilbro_squish", "squish");

    public static void initialize() {

    }
}
