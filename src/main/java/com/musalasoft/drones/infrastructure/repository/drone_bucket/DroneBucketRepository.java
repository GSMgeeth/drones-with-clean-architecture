package com.musalasoft.drones.infrastructure.repository.drone_bucket;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.drone_bucket.DroneBucket;
import com.musalasoft.drones.domain.entity.exception.InvalidClassAttributeException;
import com.musalasoft.drones.domain.entity.exception.InvalidIdentityException;
import com.musalasoft.drones.domain.repository.drone_bucket.IDroneBucketRepository;
import com.musalasoft.drones.infrastructure.database.drone.DroneORM;
import com.musalasoft.drones.infrastructure.database.drone_bucket.DroneBucketItemORM;
import com.musalasoft.drones.infrastructure.database.drone_bucket.JPADroneBucketRepository;

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
        return DroneBucketItemORM.to(jpaDroneBucketRepository.saveAll(DroneBucketItemORM.from(droneBucket)));
    }
}
