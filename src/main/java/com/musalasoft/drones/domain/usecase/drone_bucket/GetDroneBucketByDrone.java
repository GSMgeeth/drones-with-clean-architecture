package com.musalasoft.drones.domain.usecase.drone_bucket;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.drone_bucket.DroneBucket;
import com.musalasoft.drones.domain.usecase.IUseCase;

public class GetDroneBucketByDrone implements IUseCase<DroneBucket, Drone> {

    @Override
    public DroneBucket execute(final Drone input) {
        return null;
    }
}
