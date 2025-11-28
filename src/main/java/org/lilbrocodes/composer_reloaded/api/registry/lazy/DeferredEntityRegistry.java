package org.lilbrocodes.composer_reloaded.api.registry.lazy;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

@SuppressWarnings("ClassCanBeRecord")
public class DeferredEntityRegistry {
    private final String modId;

    public DeferredEntityRegistry(String modId) {
        this.modId = modId;
    }

    public <T extends Entity> EntityType<T> register(String name, EntityType<T> type) {
        Identifier id = new Identifier(modId, name);
        return Registry.register(Registries.ENTITY_TYPE, id, type);
    }

    public <T extends Entity> EntityType<T> register(String name, FabricEntityTypeBuilder<T> builder) {
        return register(name, builder.build());
    }
}
