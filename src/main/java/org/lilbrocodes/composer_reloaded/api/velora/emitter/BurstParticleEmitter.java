package org.lilbrocodes.composer_reloaded.api.velora.emitter;

import org.lilbrocodes.composer_reloaded.api.util.Vec2;
import org.lilbrocodes.composer_reloaded.api.util.builder.BuilderFields;
import org.lilbrocodes.composer_reloaded.api.velora.VeloraParticleManager;
import org.lilbrocodes.composer_reloaded.api.velora.particle.VeloraParticle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class BurstParticleEmitter {
    private final boolean staggered;
    private final int staggerDelay;
    private final double spread;
    private final int burstAmount;
    private final Function<Vec2, VeloraParticle> particleSupplier;
    private final List<StaggeredBurst> spawnCounters = new ArrayList<>();


    private BurstParticleEmitter(Builder builder) {
        this.burstAmount = builder.burstAmount;
        this.particleSupplier = builder.particleSupplier;
        this.staggered = builder.staggered;
        this.staggerDelay = builder.staggerDelay;
        this.spread = builder.spread;
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
        Random random = new Random();
        if (spread > 0.0f) {
            double angle = random.nextDouble() * Math.PI * 2;
            double radius = Math.sqrt(random.nextDouble()) * spread;
            double offsetX = Math.cos(angle) * radius;
            double offsetY = Math.sin(angle) * radius;
            spawnPos = origin.copy().add(new Vec2((float) offsetX, (float) offsetY));
        }

        manager.spawnParticle(spawnPos, particleSupplier.apply(spawnPos));
    }

    public static Builder builder(Function<Vec2, VeloraParticle> supplier) {
        return new Builder(supplier);
    }

    public static class Builder {
        private final Function<Vec2, VeloraParticle> particleSupplier;

        private int burstAmount = 0;
        private boolean staggered = false;
        private int staggerDelay = 100;
        private double spread = 0d;

        private Builder(Function<Vec2, VeloraParticle> supplier) {
            this.particleSupplier = supplier;
        }

        public Builder burstAmount(int amount) {
            this.burstAmount = amount;
            return this;
        }

        public BurstParticleEmitter build() {
            BuilderFields.verify(this);
            return new BurstParticleEmitter(this);
        }

        public Builder staggered() {
            staggered = true;
            return this;
        }

        public Builder staggerTicks(int ticks) {
            staggerDelay = 50 * ticks;
            return this;
        }

        public Builder staggerMs(int ms) {
            staggerDelay = ms;
            return this;
        }

        public Builder spread(double n) {
            spread = n;
            return this;
        }
    }

    private record StaggeredBurst(long lastSpawnTime, int remaining, Vec2 origin) {}
}
