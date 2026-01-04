package com.codex.composer.internal.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import com.codex.composer.internal.Composer;

public class ModStatistics {
    public static final Identifier PLUSH_BOOP = Registry.register(Registries.CUSTOM_STAT, "boop_plush", Composer.identify("boop_plush"));

    @SuppressWarnings("EmptyMethod")
    public static void initialize() {

    }
}
