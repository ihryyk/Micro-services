package com.app.microcollector;

public class MicroRecipientException extends RuntimeException {
    public MicroRecipientException(String message, Throwable throwable) {
        super("RecipientException: "+ message, throwable);
    }
}
