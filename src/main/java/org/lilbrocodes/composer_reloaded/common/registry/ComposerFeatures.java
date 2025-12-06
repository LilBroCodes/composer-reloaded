package org.lilbrocodes.composer_reloaded.common.registry;

import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.api.feature.FeatureHandle;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.DeferredFeatureRegistry;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.feature.Feature;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.feature.FeatureGroup;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.feature.FinalizedFeatureGroup;

public class ComposerFeatures {
    private static final DeferredFeatureRegistry FEATURES = new DeferredFeatureRegistry(ComposerReloaded.MOD_ID);

    private static final Feature TORCHES = FEATURES.hang("torches", f -> f.e().configDouble("melt_rate", 0.5));
    private static final Feature LAMPS = FEATURES.hang("lamps", f -> f.e().configDouble("melt_rate", 0.6));
    private static final Feature FIRES = FEATURES.hang("fires", f -> f.e().configDouble("melt_rate", 0.7));
    private static final Feature CAMPFIRES = FEATURES.hang("campfires", f -> f.e().configDouble("melt_rate", 0.8));

    private static final FeatureGroup SNOW_HANG = FEATURES.hangGroup(TORCHES, LAMPS, FIRES, CAMPFIRES);
    private static final FinalizedFeatureGroup SNOW_REGISTERED = FEATURES.grab(SNOW_HANG, "snow");
    private static final FeatureHandle SNOW_GROUP = FEATURES.bunch(SNOW_REGISTERED);

    private static final Feature AUTO_REPAIR = FEATURES.hang("auto_repair", false);
    private static final Feature FLAMING_SWORD = FEATURES.hang("flaming_sword", false);
    private static final Feature FROST_AXE = FEATURES.hang("frost_axe", true);

    private static final FeatureGroup SPECIAL_TOOLS_HANG = FEATURES.hangGroup(FLAMING_SWORD, FROST_AXE);
    private static final FinalizedFeatureGroup SPECIAL_TOOLS_REGISTERED = FEATURES.grab(SPECIAL_TOOLS_HANG, "special");
    private static final FeatureHandle SPECIAL_TOOLS_GROUP = FEATURES.bunch(SPECIAL_TOOLS_REGISTERED);

    private static final FeatureGroup TOOLS_HANG = FEATURES.hangGroup(AUTO_REPAIR, SPECIAL_TOOLS_HANG);
    private static final FinalizedFeatureGroup TOOLS_REGISTERED = FEATURES.grab(TOOLS_HANG, "tools");
    private static final FeatureHandle TOOLS_GROUP = FEATURES.bunch(TOOLS_REGISTERED);

    private static final Feature FAST_CRAFTING = FEATURES.hang("fast_crafting", true);
    private static final Feature EXTRA_DROPS = FEATURES.hang("extra_drops", false);
    private static final Feature CUSTOM_ORE_GENERATION = FEATURES.hang("custom_ore_generation", f -> f
            .defaultEnabled(true)
            .configDouble("spawn_rate", 0.1)
            .configInt("max_vein_size", 8));

    private static final FeatureGroup MISC_HANG = FEATURES.hangGroup(FAST_CRAFTING, EXTRA_DROPS, CUSTOM_ORE_GENERATION);
    private static final FinalizedFeatureGroup MISC_REGISTERED = FEATURES.grab(MISC_HANG, "");
    private static final FeatureHandle MISC_GROUP = FEATURES.bunch(MISC_REGISTERED);

    public static void initialize() {

    }
}
