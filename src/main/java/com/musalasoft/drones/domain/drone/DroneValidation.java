package com.musalasoft.drones.domain.drone;

import com.musalasoft.drones.domain.Validation;
import com.musalasoft.drones.domain.exception.InvalidClassAttributeException;

import java.util.Objects;

public class DroneValidation implements Validation {

    public static String validateSerialNumber(final String serialNumber) throws InvalidClassAttributeException {
        if (serialNumber == null || serialNumber.length() > 100) {
            throw new InvalidClassAttributeException("Invalid drone serial number.");
        }

        return serialNumber;
    }

    @Override
    public <T> void validate(T object) throws InvalidClassAttributeException, NullPointerException {
        final Drone drone = (Drone) Objects.requireNonNull(object, "Expected not null Drone, provided null.");

        validateSerialNumber(drone.getSerialNumber());
    }
}
