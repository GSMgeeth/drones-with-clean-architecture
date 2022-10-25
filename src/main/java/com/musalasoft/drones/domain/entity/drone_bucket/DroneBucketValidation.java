package com.musalasoft.drones.domain.entity.drone_bucket;

import com.musalasoft.drones.domain.entity.IValidation;
import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.exception.InvalidClassAttributeException;
import com.musalasoft.drones.domain.entity.exception.InvalidIdentityException;
import com.musalasoft.drones.domain.entity.identity.IdentityValidation;

import java.util.Objects;

public class DroneBucketValidation implements IValidation {
    public static Drone validateDrone(final Drone drone) throws InvalidClassAttributeException, InvalidIdentityException, NullPointerException {
        if (drone == null) {
            throw new InvalidClassAttributeException("Invalid DroneBucket drone. Drone cannot be null for DroneBucket.");
        }

        new IdentityValidation().validate(drone.getIdentity());

        return drone;
    }

    @Override
    public <T> void validate(final T object) throws InvalidClassAttributeException, InvalidIdentityException, NullPointerException {
        final DroneBucket droneBucket = (DroneBucket) Objects.requireNonNull(object, "Expected not null DroneBucket, provided null.");

        validateDrone(droneBucket.getDrone());

        final DroneBucketItemValidation droneBucketItemValidation = new DroneBucketItemValidation();
        droneBucket.getDroneBucketItems().forEach(droneBucketItemValidation::validate);
    }
}
