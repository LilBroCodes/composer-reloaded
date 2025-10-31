package org.lilbrocodes.composer_reloaded.cca.entity;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.lilbrocodes.composer_reloaded.cca.ModCardinalComponents;

public class TargetedBlockComponent implements AutoSyncedComponent, ServerTickingComponent {
    private static final String POS_KEY = "pos";
    private static final String TICKS_KEY = "ticks";
    
    private final PlayerEntity player;
    private BlockPos pos = null;
    private int ticks = -1;


    public TargetedBlockComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        ModCardinalComponents.TARGETED_BLOCK.sync(player);
    }

    public @Nullable BlockPos getPos() {
        return pos;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
        ticks = 0;
        sync();
    }

    public int getTicks() {
        return ticks;
    }

    public void readFromNbt(NbtCompound tag) {
        if (tag.contains(POS_KEY)) {
            this.pos = tag.contains(POS_KEY) ? NbtHelper.toBlockPos(tag.getCompound(POS_KEY)) : null;
        } else {
            this.pos = null;
        }
        ticks = tag.getInt(TICKS_KEY);
    }

    public void writeToNbt(NbtCompound tag) {
        if (this.pos != null) {
            tag.put(POS_KEY, NbtHelper.fromBlockPos(pos));
        }
        tag.putInt(TICKS_KEY, ticks);
    }

    @Override
    public void serverTick() {

    }
}
