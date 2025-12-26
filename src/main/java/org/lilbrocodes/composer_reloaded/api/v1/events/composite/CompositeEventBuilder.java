package org.lilbrocodes.composer_reloaded.api.v1.events.composite;

public abstract class CompositeEventBuilder<T extends CompositeEvent> {
    protected CompositeEventBuilder() {

    }

    public abstract T build();
}
