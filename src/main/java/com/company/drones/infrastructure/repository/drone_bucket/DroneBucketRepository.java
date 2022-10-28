package com.company.drones.infrastructure.repository.drone_bucket;

import com.company.drones.domain.entity.drone.Drone;
import com.company.drones.domain.entity.exception.InvalidIdentityException;
import com.company.drones.infrastructure.database.drone.DroneORM;
import com.company.drones.infrastructure.database.drone_bucket.DroneBucketItemORM;
import com.company.drones.infrastructure.database.drone_bucket.JPADroneBucketRepository;
import com.company.drones.domain.entity.drone_bucket.DroneBucket;
import com.company.drones.domain.entity.exception.InvalidClassAttributeException;
import com.company.drones.domain.repository.drone_bucket.IDroneBucketRepository;

import java.util.Objects;

public class DroneBucketRepository implements IDroneBucketRepository {
    private final JPADroneBucketRepository jpaDroneBucketRepository;

    public DroneBucketRepository(JPADroneBucketRepository jpaDroneBucketRepository) {
        this.jpaDroneBucketRepository = jpaDroneBucketRepository;
    }

    @Override
    public DroneBucket getDroneBucketByDrone(final Drone drone) throws InvalidClassAttributeException, InvalidIdentityException, NullPointerException {
        final DroneBucket droneBucket = DroneBucketItemORM.to(
                jpaDroneBucketRepository.findAllByDroneORM(new DroneORM(Objects.requireNonNull(
                        drone.getIdentity(), "Expected not null drone identity. provided null.").getId())));
        droneBucket.setDrone(drone);

        return droneBucket;
    }

    @Override
    public DroneBucket persistDroneBucketItems(final DroneBucket droneBucket) {
        return null;
    }

    @Override
    public DroneBucket persistDroneBucket(final DroneBucket droneBucket) {
        // TODO fill items (medication, etc.) by id getting from the database to have the item weights.

        return DroneBucketItemORM.to(jpaDroneBucketRepository.saveAll(DroneBucketItemORM.from(droneBucket)));
    }
}
