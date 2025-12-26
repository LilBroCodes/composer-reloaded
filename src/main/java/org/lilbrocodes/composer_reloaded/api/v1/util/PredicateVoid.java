package org.lilbrocodes.composer_reloaded.api.v1.util;

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

    public static boolean always(Object obj) {
        return true;
    }

    public static boolean never(Object obj) {
        return false;
    }
}
