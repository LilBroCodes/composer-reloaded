package org.lilbrocodes.composer_reloaded;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.CustomValue;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.registry.ComposerRegistries;
import org.lilbrocodes.composer_reloaded.api.util.AdvancementManager;
import org.lilbrocodes.composer_reloaded.client.config.ComposerConfig;
import org.lilbrocodes.composer_reloaded.common.networking.ScrollActionPayload;
import org.lilbrocodes.composer_reloaded.common.networking.TargetBlockPayload;
import org.lilbrocodes.composer_reloaded.common.networking.TargetEntityPayload;
import org.lilbrocodes.composer_reloaded.common.registry.*;


public class ComposerReloaded implements ModInitializer {
    public static final String MOD_ID = "composer_reloaded";
    public static final Identifier TARGET_ENTITY = identify("target_entity_c2s");
    public static final Identifier TARGET_BLOCK = identify("target_block_c2s");
    public static final Identifier SCROLL_ACTION = identify("scroll_action_c2s");
    private static boolean dupedKeybindsEnabled = false;

    @Override
    public void onInitialize() {
        for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
            ModMetadata meta = mod.getMetadata();

            if (meta.getId().equals("enchancement")) {
                dupedKeybindsEnabled = false;
                break;
            }

            CustomValue section = meta.getCustomValue("composer-duped-keybinds");

            if (section == null || section.getType() != CustomValue.CvType.BOOLEAN) continue;
            if (section.getAsBoolean()) {
                dupedKeybindsEnabled = true;
            }
        }

        ComposerBlockEntities.initialize();
        ComposerStatistics.initialize();
        ComposerItemGroups.initialize();
        ComposerItems.initialize();
        ComposerBlocks.initialize();
        ComposerSounds.initialize();

        ComposerConfig.initialize();
        ComposerRegistries.initialize();
        TargetEntityPayload.registerHandler();
        TargetBlockPayload.registerHandler();
        ScrollActionPayload.registerHandler();

        ServerTickEvents.END_WORLD_TICK.register(AdvancementManager::tick);
    }

    public static boolean dupedBinds() {
        return dupedKeybindsEnabled;
    }

    public static Identifier identify(String name) {
        return new Identifier(MOD_ID, name);
    }
}
