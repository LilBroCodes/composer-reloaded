package org.lilbrocodes.composer_reloaded.components;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import org.jetbrains.annotations.Nullable;
import org.lilbrocodes.composer_reloaded.components.entry.ModEntityComponents;
import org.lilbrocodes.composer_reloaded.components.i.TargetedEntityComponent;

import java.util.UUID;

public class TargetedEntityTracker implements TargetedEntityComponent {
    private UUID uuid = null;
    private int ticks = -1;

    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        if (nbtCompound.contains("Uuid")) {
            this.uuid = nbtCompound.contains("Uuid") ? NbtHelper.toUuid(nbtCompound.get("Uuid")) : null;
        } else {
            this.uuid = null;
        }
        ticks = nbtCompound.getInt("ticks");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        if (this.uuid != null) {
            nbtCompound.put("Uuid", NbtHelper.fromUuid(uuid));
        }
        nbtCompound.putInt("ticks", ticks);
    }

    @Override
    public @Nullable UUID getUuid() {
        return uuid;
    }

    @Override
    public void setUuid(PlayerEntity player, UUID pos) {
        this.uuid = pos;
        ticks = 0;
        ModEntityComponents.TARGETED_ENTITY.sync(player);
    }

    @Override
    public int getTicks() {
        return ticks;
    }

    @Override
    public void tick(PlayerEntity player) {
        ticks++;
        ModEntityComponents.TARGETED_ENTITY.sync(player);
    }
}
