package org.lilbrocodes.composer_reloaded.internal.registry;

import org.jetbrains.annotations.ApiStatus;
import org.lilbrocodes.composer_reloaded.api.v1.registry.lazy.DeferredRegistryRegistry;

import static org.lilbrocodes.composer_reloaded.api.v1.registry.ComposerRegistryKeys.*;
import static org.lilbrocodes.composer_reloaded.api.v1.registry.ComposerRegistries.*;

@ApiStatus.Internal
public class ModRegistries {
    private static final DeferredRegistryRegistry REGISTRY = new DeferredRegistryRegistry();

    @ApiStatus.Internal
    public static void initialize() {
        COMPOSER_ADVANCEMENTS = REGISTRY.create(COMPOSER_ADVANCEMENT_KEY, ModComposerAdvancements::registerAndGetDefault);
        TOAST_SERIALIZERS = REGISTRY.create(TOAST_SERIALIZERS_KEY, ModToastSerializers::registerAndGetDefault);
        OVERLAY_SERIALIZERS = REGISTRY.create(OVERLAY_SERIALIZERS_KEY, ModOverlaySerializers::registerAndGetDefault);

        REGISTRY.finalizeRegistries();
    }
}
