package org.lilbrocodes.composer_reloaded.api.v1.velora.emitter;

import org.lilbrocodes.composer_reloaded.api.v1.util.Vec2;
import org.lilbrocodes.composer_reloaded.api.v1.velora.VeloraParticleManager;
import org.lilbrocodes.composer_reloaded.api.v1.velora.particle.VeloraParticle;
import org.lilbrocodes.constructive.api.v1.anno.Constructive;
import org.lilbrocodes.constructive.api.v1.anno.builder.Description;
import org.lilbrocodes.constructive.api.v1.anno.builder.HardRequire;
import org.lilbrocodes.constructive.api.v1.anno.builder.Transient;

import java.util.Random;
import java.util.function.Function;

@Constructive(builder = true)
public class ContinuousParticleEmitter {
    @Description("The interval between two emissions in miliseconds.")
    private final int interval;
    private final double spread;
    @HardRequire
    private final Function<Vec2, VeloraParticle> particleSupplier;

    @Transient
    private long lastSpawnTime;
    @Transient
    private boolean enabled;

    ContinuousParticleEmitter(int interval, double spread, Function<Vec2, VeloraParticle> particleSupplier) {
        this.interval = interval;
        this.spread = spread;
        this.particleSupplier = particleSupplier;
        this.lastSpawnTime = System.currentTimeMillis();
    }

    protected void tick(Vec2 origin) {
        if (enabled) {
            long now = System.currentTimeMillis();
            if (now - lastSpawnTime >= interval) {
                lastSpawnTime = now;
                spawnParticle(origin);
            }
        }
    }

    protected void toggle(boolean bl) {
        enabled = bl;
    }

    private void spawnParticle(Vec2 origin) {
        Vec2 spawnPos = origin.copy();
        if (spread > 0.0f) {
            Random random = new Random();
            double angle = random.nextDouble() * Math.PI * 2;
            double radius = Math.sqrt(random.nextDouble()) * spread;
            float offsetX = (float) (Math.cos(angle) * radius);
            float offsetY = (float) (Math.sin(angle) * radius);
            spawnPos = origin.copy().add(new Vec2(offsetX, offsetY));
        }

        VeloraParticleManager.getInstance().spawnParticle(spawnPos, particleSupplier.apply(spawnPos));
    }
}
