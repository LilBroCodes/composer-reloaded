package org.lilbrocodes.composer_reloaded.mixin.impl.access;

import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.level.storage.LevelStorage;
import org.lilbrocodes.composer_reloaded.mixin.accessor.MinecraftServerMethodAccessor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.nio.file.Path;
import java.util.Set;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin implements MinecraftServerMethodAccessor {
    @Shadow @Final protected LevelStorage.Session session;
    @Shadow public abstract Set<RegistryKey<World>> getWorldRegistryKeys();

    @Override
    public Path composerReloaded$getWorldDirectory() {
        return this.session.getWorldDirectory(getWorldRegistryKeys().stream().toList().get(0));
    }
}
