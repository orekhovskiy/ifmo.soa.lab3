package ru.ifmo.soa.lab3.service1.exceptions;

public class WrongArgumentException extends Exception {
    public WrongArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
