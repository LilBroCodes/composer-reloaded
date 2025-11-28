package org.lilbrocodes.composer_reloaded.api.util.builder;

public class NotAllFieldsFilledException extends RuntimeException {
    public NotAllFieldsFilledException() {
        this("All fields of builder must be filled and must not be null!");
    }

    public NotAllFieldsFilledException(String string) {
        super(string);
    }
}
