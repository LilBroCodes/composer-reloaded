package org.lilbrocodes.composer_reloaded.api.registry.lazy;

//? if minecraft: >=1.21.4 {
/*import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class DeferredDataComponentTypeRegistry extends EmptyDeferredRegistry {
    public DeferredDataComponentTypeRegistry(String modId) {
        super(modId);
    }

    public <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> handler) {
        return Registry.register(
                Registries.DATA_COMPONENT_TYPE,
                Identifier.of(modId, name),
                handler.apply(ComponentType.builder()).build()
        );
    }
}
*///? }
