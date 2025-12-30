package org.lilbrocodes.composer_reloaded.api.v1.velora.emitter;

import org.lilbrocodes.composer_reloaded.api.v1.util.Vec2;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BurstEmitterGroup {
    private final Map<UUID, EmitterEntry<?>> emitters = new HashMap<>();

    private BurstEmitterGroup() {

    }

    public static BurstEmitterGroup create() {
        return new BurstEmitterGroup();
    }

    public <T extends BurstParticleEmitter> UUID add(UUID uuid, Vec2 position, T emitter) {
        emitters.put(uuid, new EmitterEntry<>(position, emitter));
        return uuid;
    }

    public <T extends BurstParticleEmitter> UUID add(Vec2 position, T emitter) {
        return add(UUID.randomUUID(), position, emitter);
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

    private record EmitterEntry<E extends BurstParticleEmitter>(Vec2 pos, E emitter) {
    }
}
