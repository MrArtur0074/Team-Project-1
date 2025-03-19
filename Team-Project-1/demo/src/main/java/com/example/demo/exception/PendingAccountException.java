package com.example.demo.exception;

public class PendingAccountException extends RuntimeException {
    public PendingAccountException(String message) {
        super(message);
    }
}