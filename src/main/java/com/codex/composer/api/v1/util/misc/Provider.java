package com.codex.composer.api.v1.util.misc;

@FunctionalInterface
public interface Provider<P> {
    P provide();
}
