package org.lilbrocodes.composer_reloaded.cca.entity;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import org.jetbrains.annotations.Nullable;
import org.lilbrocodes.composer_reloaded.cca.ModCardinalComponents;

import java.util.Objects;
import java.util.UUID;

public class TargetedEntityComponent implements AutoSyncedComponent, ServerTickingComponent {
    private static final String UUID_KEY = "uuid";
    private static final String TICKS_KEY = "ticks";

    private final PlayerEntity player;
    private UUID uuid = null;
    private int ticks = -1;

    public TargetedEntityComponent(PlayerEntity player) {
        this.player = player;
    }

    public @Nullable UUID getUuid() {
        return uuid;
    }

    public void sync() {
        ModCardinalComponents.TARGETED_ENTITY.sync(player);
    }

    public void setUuid(UUID pos) {
        this.uuid = pos;
        ticks = 0;
        sync();
    }

    public int getTicks() {
        return ticks;
    }

    public void serverTick() {
        ticks++;
        sync();
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        if (this.uuid != null) {
            nbtCompound.put(UUID_KEY, NbtHelper.fromUuid(uuid));
        }
        nbtCompound.putInt(TICKS_KEY, ticks);
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        if (tag.contains(UUID_KEY)) {
            this.uuid = tag.contains(UUID_KEY) ? NbtHelper.toUuid(Objects.requireNonNull(tag.get(UUID_KEY))) : null;
        } else {
            this.uuid = null;
        }
        ticks = tag.getInt(TICKS_KEY);
    }
}
