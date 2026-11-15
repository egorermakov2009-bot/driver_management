package com.driver.management.exception;

public class DriverAlreadyExistsException extends RuntimeException{
    public DriverAlreadyExistsException(String message) {
        super(message);
    }
}
