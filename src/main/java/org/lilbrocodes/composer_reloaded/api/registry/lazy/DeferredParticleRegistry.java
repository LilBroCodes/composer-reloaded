package org.lilbrocodes.composer_reloaded.api.registry.lazy;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class DeferredParticleRegistry {
    private final String modId;
    private final List<ParticleType<?>> registered = new ArrayList<>();

    public DeferredParticleRegistry(String modId) {
        this.modId = modId;
    }

    public <T extends ParticleEffect> ParticleType<T> register(
            String name, Codec<T> codec, ParticleEffect.Factory<T> deserializer) {

        Identifier id = new Identifier(modId, name);
        ParticleType<T> type = Registry.register(Registries.PARTICLE_TYPE, id,
                new ParticleType<T>(true, deserializer) {
                    @Override
                    public Codec<T> getCodec() {
                        return codec;
                    }
                });
        registered.add(type);
        return type;
    }

    public <T extends ParticleEffect> void registerClient(
            ParticleType<T> type, ParticleFactoryRegistry.PendingParticleFactory<T> factory) {
        ParticleFactoryRegistry.getInstance().register(type, factory);
    }
}
