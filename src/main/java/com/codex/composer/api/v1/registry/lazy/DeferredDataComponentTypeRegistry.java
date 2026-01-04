package com.codex.composer.api.v1.registry.lazy;

//? if minecraft: >= 1.21 {
import net.minecraft.component.ComponentType;
//? } else if minecraft: >=1.20.6 {
/*import net.minecraft.component.DataComponentType;
 *///? }

//? if minecraft: >=1.20.6 {
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class DeferredDataComponentTypeRegistry extends EmptyDeferredRegistry {
    public DeferredDataComponentTypeRegistry(String modId) {
        super(modId);
    }

    public <T> /*? minecraft: >=1.21 {*/ComponentType/*? } else {*//*DataComponentType*//*? }*/<T> register(String name, UnaryOperator</*? minecraft: >=1.21 {*/ComponentType/*? } else {*//*DataComponentType*//*? }*/.Builder<T>> handler) {
        return Registry.register(
                Registries.DATA_COMPONENT_TYPE,
                Identifier.of(modId, name),
                handler.apply(/*? minecraft: >=1.21 {*/ComponentType/*? } else {*//*DataComponentType*//*? }*/.builder()).build()
        );
    }
}
//? }