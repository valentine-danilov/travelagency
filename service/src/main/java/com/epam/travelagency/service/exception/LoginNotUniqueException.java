package com.epam.travelagency.service.exception;

public class LoginNotUniqueException extends Exception {
    public LoginNotUniqueException(String login) {
        super(String.format("Login '%s' already exists", login));
    }
}
