package org.lilbrocodes.composer_reloaded.internal.registry;

import org.lilbrocodes.composer_reloaded.api.feature.FeatureBuilder;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.DeferredFeatureRegistry;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.feature.Feature;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

public class ModFeatures {
    private static final DeferredFeatureRegistry FEATURES = new DeferredFeatureRegistry(ComposerReloaded.MOD_ID);

    public static class TargetSynchronization {
        static final String FREQ_KEY = "update_frequency";

        public static final Feature ENTITY = FEATURES.hang("entity", TargetSynchronization::config);
        public static final Feature BLOCK = FEATURES.hang("block", TargetSynchronization::config);

        private static void config(FeatureBuilder builder) {
            builder.defaultEnabled(true).configInt(FREQ_KEY, 1);
        }

        public static boolean entity() {
            return ENTITY.getHandle().enabled();
        }

        public static int eFreq() {
            return ENTITY.getHandle().getInt(FREQ_KEY);
        }

        public static boolean block() {
            return BLOCK.getHandle().enabled();
        }

        public static int bFreq() {
            return BLOCK.getHandle().getInt(FREQ_KEY);
        }
    }

    public static void initialize() {
        FEATURES.group(
                "target_synchronization",
                TargetSynchronization.BLOCK,
                TargetSynchronization.ENTITY
        );
    }
}
