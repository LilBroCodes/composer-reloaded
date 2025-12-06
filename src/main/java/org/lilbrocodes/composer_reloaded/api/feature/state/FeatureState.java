package org.lilbrocodes.composer_reloaded.api.feature.state;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;
import org.lilbrocodes.composer_reloaded.api.nbt.ComposerCompound;
import org.lilbrocodes.composer_reloaded.api.nbt.serial.SerializableBoolean;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FeatureState extends PersistentState {
    public Map<String, SerializableBoolean> states = new HashMap<>();

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        ComposerCompound tag = ComposerCompound.copy(nbt);
        tag.putMap("states", states);
        return tag;
    }

    public static FeatureState get(MinecraftServer server) {
        PersistentStateManager manager = Objects.requireNonNull(server.getWorld(World.OVERWORLD)).getPersistentStateManager();
        return manager.getOrCreate(FeatureState::read, FeatureState::def, "composer_features");
    }

    private static FeatureState def() {
        return new FeatureState();
    }

    private static FeatureState read(NbtCompound nbtCompound) {
        Map<String, SerializableBoolean> map = ComposerCompound.copy(nbtCompound).getMap("states", SerializableBoolean::new);
        FeatureState s = new FeatureState();
        s.states = map;
        return s;
    }
}
