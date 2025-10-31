package org.lilbrocodes.composer_reloaded.api.registry.lazy;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

@SuppressWarnings("ClassCanBeRecord")
public class DeferredScreenRegistry {
    private final String modId;

    public DeferredScreenRegistry(String modId) {
        this.modId = modId;
    }

    public <T extends ScreenHandler> ScreenHandlerType<T> register(String name, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(
                Registries.SCREEN_HANDLER, Identifier.of(modId, name),
                new ScreenHandlerType<>(factory, FeatureFlags.VANILLA_FEATURES));
    }

    public <T extends ScreenHandler> void registerClient(ScreenHandlerType<T> type,
                                                         HandledScreens.Provider<T, ?> screenProvider) {
        HandledScreens.register(type, screenProvider);
    }
}
