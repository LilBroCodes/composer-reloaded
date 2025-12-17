package org.lilbrocodes.composer_reloaded.internal.registry;

import net.minecraft.item.BlockItem;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.DeferredItemRegistry;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class ModItems {
    private static final DeferredItemRegistry REGISTRY = new DeferredItemRegistry(ComposerReloaded.MOD_ID, ModItemGroups.COMPOSER);

    public static final BlockItem PLUSHIE = REGISTRY.register(ModBlocks.PLUSH, "plush");

    public static void initialize() {
        REGISTRY.finalizeRegistration();
    }
}
