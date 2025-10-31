package org.lilbrocodes.composer_reloaded.common.registry;

import net.minecraft.sound.SoundEvent;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.DeferredSoundRegistry;

public class ComposerSounds {
    private static final DeferredSoundRegistry REGISTRY = new DeferredSoundRegistry(ComposerReloaded.MOD_ID);
    public static final SoundEvent LILBRO_SQUISH = REGISTRY.register("lilbro_squish");

    public static void initialize() {
        REGISTRY.finalizeSounds();
    }
}
