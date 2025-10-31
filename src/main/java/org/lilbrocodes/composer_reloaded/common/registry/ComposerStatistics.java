package org.lilbrocodes.composer_reloaded.common.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;

public class ComposerStatistics {
    public static final Identifier PLUSH_BOOP = Registry.register(Registries.CUSTOM_STAT, "boop_plush", ComposerReloaded.identify("boop_plush"));

    public static void initialize() {

    }
}
