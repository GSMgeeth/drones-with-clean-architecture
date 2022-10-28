package com.company.drones.domain.usecase.drone_bucket;

import com.company.drones.domain.entity.drone.Drone;
import com.company.drones.domain.entity.drone_bucket.DroneBucket;
import com.company.drones.domain.entity.exception.InvalidClassAttributeException;
import com.company.drones.domain.entity.exception.InvalidIdentityException;
import com.company.drones.domain.repository.drone_bucket.IDroneBucketRepository;
import com.company.drones.domain.usecase.IUseCase;

import java.util.Objects;

public class GetDroneBucketByDroneUseCase implements IUseCase<DroneBucket, Drone> {
    private final IDroneBucketRepository droneBucketRepository;

    public GetDroneBucketByDroneUseCase(IDroneBucketRepository droneBucketRepository) {
        this.droneBucketRepository = droneBucketRepository;
    }

    /**
     * Gets a drone bucket with the list of loaded items by drone id.
     *
     * @param drone Drone with the drone id to fetch the drone bucket list by.
     * @return Drone bucket filled with loaded item list. Null if no drone bucket found by the passed drone.
     */
    @Override
    public DroneBucket execute(final Drone drone) throws InvalidClassAttributeException, InvalidIdentityException, NullPointerException {
        return droneBucketRepository.getDroneBucketByDrone(Objects.requireNonNull(drone));
    }
}
