package com.example.securityservice.exception;

public class BadCredentialsException extends Exception{
    public BadCredentialsException(String message) {
       super(message);
    }
}
