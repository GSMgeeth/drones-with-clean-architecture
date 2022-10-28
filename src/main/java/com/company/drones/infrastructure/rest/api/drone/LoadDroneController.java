package com.company.drones.infrastructure.rest.api.drone;

import com.company.drones.domain.entity.drone.Drone;
import com.company.drones.domain.usecase.drone.UpdateDroneStateUseCase;
import com.company.drones.domain.usecase.drone_bucket.LoadDroneBucketWithItemsUseCase;
import com.company.drones.domain.entity.drone_bucket.DroneBucket;
import com.company.drones.domain.usecase.drone_bucket.GetDroneBucketByDroneUseCase;

public class LoadDroneController {
    private final GetDroneBucketByDroneUseCase getDroneBucketByDroneUseCase;
    private final LoadDroneBucketWithItemsUseCase loadDroneBucketWithItemsUseCase;
    private final UpdateDroneStateUseCase updateDroneStateUseCase;

    public LoadDroneController(GetDroneBucketByDroneUseCase getDroneBucketByDroneUseCase, LoadDroneBucketWithItemsUseCase loadDroneBucketWithItemsUseCase, UpdateDroneStateUseCase updateDroneStateUseCase) {
        this.getDroneBucketByDroneUseCase = getDroneBucketByDroneUseCase;
        this.loadDroneBucketWithItemsUseCase = loadDroneBucketWithItemsUseCase;
        this.updateDroneStateUseCase = updateDroneStateUseCase;
    }

    DroneBucket getDroneBucketByDrone(final Long droneId) {
        return getDroneBucketByDroneUseCase.execute(new Drone(droneId));
    }

    DroneBucket loadDroneWithItems(final DroneBucket droneBucket) {
        updateDroneStateUseCase.execute(droneBucket.getDrone());

        return loadDroneBucketWithItemsUseCase.execute(droneBucket);
    }
}
