package org.lilbrocodes.composer_reloaded.api.registry.lazy;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.util.misc.TranslatableSoundEvent;

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

    public TranslatableSoundEvent register(String name, String translation) {
        return register(name, modId, translation);
    }

    public TranslatableSoundEvent register(String name, String translationPrefix, String translationKey) {
        TranslatableSoundEvent soundEvent = TranslatableSoundEvent.of(Identifier.of(modId, name));
        return Registry.register(Registries.SOUND_EVENT, soundEvent.getId(), soundEvent).translate(translationPrefix + "." + translationKey);
    }

    @Deprecated(since = "1.7", forRemoval = true)
    public void finalizeSounds() {

    }
}
