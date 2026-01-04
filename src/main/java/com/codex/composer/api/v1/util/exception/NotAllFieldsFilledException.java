package com.codex.composer.api.v1.util.exception;

public class NotAllFieldsFilledException extends RuntimeException {
    public NotAllFieldsFilledException() {
        this("All fields of builder must be filled and must not be null!");
    }

    public NotAllFieldsFilledException(String string) {
        super(string);
    }
}
