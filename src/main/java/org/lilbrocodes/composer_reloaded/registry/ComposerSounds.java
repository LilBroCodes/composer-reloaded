package org.lilbrocodes.composer_reloaded.registry;

import net.minecraft.sound.SoundEvent;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.DeferredSoundRegistry;
import org.lilbrocodes.composer_reloaded.api.util.Loadable;

public class ComposerSounds extends Loadable {
    private static final DeferredSoundRegistry REGISTRY = new DeferredSoundRegistry(ComposerReloaded.MOD_ID);
    public static final SoundEvent LILBRO_SQUISH = REGISTRY.register("lilbro_squish");

    public static void initialize() {
        REGISTRY.finalizeSounds();
    }
}
