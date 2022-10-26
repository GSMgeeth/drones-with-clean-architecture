package com.musalasoft.drones.infrastructure.rest.api.drone;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.drone.DroneState;
import com.musalasoft.drones.domain.entity.drone_bucket.DroneBucket;
import com.musalasoft.drones.domain.usecase.drone.GetDroneBatteryLevelBySerialNumberUseCase;
import com.musalasoft.drones.domain.usecase.drone.GetDroneBySerialNumberUseCase;
import com.musalasoft.drones.domain.usecase.drone.GetDronesByState;
import com.musalasoft.drones.domain.usecase.drone_bucket.GetDroneBucketByDrone;
import com.musalasoft.drones.domain.usecase.exception.NotFoundException;

import java.util.List;

public class GetDroneController {
    private final GetDroneBySerialNumberUseCase getDroneBySerialNumberUseCase;
    private final GetDroneBatteryLevelBySerialNumberUseCase getDroneBatteryLevelBySerialNumberUseCase;
    private final GetDronesByState getDronesByState;
    private final GetDroneBucketByDrone getDroneBucketByDrone;

    public GetDroneController(GetDroneBySerialNumberUseCase getDroneBySerialNumberUseCase, GetDroneBatteryLevelBySerialNumberUseCase getDroneBatteryLevelBySerialNumberUseCase, GetDronesByState getDronesByState, GetDroneBucketByDrone getDroneBucketByDrone) {
        this.getDroneBySerialNumberUseCase = getDroneBySerialNumberUseCase;
        this.getDroneBatteryLevelBySerialNumberUseCase = getDroneBatteryLevelBySerialNumberUseCase;
        this.getDronesByState = getDronesByState;
        this.getDroneBucketByDrone = getDroneBucketByDrone;
    }

    Drone getDroneBySerialNumber(final String serialNumber) {
        return getDroneBySerialNumberUseCase.execute(serialNumber);
    }

    Double getDroneBatteryLevelBySerialNumber(final String serialNumber) throws NotFoundException, NullPointerException {
        return getDroneBatteryLevelBySerialNumberUseCase.execute(serialNumber);
    }

    List<Drone> getDronesByState(final String droneState) throws NullPointerException {
        return getDronesByState.execute(DroneState.getKey(droneState));
    }

    DroneBucket getDroneBucketByDrone(final Long droneId) {
        return getDroneBucketByDrone.execute(new Drone(droneId));
    }
}
