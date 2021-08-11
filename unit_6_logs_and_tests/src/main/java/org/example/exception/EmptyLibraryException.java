package org.example.exception;

public class EmptyLibraryException extends Exception {
    @Override
    public String toString() {
        return "Library is empty";
    }
}
