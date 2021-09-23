package org.example.exception;

public class NotFoundException extends Exception {
    private final String defaultMass = "Not found";
    private final String message;

    public NotFoundException(String message) {
        this.message = defaultMass + " " + message;
    }

    @Override
    public String toString() {
        return message;
    }
}
