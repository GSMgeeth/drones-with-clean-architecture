package com.musalasoft.drones.domain.entity.drone_bucket;

import com.musalasoft.drones.domain.entity.IValidation;
import com.musalasoft.drones.domain.entity.exception.InvalidClassAttributeException;

import java.util.Objects;

public class DroneBucketItemValidation implements IValidation {
    public static double validateWeight(final double weight) throws InvalidClassAttributeException {
        if (weight < 0.0) {
            throw new InvalidClassAttributeException("Invalid drone bucket item weight. Weight cannot be a minus value.");
        }

        return weight;
    }

    @Override
    public <T> void validate(T object) throws InvalidClassAttributeException {
        final DroneBucketItem droneBucketItem = (DroneBucketItem) Objects.requireNonNull(object, "Expected not null DroneBucketItem, provided null.");

        validateWeight(droneBucketItem.getWeight());
    }
}
