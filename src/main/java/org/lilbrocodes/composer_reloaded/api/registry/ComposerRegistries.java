package org.lilbrocodes.composer_reloaded.api.registry;

import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.advancement.ComposerAdvancement;
import org.lilbrocodes.composer_reloaded.api.util.PredicateVoid;

public class ComposerRegistries {
    public static final RegistryKey<Registry<ComposerAdvancement>> COMPOSER_ADVANCEMENT_KEY =
            RegistryKey.ofRegistry(new Identifier("composer_reloaded", "composer_advancement"));

    public static RegistryWrapper.Impl<ComposerAdvancement> COMPOSER_ADVANCEMENTS;

    public static void initialize() {
        RegistryBuilder builder = new RegistryBuilder();

        builder.addRegistry(COMPOSER_ADVANCEMENT_KEY, PredicateVoid::nil);

        RegistryWrapper.WrapperLookup lookup = builder.createWrapperLookup(DynamicRegistryManager.EMPTY);
        COMPOSER_ADVANCEMENTS = lookup.getWrapperOrThrow(COMPOSER_ADVANCEMENT_KEY);
    }
}
