package org.lilbrocodes.composer_reloaded.api.v1.registry.lazy;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

//? if minecraft: <=1.20.1
//import org.lilbrocodes.composer_reloaded.api.v1.util.misc.TranslatableSoundEvent;

public class DeferredSoundRegistry extends EmptyDeferredRegistry {
    public DeferredSoundRegistry(String modId) {
        super(modId);
    }

    public SoundEvent register(String name) {
        SoundEvent soundEvent = SoundEvent.of(Identifier.of(modId, name));
        return Registry.register(Registries.SOUND_EVENT, /*? if minecraft: <=1.20.1 { */ /*soundEvent.getId() *//*? } else {*/soundEvent.id()/*?}*/, soundEvent);
    }

    //? if minecraft: <=1.20.1 {
    /*public TranslatableSoundEvent register(String name, String translation) {
        return register(name, modId, translation);
    }

    public TranslatableSoundEvent register(String name, String translationPrefix, String translationKey) {
        TranslatableSoundEvent soundEvent = TranslatableSoundEvent.of(Identifier.of(modId, name));
        return Registry.register(Registries.SOUND_EVENT, soundEvent.getId(), soundEvent).translate(translationPrefix + "." + translationKey);
    }
    *///?}
}
