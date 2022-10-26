package com.musalasoft.drones.infrastructure.rest.api.drone;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.drone_bucket.DroneBucket;
import com.musalasoft.drones.domain.usecase.drone.UpdateDroneStateUseCase;
import com.musalasoft.drones.domain.usecase.drone_bucket.GetDroneBucketByDroneUseCase;
import com.musalasoft.drones.domain.usecase.drone_bucket.LoadDroneBucketWithItemsUseCase;

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
