package com.codex.composer.internal.registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import com.codex.composer.api.v1.registry.lazy.DeferredItemGroupRegistry;
import com.codex.composer.internal.Composer;

public class ModItemGroups {
    private static final DeferredItemGroupRegistry REGISTRY = new DeferredItemGroupRegistry(Composer.MOD_ID);

    public static final RegistryKey<ItemGroup> COMPOSER = REGISTRY.registerItemGroup(
            "composer",
            () -> new ItemStack(ModItems.PLUSHIE)
    );

    @SuppressWarnings("EmptyMethod")
    public static void initialize() {

    }
}
