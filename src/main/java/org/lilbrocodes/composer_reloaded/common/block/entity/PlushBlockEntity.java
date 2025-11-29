package org.lilbrocodes.composer_reloaded.common.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lilbrocodes.composer_reloaded.common.registry.ComposerBlockEntities;

public class PlushBlockEntity extends BlockEntity {
    private static final float SQUASH = 3f;
    private static final float SQUASH_EPS = 0.01f;
    public double squash;

    public PlushBlockEntity(BlockPos pos, BlockState state) {
        super(ComposerBlockEntities.PLUSH, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, @NotNull PlushBlockEntity spark) {
        if (spark.squash > 0) {
            spark.squash /= SQUASH;
            if (spark.squash < SQUASH_EPS) {
                spark.squash = 0;
                if (world != null) world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
            }
        }
    }

    public void squish(int squash) {
        this.squash += squash;
        if (this.world != null)
            this.world.updateListeners(this.pos, this.getCachedState(), this.getCachedState(), Block.NOTIFY_LISTENERS);
        this.markDirty();
    }

    @Override
    protected void writeNbt(@NotNull NbtCompound nbt) {
        nbt.putDouble("squash", this.squash);
    }

    @Override
    public void readNbt(@NotNull NbtCompound nbt) {
        this.squash = nbt.getDouble("squash");
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }
}