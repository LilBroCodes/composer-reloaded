package org.lilbrocodes.composer_reloaded;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.CustomValue;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lilbrocodes.composer_reloaded.api.easytags.impl.DefaultSerializers;
import org.lilbrocodes.composer_reloaded.api.events.composite.ComposerCompositeEvents;
import org.lilbrocodes.composer_reloaded.api.events.composite.CompositeEventRegistry;
import org.lilbrocodes.composer_reloaded.api.feature.ComposerFeatures;
import org.lilbrocodes.composer_reloaded.api.runtime.ServerHolder;
import org.lilbrocodes.composer_reloaded.api.util.AdvancementManager;
import org.lilbrocodes.composer_reloaded.api.util.misc.AbstractPseudoRegistry;
import org.lilbrocodes.composer_reloaded.api.util.misc.EventStacker;
import org.lilbrocodes.composer_reloaded.client.config.ComposerConfig;
import org.lilbrocodes.composer_reloaded.common.data.FeatureStateLoader;
import org.lilbrocodes.composer_reloaded.common.data.SimpleItemFixerLoader;
import org.lilbrocodes.composer_reloaded.common.except.InvalidMetadataException;
import org.lilbrocodes.composer_reloaded.common.feature.FeatureCommand;
import org.lilbrocodes.composer_reloaded.common.networking.ScrollActionPayload;
import org.lilbrocodes.composer_reloaded.common.networking.TargetBlockPayload;
import org.lilbrocodes.composer_reloaded.common.networking.TargetEntityPayload;
import org.lilbrocodes.composer_reloaded.common.registry.*;


public class ComposerReloaded implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger(ComposerReloaded.class);
    public static final String MOD_ID = "composer_reloaded";
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

        for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
            ModMetadata meta = mod.getMetadata();
            String modId = meta.getId();
            CustomValue section = meta.getCustomValue("composer-features");

            if (section != null && section.getType() == CustomValue.CvType.ARRAY)
                throw new InvalidMetadataException("Mod " + modId + " is using the deprecated feature registration system. Update the mod, contact the developer or downgrade composer if possible.");
        }

        ComposerCompositeEvents.initialize();
        ComposerBlockEntities.initialize();
        ComposerStatistics.initialize();
        ComposerItemGroups.initialize();
        ComposerItems.initialize();
        ComposerBlocks.initialize();
        ComposerSounds.initialize();

        ComposerConfig.initialize();
        ComposerRegistries.initialize();
        DefaultSerializers.initialize();
        TargetEntityPayload.registerHandler();
        TargetBlockPayload.registerHandler();
        ScrollActionPayload.registerHandler();

        CommandRegistrationCallback.EVENT.register(new FeatureCommand());

        AbstractPseudoRegistry.identify(identify("composite_events"), CompositeEventRegistry.getInstance());

        ServerTickEvents.END_WORLD_TICK.register(AdvancementManager::tick);
        EventStacker.registerAll(
                ServerLifecycleEvents.SERVER_STARTED,
                ServerHolder::accept,
                AbstractPseudoRegistry::runAfterInit,
                ComposerFeatures.getInstance()::afterInitialization
        );

        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleItemFixerLoader());
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new FeatureStateLoader());
    }

    public static boolean dupedBinds() {
        return dupedKeybindsEnabled;
    }

    public static Identifier identify(String name) {
        return new Identifier(MOD_ID, name);
    }
}
