package com.company.drones.domain.usecase.exception;

public class OperationPreconditionsFailedException extends RuntimeException {

    public OperationPreconditionsFailedException(String message) {
        super(message);
    }
}
