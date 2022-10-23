package com.musalasoft.drones.domain.entity.medication;

import com.musalasoft.drones.domain.entity.Validation;
import com.musalasoft.drones.domain.entity.exception.InvalidClassAttributeException;

import java.util.Objects;

public class MedicationValidation implements Validation {
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

    public static double validateWeight(final double weight) throws InvalidClassAttributeException {
        if (weight < 0.0) {
            throw new InvalidClassAttributeException("Invalid medication weight. Medication weight cannot be a minus value.");
        }

        return weight;
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
