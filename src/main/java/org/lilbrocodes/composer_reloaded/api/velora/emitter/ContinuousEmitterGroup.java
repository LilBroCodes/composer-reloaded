package org.lilbrocodes.composer_reloaded.api.velora.emitter;

import org.lilbrocodes.composer_reloaded.api.util.Vec2;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ContinuousEmitterGroup {
    private final Map<UUID, EmitterEntry<?>> emitters = new HashMap<>();

    public <T extends ContinuousParticleEmitter> UUID addEmitter(UUID uuid, Vec2 position, T emitter) {
        emitters.put(uuid, new EmitterEntry<>(position, emitter));
        return uuid;
    }

    public <T extends ContinuousParticleEmitter> UUID addEmitter(Vec2 position, T emitter) {
        return addEmitter(UUID.randomUUID(), position, emitter);
    }

    public void tick() {
        for (EmitterEntry<?> entry : emitters.values()) {
            entry.emitter.tick(entry.pos);
        }
    }

    public void move(UUID id, Vec2 pos) {
        EmitterEntry<?> entry = emitters.get(id);
        if (entry == null) return;
        emitters.put(id, new EmitterEntry<ContinuousParticleEmitter>(pos, entry.emitter));
    }

    private void toggle(UUID id, boolean bl) {
        EmitterEntry<?> entry = emitters.get(id);
        if (entry == null) return;
        entry.emitter.toggle(bl);
    }

    public void enable(UUID id) {
        toggle(id, true);
    }

    public void disable(UUID id) {
        toggle(id, false);
    }

    private record EmitterEntry<E extends ContinuousParticleEmitter>(Vec2 pos, E emitter) {
    }
}
