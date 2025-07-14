package org.lilbrocodes.composer_reloaded.components;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.lilbrocodes.composer_reloaded.components.entry.ModEntityComponents;
import org.lilbrocodes.composer_reloaded.components.i.TargetedBlockComponent;

public class TargetedBlockTracker implements TargetedBlockComponent {
    private BlockPos pos = null;
    private int ticks = -1;

    @Override
    public void readFromNbt(NbtCompound nbtCompound) {
        if (nbtCompound.contains("Pos")) {
            this.pos = nbtCompound.contains("Pos") ? NbtHelper.toBlockPos(nbtCompound.getCompound("Pos")) : null;
        } else {
            this.pos = null;
        }
        ticks = nbtCompound.getInt("ticks");
    }

    @Override
    public void writeToNbt(NbtCompound nbtCompound) {
        if (this.pos != null) {
            nbtCompound.put("Pos", NbtHelper.fromBlockPos(pos));
        }
        nbtCompound.putInt("ticks", ticks);
    }

    @Override
    public @Nullable BlockPos getPos() {
        return pos;
    }

    @Override
    public void setPos(PlayerEntity player, BlockPos pos) {
        this.pos = pos;
        ticks = 0;
        ModEntityComponents.TARGETED_BLOCK.sync(player);
    }

    @Override
    public int getTicks() {
        return ticks;
    }

    @Override
    public void tick(PlayerEntity player) {
        ticks++;
        ModEntityComponents.TARGETED_BLOCK.sync(player);
    }
}
