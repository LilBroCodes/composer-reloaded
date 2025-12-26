package org.lilbrocodes.composer_reloaded.api.v1.util;

public class CharCount {
    public static int count(String s, CharSequence c) {
        return s.length() - s.replace(c, "").length();
    }

    public static int count(String s, char c) {
        return count(s, String.valueOf(c));
    }
}
