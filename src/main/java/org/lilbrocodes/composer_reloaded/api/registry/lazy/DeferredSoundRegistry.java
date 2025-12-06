package org.lilbrocodes.composer_reloaded.api.registry.lazy;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class DeferredSoundRegistry extends EmptyDeferredRegistry {
    private final List<SoundEvent> sounds = new ArrayList<>();

    public DeferredSoundRegistry(String modId) {
        super(modId);
    }

    public SoundEvent register(String name) {
        SoundEvent soundEvent = SoundEvent.of(Identifier.of(modId, name));
        return Registry.register(Registries.SOUND_EVENT, soundEvent.getId(), soundEvent);
    }

    @Deprecated(since = "1.7", forRemoval = true)
    public void finalizeSounds() {

    }
}
