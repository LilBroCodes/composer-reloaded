package org.lilbrocodes.composer_reloaded.api.registry.lazy;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class DeferredSoundRegistry {
    private final String modId;
    private final List<SoundEvent> sounds = new ArrayList<>();

    public DeferredSoundRegistry(String modId) {
        this.modId = modId;
    }

    public SoundEvent register(String name) {
        SoundEvent soundEvent = SoundEvent.of(Identifier.of(modId, name));
        sounds.add(soundEvent);
        return soundEvent;
    }

    public void finalizeSounds() {
        sounds.forEach(soundEvent -> Registry.register(Registries.SOUND_EVENT, soundEvent.getId(), soundEvent));
    }
}
