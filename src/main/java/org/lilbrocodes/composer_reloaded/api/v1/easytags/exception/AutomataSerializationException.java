package org.lilbrocodes.composer_reloaded.api.v1.easytags.exception;

public class AutomataSerializationException extends RuntimeException {
    public AutomataSerializationException(String message) {
        super(message);
    }

    public AutomataSerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
