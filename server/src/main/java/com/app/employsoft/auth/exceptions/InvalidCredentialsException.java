package com.app.employsoft.auth.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super( message );
    }
}
