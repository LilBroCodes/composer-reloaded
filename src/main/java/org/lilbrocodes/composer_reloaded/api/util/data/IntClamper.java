package org.lilbrocodes.composer_reloaded.api.util.data;

import net.minecraft.util.math.MathHelper;

import java.util.Random;

public record IntClamper<V>(Integer min, Integer max) {
    public ValidatedInt<V> get(Integer value) {
        return new ValidatedInt<>(MathHelper.clamp(value, min, max));
    }

    public ValidatedInt<V> get(Integer value, Integer bound) {
        return new RandomizedInt<>(value, bound);
    }

    public ValidatedInt<V> get(Integer value, Integer origin, Integer bound) {
        return new RandomizedInt<>(value, origin, bound);
    }

    public static class ValidatedInt<V> {
        protected final int n;

        public ValidatedInt(Integer n) {
            this.n = n;
        }

        public Integer build() {
            return n;
        }
    }

    public static class RandomizedInt<V> extends ValidatedInt<V> {
        private final Random random = new Random(0);
        private final int origin;
        private final int bound;

        public RandomizedInt(Integer n, Integer origin, Integer bound) {
            super(n);
            this.origin = origin;
            this.bound = bound;
        }

        public RandomizedInt(Integer n, Integer bound) {
            this(n, -bound, bound);
        }

        @Override
        public Integer build() {
            return n + random.nextInt(origin, bound);
        }
    }
}
