package org.lilbrocodes.composer_reloaded.api.registry.lazy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class DeferredEntityRegistry {
    private final String modId;
    private final List<EntityType<?>> entities = new ArrayList<>();

    public DeferredEntityRegistry(String modId) {
        this.modId = modId;
    }

    public <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        Identifier id = new Identifier(modId, name);
        EntityType<T> type = Registry.register(Registries.ENTITY_TYPE, id, builder.build(name));
        entities.add(type);
        return type;
    }

    public List<EntityType<?>> getRegistered() {
        return entities;
    }
}
