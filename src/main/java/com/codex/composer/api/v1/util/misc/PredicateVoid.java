package com.codex.composer.api.v1.util.misc;

/**
 * Utility class providing no-op or null-returning methods for lambdas or callbacks.
 */
public class PredicateVoid {

    /**
     * No-op method; can be used as a placeholder for lambdas that do nothing.
     */
    @SuppressWarnings("EmptyMethod")
    public static void nil(Object... v) {
    }

    /**
     * Returns null; can be used as a placeholder for lambdas that must return an object.
     */
    @SuppressWarnings("SameReturnValue")
    public static Object obj(Object... v) {
        return null;
    }

    public static boolean always(Object... v) {
        return true;
    }

    public static boolean never(Object... v) {
        return false;
    }

    public static <T> ConstantProvider<T> constant(T value) {
        return new ConstantProvider<>(value);
    }

    public static class ConstantProvider<T> {
        private final T value;

        public ConstantProvider(T value) {
            this.value = value;
        }

        public T get(Object... ignored) {
            return value;
        }
    }
}
