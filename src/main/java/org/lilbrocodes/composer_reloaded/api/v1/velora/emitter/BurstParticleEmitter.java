package org.lilbrocodes.composer_reloaded.api.v1.velora.emitter;

import org.lilbrocodes.composer_reloaded.api.v1.util.Vec2;
import org.lilbrocodes.composer_reloaded.api.v1.velora.VeloraParticleManager;
import org.lilbrocodes.composer_reloaded.api.v1.velora.particle.VeloraParticle;
import org.lilbrocodes.constructive.api.v1.anno.Constructive;
import org.lilbrocodes.constructive.api.v1.anno.builder.Default;
import org.lilbrocodes.constructive.api.v1.anno.builder.HardRequire;
import org.lilbrocodes.constructive.api.v1.anno.builder.Transient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.random.RandomGenerator;

@Constructive(builder = true)
public class BurstParticleEmitter {
    @Default
    private boolean staggered = false;
    private final int staggerDelay;
    @Default
    private double spread = 100d;
    private final int burstAmount;
    @HardRequire
    private final Function<Vec2, VeloraParticle> particleSupplier;
    @Transient
    private final List<StaggeredBurst> spawnCounters = new ArrayList<>();

    public BurstParticleEmitter(boolean staggered, int staggerDelay, double spread, int burstAmount, Function<Vec2, VeloraParticle> particleSupplier) {
        this.staggered = staggered;
        this.staggerDelay = staggerDelay;
        this.spread = spread;
        this.burstAmount = burstAmount;
        this.particleSupplier = particleSupplier;
    }

    public void trigger(Vec2 origin) {
        if (staggered) {
            spawnCounters.add(new StaggeredBurst(System.currentTimeMillis(), burstAmount, origin.copy()));
            return;
        }
        spawnParticles(origin);
    }

    public void tick(Vec2 origin) {
        List<Integer> rem = new ArrayList<>();
        VeloraParticleManager manager = VeloraParticleManager.getInstance();

        for (int i = 0; i < spawnCounters.size(); i++) {
            StaggeredBurst burst = spawnCounters.get(i);
            if (System.currentTimeMillis() - burst.lastSpawnTime() > staggerDelay) {
                spawnParticle(manager, burst.origin());
                if (burst.remaining() <= 1) {
                    rem.add(i);
                } else {
                    spawnCounters.set(i, new StaggeredBurst(System.currentTimeMillis(), burst.remaining() - 1, burst.origin()));
                }
            }
        }

        rem.forEach(i -> spawnCounters.remove((int) i));
    }

    private void spawnParticles(Vec2 origin) {
        VeloraParticleManager manager = VeloraParticleManager.getInstance();
        for (int i = 0; i < burstAmount; i++) {
            spawnParticle(manager, origin);
        }
    }

    private void spawnParticle(VeloraParticleManager manager, Vec2 origin) {
        Vec2 spawnPos = origin.copy();
        RandomGenerator random = new Random();
        if (spread > 0.0f) {
            double angle = random.nextDouble() * Math.PI * 2;
            double radius = Math.sqrt(random.nextDouble()) * spread;
            double offsetX = Math.cos(angle) * radius;
            double offsetY = Math.sin(angle) * radius;
            spawnPos = origin.copy().add(new Vec2((float) offsetX, (float) offsetY));
        }

        manager.spawnParticle(spawnPos, particleSupplier.apply(spawnPos));
    }

    record StaggeredBurst(long lastSpawnTime, int remaining, Vec2 origin) {
    }
}
