package com.musalasoft.drones.domain.usecase.exception;

public class DroneConnectivityException extends RuntimeException {

    public DroneConnectivityException(String message) {
        super(message);
    }
}
