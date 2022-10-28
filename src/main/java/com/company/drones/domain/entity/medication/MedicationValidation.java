package com.company.drones.domain.entity.medication;

import com.company.drones.domain.entity.IValidation;
import com.company.drones.domain.entity.exception.InvalidClassAttributeException;

import java.util.Objects;

import static com.company.drones.domain.entity.drone_bucket.DroneBucketItemValidation.validateWeight;

public class MedicationValidation implements IValidation {
    public static String validateName(final String name) throws InvalidClassAttributeException {
        if (name == null || name.trim().length() == 0) {
            throw new InvalidClassAttributeException("Invalid medication name. Medication name cannot be null or empty.");
        }

        if (!name.matches("^[a-zA-Z0-9_-]+$")) {
            throw new InvalidClassAttributeException("Invalid medication name. " +
                    "Allowed only letters, numbers, dash (-) and underscore (_).");
        }

        return name;
    }

    public static String validateCode(final String code) throws InvalidClassAttributeException {
        if (code == null || code.trim().length() == 0) {
            throw new InvalidClassAttributeException("Invalid medication code. Medication code cannot be null or empty.");
        }

        if (!code.matches("^[A-Z0-9_]+$")) {
            throw new InvalidClassAttributeException("Invalid medication code. " +
                    "Allowed only uppercase letters, numbers and underscore (_).");
        }

        return code;
    }

    @Override
    public <T> void validate(final T object) throws InvalidClassAttributeException {
        final Medication medication = (Medication) Objects
                .requireNonNull(object, "Expected not null Medication, provided null.");

        validateName(medication.getName());
        validateCode(medication.getCode());
        validateWeight(medication.getWeight());
    }
}
