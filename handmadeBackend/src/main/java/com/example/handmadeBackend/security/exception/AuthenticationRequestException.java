package com.example.handmadeBackend.security.exception;

public class AuthenticationRequestException extends Exception{

    public AuthenticationRequestException() {
    }

    public AuthenticationRequestException(String message) {
        super(message);
    }
}
