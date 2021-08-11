package org.example.exception;

public class NonexistentIdException extends Exception {
    @Override
    public String toString() {
        return "Nonexistent id";
    }
}
