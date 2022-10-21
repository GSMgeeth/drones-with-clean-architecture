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

    public static double validateWeightLimit(final double weightLimit) throws InvalidClassAttributeException {
        if (weightLimit < 0.0) {
            throw new InvalidClassAttributeException("Invalid drone weight limit.");
        }

        return weightLimit;
    }

    public static double validateBatteryCapacity(final double batteryCapacity) throws InvalidClassAttributeException {
        if (batteryCapacity < 0.0 || batteryCapacity > 100.0) {
            throw new InvalidClassAttributeException("Invalid drone battery capacity.");
        }

        return batteryCapacity;
    }

    public static double validateLoadedWeight(final double loadedWeight) throws InvalidClassAttributeException {
        if (loadedWeight < 0.0) {
            throw new InvalidClassAttributeException("Invalid drone loaded weight.");
        }

        return loadedWeight;
    }

    @Override
    public <T> void validate(T object) throws InvalidClassAttributeException {
        final Drone drone = (Drone) Objects.requireNonNull(object, "Expected not null Drone, provided null.");

        validateSerialNumber(drone.getSerialNumber());
        validateBatteryCapacity(drone.getBatteryCapacity());
        validateLoadedWeight(drone.getLoadedWeight());
    }
}
