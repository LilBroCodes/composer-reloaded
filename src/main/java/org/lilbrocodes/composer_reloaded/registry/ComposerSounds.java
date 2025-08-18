package org.lilbrocodes.composer_reloaded.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;

import java.util.LinkedList;
import java.util.List;

public class ComposerSounds {
    private static final List<SoundEvent> SOUND_EVENTS = new LinkedList<>();
    public static final SoundEvent LILBRO_SQUISH = create("lilbro_squish");

    public static SoundEvent create(String name) {
        SoundEvent soundEvent = SoundEvent.of(ComposerReloaded.identify(name));
        SOUND_EVENTS.add(soundEvent);
        return soundEvent;
    }

    public static void initialize() {
        SOUND_EVENTS.forEach(soundEvent -> Registry.register(Registries.SOUND_EVENT, soundEvent.getId(), soundEvent));
    }
}
