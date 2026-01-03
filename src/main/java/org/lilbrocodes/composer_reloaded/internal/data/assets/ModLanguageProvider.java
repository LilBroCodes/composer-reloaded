package org.lilbrocodes.composer_reloaded.internal.data.assets;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import org.lilbrocodes.composer_reloaded.api.v1.datagen.ComposerLanguageProvider;
import org.lilbrocodes.composer_reloaded.internal.client.config.ComposerConfig;
import org.lilbrocodes.composer_reloaded.internal.registry.*;

//? minecraft: >=1.20.6 {
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;
//? }

import static org.lilbrocodes.composer_reloaded.internal.registry.ModFeatures.TargetSynchronization.*;

public class ModLanguageProvider extends ComposerLanguageProvider {
    //? if minecraft: <=1.20.4 {
    /*public ModLanguageProvider(FabricDataOutput output) {
        super(output);
    }
    *///? } else {
    public ModLanguageProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }
    //? }

    @Override
    public void generate() {
        block(ModBlocks.PLUSH, "LilBro Plush");
        //? if minecraft: >=1.21.3
        item(ModBlocks.PLUSH, "LilBro Plush");
        stat(ModStatistics.PLUSH_BOOP, "LilBro Plushies Booped");
        group(ModItemGroups.COMPOSER, "Composer's Silly Little Additions");

        enumTranslatable(
                ComposerConfig.BindsMode.class,
                "None",
                "Vanilla & Mods that use Composer",
                "All"
        );

        sound(ModSounds.LILBRO_SQUISH, "Plush Booped");

        feature(ENTITY, "Synchronizes players' target entities to the client. Frequency controls how often (in ticks) updates are sent. Changing this or disabling it may break other mods.");
        feature(BLOCK, "Synchronizes players' target blocks to the client. Frequency controls how often (in ticks) updates are sent. Changing this or disabling it may break other mods.");

        prefix("command.exception.player_not_found", "Player not found.");
        prefix("command.exception.no_players_found", "No players found.");

        prefix("feature.enable", "Enabled feature %s");
        prefix("feature.disable", "Disabled feature %s");
        prefix("feature.missing", "Unknown feature %s");
        prefix("feature.description.missing", "No description was provided.");
        prefix("feature.prefix", "Composer Features");

        prefix("toast.cleared_for_player", "Cleared toasts for %s.");
        prefix("toast.cleared_all", "Successfully cleared all toasts.");
        prefix("toast.invalid_icon_texture", "Invalid identifier for icon texture.");
        prefix("toast.sent_simple", "Sent toast: %s\n | Icon texture: %s\n | Background color: %s\n | Border color: %s");
        prefix("toast.sent_notify", "Sent notify toast: %s\n | Background color: %s\n | Border color: %s");
        prefix("toast.prefix", "Composer Toasts");

        prefix("overlay.prefix", "Composer Overlays");
        prefix("overlay.cleared_all", "Successfully cleared all visible and queued overlays.");
        prefix("overlay.cleared_all_for", "Successfully cleared all visible and queued overlays for %s.");
        prefix("overlay.cleared_all_visible", "Successfully cleared all visible overlays.");
        prefix("overlay.cleared_all_visible_for", "Successfully cleared all visible overlays for %s.");
        prefix("overlay.cleared_all_queued", "Successfully cleared all queued overlays.");
        prefix("overlay.cleared_all_queued_for", "Successfully cleared all queued overlays for %s.");
        prefix("overlay.invalid_texture", "Invalid texture identifier.");
        prefix("overlay.sent_texture_scale_duration", "Sent textured overlay (%s) with scale %.2f for %d ticks.");
        prefix("overlay.sent_texture_scale_fade", "Sent textured overlay (%s) with scale %.2f (fade %d/%d/%d).");
        prefix("overlay.sent_texture_duration", "Sent textured overlay (%s) for %d ticks.");
        prefix("overlay.sent_texture_fade", "Sent textured overlay (%s) (fade %d/%d/%d).");
        prefix("overlay.sent_text_scale_duration", "Sent text overlay \"%s\" (%s) with scale %.2f for %d ticks.");
        prefix("overlay.sent_text_scale_fade", "Sent text overlay \"%s\" (%s) with scale %.2f (fade %d/%d/%d).");
        prefix("overlay.sent_text_duration", "Sent text overlay \"%s\" (%s) for %d ticks.");
        prefix("overlay.sent_text_fade", "Sent text overlay \"%s\" (%s) (fade %d/%d/%d).");

        prefix("registry.prefix", "Composer Utilities");
        prefix("dynamic_tooltips.hidden", "Press %s to show %s");

        prefix("tooltips.soulbound", "This item is soulbound");
        prefix("tooltips.soulbound.not", "This item is not soulbound");
        prefix("tooltips.soulbound.details", "binding details");
        prefix("tooltips.soulbound.droppable", "This item is droppable when soulbound");
        prefix("tooltips.soulbound.droppable.not", "This item is not droppable when soulbound");
    }

    @Override
    public String prefix() {
        return "composer_reloaded";
    }
}
