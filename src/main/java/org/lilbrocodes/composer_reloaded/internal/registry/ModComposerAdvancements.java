package org.lilbrocodes.composer_reloaded.internal.registry;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.v1.advancement.ComposerAdvancement;
import org.lilbrocodes.composer_reloaded.api.v1.util.PredicateVoid;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class ModComposerAdvancements {
    public static ComposerAdvancement IMPOSSIBLE;

    public static void initialize() {

    }

    public static ComposerAdvancement registerAndGetDefault(Registry<ComposerAdvancement> registry) {
        IMPOSSIBLE = Registry.register(
                registry,
                ComposerReloaded.identify("impossible"),
                new ComposerAdvancement(Identifier.of("nil", "nil"), PredicateVoid::never)
        );

        return IMPOSSIBLE;
    }
}
