package org.lilbrocodes.composer_reloaded.internal.registry;

import net.minecraft.registry.*;
import org.lilbrocodes.composer_reloaded.api.v1.advancement.ComposerAdvancement;
import org.lilbrocodes.composer_reloaded.api.v1.util.PredicateVoid;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class ModRegistries {
    public static final RegistryKey<Registry<ComposerAdvancement>> COMPOSER_ADVANCEMENT_KEY =
            RegistryKey.ofRegistry(ComposerReloaded.identify("composer_advancement"));
    public static RegistryWrapper.Impl<ComposerAdvancement> COMPOSER_ADVANCEMENTS;

    public static void initialize() {
        RegistryBuilder builder = new RegistryBuilder();

        builder.addRegistry(COMPOSER_ADVANCEMENT_KEY, PredicateVoid::nil);

        RegistryWrapper.WrapperLookup lookup = builder.createWrapperLookup(DynamicRegistryManager.EMPTY);
        COMPOSER_ADVANCEMENTS = lookup./*? if minecraft: <=1.20.1 { *//*getWrapperOrThrow*//*?} else {*/getOrThrow/*?}*/(COMPOSER_ADVANCEMENT_KEY);
    }
}
