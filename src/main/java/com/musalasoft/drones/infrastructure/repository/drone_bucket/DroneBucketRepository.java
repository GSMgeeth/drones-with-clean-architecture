package com.musalasoft.drones.infrastructure.repository.drone_bucket;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.drone_bucket.DroneBucket;
import com.musalasoft.drones.domain.repository.drone_bucket.IDroneBucketRepository;
import com.musalasoft.drones.infrastructure.database.drone.DroneORM;
import com.musalasoft.drones.infrastructure.database.drone_bucket.DroneBucketItemORM;
import com.musalasoft.drones.infrastructure.database.drone_bucket.JPADroneBucketRepository;

public class DroneBucketRepository implements IDroneBucketRepository {
    private final JPADroneBucketRepository jpaDroneBucketRepository;

    public DroneBucketRepository(JPADroneBucketRepository jpaDroneBucketRepository) {
        this.jpaDroneBucketRepository = jpaDroneBucketRepository;
    }

    @Override
    public DroneBucket getDroneBucketByDrone(final Drone drone) {
        final DroneBucket droneBucket = DroneBucketItemORM.to(
                jpaDroneBucketRepository.findAllByDroneORM(DroneORM.from(drone)));
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
