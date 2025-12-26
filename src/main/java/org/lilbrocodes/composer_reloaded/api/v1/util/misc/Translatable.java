package org.lilbrocodes.composer_reloaded.api.v1.util.misc;

public interface Translatable {
    String getTranslationKey();

    default String getTranslationKey(String prefix) {
        return getTranslationKey(prefix, "");
    }

    default String getTranslationKey(String prefix, String suffix) {
        return "%s%s%s%s%s".formatted(prefix, prefix.isBlank() ? "" : ".", getTranslationKey(), suffix.isBlank() ? "" : ".", suffix);
    }
}
