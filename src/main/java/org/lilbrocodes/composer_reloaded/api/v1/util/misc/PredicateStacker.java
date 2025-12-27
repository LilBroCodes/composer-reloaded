package org.lilbrocodes.composer_reloaded.api.v1.util.misc;

import java.util.function.Predicate;


public class PredicateStacker {
    @SafeVarargs
    public static <T> Predicate<? super T> stack(Predicate<? super T>... predicates) {
        return v -> {
            for (Predicate<? super T> p : predicates) {
                if (!p.test(v)) return false;
            }
            return true;
        };
    }
}
