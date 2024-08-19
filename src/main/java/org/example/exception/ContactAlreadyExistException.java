package org.example.exception;

public class ContactAlreadyExistException extends RuntimeException {
    public ContactAlreadyExistException(String message) {
        super(message);
    }
}
