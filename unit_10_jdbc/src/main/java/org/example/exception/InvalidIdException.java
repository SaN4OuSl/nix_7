package org.example.exception;

public class InvalidIdException extends Exception {
    @Override
    public String toString() {
        return "Invalid Id";
    }
}
