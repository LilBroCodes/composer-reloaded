package org.lilbrocodes.composer_reloaded.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.block.entity.PlushBlockEntity;

public class ComposerBlockEntities {
    public static final BlockEntityType<PlushBlockEntity> PLUSH = register(FabricBlockEntityTypeBuilder.create(PlushBlockEntity::new, ComposerBlocks.PLUSH.block).build(), "plush");

    public static void initialize() {

    }

    private static  <T extends BlockEntity> BlockEntityType<T> register(BlockEntityType<T> blockEntityType, String id) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, ComposerReloaded.identify(id), blockEntityType);
    }
}
