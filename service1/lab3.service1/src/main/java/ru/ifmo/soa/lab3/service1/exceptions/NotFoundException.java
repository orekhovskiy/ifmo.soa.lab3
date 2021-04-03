package ru.ifmo.soa.lab3.service1.exceptions;


public class NotFoundException extends Exception {
    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
