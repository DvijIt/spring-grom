package com.lesson3.hw.exception;

public class BadRequestException extends Exception{

    public BadRequestException(String message) {
        super(message);
    }
}
