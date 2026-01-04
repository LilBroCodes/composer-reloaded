package com.codex.composer.api.v1.util.misc;

import java.util.List;

public class LanguageUtils {
    public static String plural(String key, String plural, List<?> ls) {
        return String.format("%s%s", key, ls.size() == 1 ? "" : plural);
    }

    public static String plural(String key, List<?> ls) {
        return plural(key, "s", ls);
    }

    public static String negate(String key, String negate, boolean b) {
        return String.format("%s%s", key, b ? "" : negate);
    }

    public static String negate(String key, String negate, Provider<Boolean> b) {
        return String.format("%s%s", key, b.provide() ? "" : negate);
    }

    public static String negate(String key, boolean b) {
        return negate(key, ".not", b);
    }

    public static String negate(String key, Provider<Boolean> b) {
        return negate(key, ".not", b);
    }
}
