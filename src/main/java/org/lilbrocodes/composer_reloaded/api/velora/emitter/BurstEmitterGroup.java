package org.lilbrocodes.composer_reloaded.api.velora.emitter;

import org.lilbrocodes.composer_reloaded.api.util.Vec2;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BurstEmitterGroup {
    private final Map<UUID, EmitterEntry<?>> emitters = new HashMap<>();

    public <T extends BurstParticleEmitter> UUID addEmitter(UUID uuid, Vec2 position, T emitter) {
        emitters.put(uuid, new EmitterEntry<>(position, emitter));
        return uuid;
    }

    public <T extends BurstParticleEmitter> UUID addEmitter(Vec2 position, T emitter) {
        return addEmitter(UUID.randomUUID(), position, emitter);
    }

    public void tick() {
        for (EmitterEntry<?> emitter : emitters.values()) {
            emitter.emitter.tick(emitter.pos);
        }
    }

    public void trigger(UUID id) {
        EmitterEntry<?> entry = emitters.get(id);
        if (entry == null) return;
        entry.emitter.trigger(entry.pos);
    }

    public void move(UUID id, Vec2 pos) {
        EmitterEntry<?> entry = emitters.get(id);
        if (entry == null) return;
        emitters.put(id, new EmitterEntry<BurstParticleEmitter>(pos, entry.emitter));
    }

    private record EmitterEntry<E extends BurstParticleEmitter>(Vec2 pos, E emitter) {}
}
