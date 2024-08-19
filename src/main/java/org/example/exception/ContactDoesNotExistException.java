package org.example.exception;

public class ContactDoesNotExistException extends RuntimeException {
    public ContactDoesNotExistException(String message) {
        super(message);

    }
}
