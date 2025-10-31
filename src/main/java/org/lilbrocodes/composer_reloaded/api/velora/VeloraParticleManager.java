package org.lilbrocodes.composer_reloaded.api.velora;

import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.ApiStatus;
import org.lilbrocodes.composer_reloaded.api.util.Vec2;
import org.lilbrocodes.composer_reloaded.api.velora.particle.VeloraParticle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("UnusedReturnValue")
public class VeloraParticleManager {
    private static VeloraParticleManager INSTANCE;
    private final List<ParticleEntry<?>> aliveParticles = new ArrayList<>();

    private VeloraParticleManager() {

    }

    public static VeloraParticleManager getInstance() {
        if (INSTANCE == null) INSTANCE = new VeloraParticleManager();
        return INSTANCE;
    }

    public <T extends VeloraParticle> T spawnParticle(Vec2 origin, T particle) {
        aliveParticles.add(new ParticleEntry<>(particle, origin, System.currentTimeMillis()));
        return particle;
    }

    @ApiStatus.Internal
    public void tick(DrawContext ctx) {
        Iterator<ParticleEntry<?>> iterator = aliveParticles.iterator();
        while (iterator.hasNext()) {
            ParticleEntry<?> particle = iterator.next();
            long passedTime = System.currentTimeMillis() - particle.creationDate;

            if (passedTime > particle.particle.lifetimeSeconds * 1000) {
                iterator.remove();
                continue;
            }

            particle.particle.render(ctx, particle.origin);
        }
    }

    @ApiStatus.Internal
    private record ParticleEntry<E extends VeloraParticle>(E particle, Vec2 origin, Long creationDate) { }
}
