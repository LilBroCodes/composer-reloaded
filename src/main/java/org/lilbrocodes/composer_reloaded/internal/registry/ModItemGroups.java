package org.lilbrocodes.composer_reloaded.internal.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import org.lilbrocodes.composer_reloaded.api.v1.registry.lazy.DeferredItemGroupRegistry;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class ModItemGroups {
    private static final DeferredItemGroupRegistry REGISTRY = new DeferredItemGroupRegistry(ComposerReloaded.MOD_ID);

    public static final RegistryKey<ItemGroup> COMPOSER = REGISTRY.registerItemGroup(
            "composer",
            () -> new ItemStack(ModItems.PLUSHIE)
    );

    @SuppressWarnings("EmptyMethod")
    public static void initialize() {

    }
}
