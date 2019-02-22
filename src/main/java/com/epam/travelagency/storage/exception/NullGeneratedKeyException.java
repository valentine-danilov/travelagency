package com.epam.travelagency.storage.exception;

public class NullGeneratedKeyException extends Exception {
    public NullGeneratedKeyException() {
        super("Key was not generated!");
    }
}
