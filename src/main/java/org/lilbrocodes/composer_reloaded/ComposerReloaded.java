package org.lilbrocodes.composer_reloaded;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.registry.ComposerRegistries;
import org.lilbrocodes.composer_reloaded.api.util.AdvancementManager;
import org.lilbrocodes.composer_reloaded.common.networking.TargetBlockPayload;
import org.lilbrocodes.composer_reloaded.common.networking.TargetEntityPayload;
import org.lilbrocodes.composer_reloaded.common.registry.*;


public class ComposerReloaded implements ModInitializer {
    public static final String MOD_ID = "composer_reloaded";
    public static final Identifier TARGET_ENTITY = identify("target_entity_c2s");
    public static final Identifier TARGET_BLOCK = identify("target_block_c2s");

    @Override
    public void onInitialize() {
        ComposerBlockEntities.initialize();
        ComposerStatistics.initialize();
        ComposerItemGroups.initialize();
        ComposerBlocks.initialize();
        ComposerSounds.initialize();

        ComposerRegistries.initialize();
        TargetEntityPayload.registerHandler();
        TargetBlockPayload.registerHandler();

        ServerTickEvents.END_WORLD_TICK.register(AdvancementManager::tick);
    }

    public static Identifier identify(String name) {
        return new Identifier(MOD_ID, name);
    }
}
