package org.lilbrocodes.composer_reloaded.internal.registry;

import net.minecraft.block.entity.BlockEntityType;
import org.lilbrocodes.composer_reloaded.api.v1.registry.lazy.DeferredBlockEntityRegistry;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.internal.block.entity.PlushBlockEntity;

public class ModBlockEntities {
    private static final DeferredBlockEntityRegistry REGISTRY = new DeferredBlockEntityRegistry(ComposerReloaded.MOD_ID);

    public static final BlockEntityType<PlushBlockEntity> PLUSH = REGISTRY.register(
            "plush",
            PlushBlockEntity::new,
            ModBlocks.PLUSH
    );

    @SuppressWarnings("EmptyMethod")
    public static void initialize() {

    }
}
