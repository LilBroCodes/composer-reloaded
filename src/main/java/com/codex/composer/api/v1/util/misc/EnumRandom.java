package com.codex.composer.api.v1.util.misc;

import java.util.concurrent.ThreadLocalRandom;

public class EnumRandom {
    public static <T extends Enum<T>> T pick(Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(enumClass + " is not an enum or has no constants");
        }
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }
}
