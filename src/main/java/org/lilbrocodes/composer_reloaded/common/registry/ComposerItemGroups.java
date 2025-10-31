package org.lilbrocodes.composer_reloaded.common.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.DeferredItemGroupRegistry;

public class ComposerItemGroups {
    private static final DeferredItemGroupRegistry REGISTRY = new DeferredItemGroupRegistry(ComposerReloaded.MOD_ID);

    public static final RegistryKey<ItemGroup> COMPOSER = REGISTRY.registerItemGroup(
            "composer",
            () -> new ItemStack(ComposerBlocks.PLUSH.item)
    );

    public static void initialize() {

    }
}
