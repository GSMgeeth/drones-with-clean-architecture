package com.musalasoft.drones.domain.entity.drone;

import com.musalasoft.drones.domain.entity.IValidation;
import com.musalasoft.drones.domain.entity.exception.InvalidClassAttributeException;

import java.util.Objects;

public class DroneValidation implements IValidation {
    public static String validateSerialNumber(final String serialNumber) throws InvalidClassAttributeException {
        if (serialNumber == null || serialNumber.trim().length() == 0 || serialNumber.length() > 100) {
            throw new InvalidClassAttributeException("" +
                    "Invalid drone serial number. Drone serial number cannot be null or empty. Max length allowed is 100.");
        }

        return serialNumber;
    }

    @Override
    public <T> void validate(final T object) throws InvalidClassAttributeException, NullPointerException {
        final Drone drone = (Drone) Objects.requireNonNull(object, "Expected not null Drone, provided null.");

        validateSerialNumber(drone.getSerialNumber());
    }
}
