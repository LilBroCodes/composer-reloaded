package org.lilbrocodes.composer_reloaded.api.v1.util.misc;

public class ColorUtil {
    public static int argb(int r, int g, int b, int a) {
        return (a << 24) | (r << 16) | (g << 8) | b;
    }
    public static int rgba(int r, int g, int b, int a) {
        return (r << 24) | (g << 16) | (b << 8) | a;
    }
}