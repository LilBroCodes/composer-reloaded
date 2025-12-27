package org.lilbrocodes.composer_reloaded.api.v1.util.misc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.*;

public class ComposedStream<T> {
    private final Stream<T> inner;

    private ComposedStream(Stream<T> inner) {
        this.inner = inner;
    }

    public static <T> ComposedStream<T> of(Stream<T> stream) {
        return new ComposedStream<>(stream);
    }

    // -------- Inst --------

    public void forEachIndexed(BiConsumer<T, Integer> biConsumer) {
        forEachIndexed(inner, biConsumer);
    }

    @SafeVarargs
    public final ComposedStream<T> filter(Predicate<? super T>... predicates) {
        return ComposedStream.of(filter(inner, predicates));
    }

    public Stream<T> exit() {
        return inner;
    }

    // -------- Impl --------

    public static <T> void forEachIndexed(Stream<T> stream, BiConsumer<T, Integer> biConsumer) {
        AtomicInteger i = new AtomicInteger();
        stream.forEach(t -> {
            biConsumer.accept(t, i.get());
            i.getAndIncrement();
        });
    }

    @SafeVarargs
    public static <T> Stream<T> filter(Stream<T> stream, Predicate<? super T>... predicates) {
        return stream.filter(PredicateStacker.stack(predicates));
    }
}
