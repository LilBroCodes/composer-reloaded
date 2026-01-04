package com.codex.composer.internal.cca.entity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import com.codex.composer.internal.cca.ModCardinalComponents;

import static com.codex.composer.internal.registry.ModFeatures.TargetSynchronization.*;

//? if minecraft: <=1.20.4 {
/*import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
 *///? } else {
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.registry.RegistryWrapper;
//?}

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

    public void readFromNbt(NbtCompound tag /*? if minecraft: >= 1.20.6 { */, RegistryWrapper.WrapperLookup registries /*?}*/) {
        this.pos = /*? if minecraft: >= 1.20.6 { */NbtHelper.toBlockPos(tag, POS_KEY).orElse(null)/*? } else {*//*tag.contains(POS_KEY) ? NbtHelper.toBlockPos(tag.getCompound(POS_KEY)) : null*//*?}*/;
        ticks = tag.getInt(TICKS_KEY);
    }

    public void writeToNbt(NbtCompound tag /*? if minecraft: >= 1.20.6 { */, RegistryWrapper.WrapperLookup registries /*?}*/) {
        if (this.pos != null) {
            tag.put(POS_KEY, NbtHelper.fromBlockPos(pos));
        }
        tag.putInt(TICKS_KEY, ticks);
    }

    @Override
    public void serverTick() {
        ticks++;
        if (block() && player.age % bFreq() == 0) sync();
    }
}
