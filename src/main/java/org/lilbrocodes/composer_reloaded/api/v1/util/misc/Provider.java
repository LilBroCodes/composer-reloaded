package org.lilbrocodes.composer_reloaded.api.v1.util.misc;

@FunctionalInterface
public interface Provider<P> {
    P provide();
}
