package org.lilbrocodes.composer_reloaded.api.feature;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.feature.state.FeatureState;
import org.lilbrocodes.composer_reloaded.api.nbt.serial.SerializableBoolean;
import org.lilbrocodes.composer_reloaded.api.util.misc.AbstractPseudoRegistry;

import java.util.Map;
import java.util.stream.Collectors;

public class Features extends AbstractPseudoRegistry<Feature> {
    private static Features INSTANCE;

    private Features() {
        super();
    }

    public static Features getInstance() {
        if (INSTANCE == null) INSTANCE = new Features();
        return INSTANCE;
    }

    @Override
    protected void afterInitialization(MinecraftServer server) {
        FeatureState state = FeatureState.get(server);

        Map<String, Identifier> ids = getAll().keySet().stream()
                .collect(Collectors.toMap(Identifier::toString, i -> i));

        state.states.keySet().removeIf(id -> !ids.containsKey(id));

        ids.forEach((idString, identifier) -> state.states.computeIfAbsent(idString, unused ->
                new SerializableBoolean(getInstance().get(identifier).defaultEnabled())
        ));

        state.markDirty();
    }

    public static Feature register(Identifier id, boolean defaultEnabled) {
        return register(getInstance(), id, defaultEnabled);
    }

    public static Feature register(Features f, Identifier id, boolean defaultEnabled) {
        return f.register(id, new Feature(id, defaultEnabled));
    }
}
