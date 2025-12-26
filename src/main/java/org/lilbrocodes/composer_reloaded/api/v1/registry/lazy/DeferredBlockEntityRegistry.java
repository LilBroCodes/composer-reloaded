package org.lilbrocodes.composer_reloaded.api.v1.registry.lazy;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class DeferredBlockEntityRegistry extends EmptyDeferredRegistry {
    public DeferredBlockEntityRegistry(String modId) {
        super(modId);
    }

    public final <T extends BlockEntity> BlockEntityType<T> register(String name, FabricBlockEntityTypeBuilder.Factory<T> factory, Block... blocks) {
        Identifier id = new Identifier(modId, name);
        return Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                id,
                FabricBlockEntityTypeBuilder.create(factory, blocks).build()
        );
    }
}
