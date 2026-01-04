package com.codex.composer.api.v1.registry.lazy;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

//? minecraft: >=1.21.3 {
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
//? }

public class DeferredEntityRegistry extends EmptyDeferredRegistry {
    public DeferredEntityRegistry(String modId) {
        super(modId);
    }

    public <T extends Entity> EntityType<T> register(String name, EntityType<T> type) {
        Identifier id = Identifier.of(modId, name);
        return Registry.register(Registries.ENTITY_TYPE, id, type);
    }

    @SuppressWarnings("deprecation")
    public <T extends Entity> EntityType<T> register(String name, FabricEntityTypeBuilder<T> builder) {
        return register(name, builder.build(/*? if minecraft: >=1.21.3 { */RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(modId, name))/*?}*/));
    }
}
