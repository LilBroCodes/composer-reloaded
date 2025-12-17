package org.lilbrocodes.composer_reloaded.internal.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class ModStatistics {
    public static final Identifier PLUSH_BOOP = Registry.register(Registries.CUSTOM_STAT, "boop_plush", ComposerReloaded.identify("boop_plush"));

    @SuppressWarnings("EmptyMethod")
    public static void initialize() {

    }
}
