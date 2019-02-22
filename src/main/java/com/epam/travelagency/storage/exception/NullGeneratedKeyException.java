package com.epam.travelagency.storage.exception;

public class NullGeneratedKeyException extends RuntimeException {
    public NullGeneratedKeyException(Exception e) {
        super("Key was not generated.", e);
    }
}
