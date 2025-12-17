package org.lilbrocodes.composer_reloaded.internal.data.assets;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import org.lilbrocodes.composer_reloaded.api.datagen.ComposerLanguageProvider;
import org.lilbrocodes.composer_reloaded.internal.client.config.ComposerConfig;
import org.lilbrocodes.composer_reloaded.internal.registry.ModBlocks;
import org.lilbrocodes.composer_reloaded.internal.registry.ModItemGroups;
import org.lilbrocodes.composer_reloaded.internal.registry.ModSounds;
import org.lilbrocodes.composer_reloaded.internal.registry.ModStatistics;

public class ModLanguageProvider extends ComposerLanguageProvider {
    public ModLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        block(ModBlocks.PLUSH, "LilBro Plush");
        stat(ModStatistics.PLUSH_BOOP, "LilBro Plushies Booped");
        group(ModItemGroups.COMPOSER, "Composer's Silly Little Additions");

        enumTranslatable(
                ComposerConfig.BindsMode.class,
                "None",
                "Vanilla & Mods that use Composer",
                "All"
        );

        sound(ModSounds.LILBRO_SQUISH, "Plush Booped");

        prefix("feature.enable", "Enabled feature %s");
        prefix("feature.disable", "Disabled feature %s");
        prefix("feature.missing", "Unknown feature %s");
        prefix("feature.description.missing", "No description was provided.");
        prefix("feature.prefix", "Composer Features");
        prefix("toast.cleared_all", "Successfully cleared all toasts.");
        prefix("toast.cleared_for_player", "Cleared toasts for %s.");
        prefix("toast.player_not_found", "Player not found.");
        prefix("toast.no_players_found", "No players found.");
        prefix("toast.invalid_icon_texture", "Invalid identifier for icon texture.");
        prefix("toast.sent_simple", "Sent toast: %s\n | Icon texture: %s\n | Background color: %s\n | Border color: %s");
        prefix("toast.sent_notify", "Sent notify toast: %s\n | Background color: %s\n | Border color: %s");
        prefix("toast.prefix", "Composer Toasts");
    }

    @Override
    public String prefix() {
        return "composer_reloaded";
    }
}
