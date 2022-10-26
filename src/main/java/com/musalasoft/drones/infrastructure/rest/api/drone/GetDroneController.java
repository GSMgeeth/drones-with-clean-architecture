package com.musalasoft.drones.infrastructure.rest.api.drone;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.drone.DroneState;
import com.musalasoft.drones.domain.entity.drone_bucket.DroneBucket;
import com.musalasoft.drones.domain.usecase.drone.GetDroneBatteryLevelBySerialNumberUseCase;
import com.musalasoft.drones.domain.usecase.drone.GetDroneBySerialNumberUseCase;
import com.musalasoft.drones.domain.usecase.drone.GetDronesByStateUseCase;
import com.musalasoft.drones.domain.usecase.drone_bucket.GetDroneBucketByDroneUseCase;
import com.musalasoft.drones.domain.usecase.exception.NotFoundException;

import java.util.List;

public class GetDroneController {
    private final GetDroneBySerialNumberUseCase getDroneBySerialNumberUseCase;
    private final GetDroneBatteryLevelBySerialNumberUseCase getDroneBatteryLevelBySerialNumberUseCase;
    private final GetDronesByStateUseCase getDronesByStateUseCase;
    private final GetDroneBucketByDroneUseCase getDroneBucketByDroneUseCase;

    public GetDroneController(GetDroneBySerialNumberUseCase getDroneBySerialNumberUseCase, GetDroneBatteryLevelBySerialNumberUseCase getDroneBatteryLevelBySerialNumberUseCase, GetDronesByStateUseCase getDronesByStateUseCase, GetDroneBucketByDroneUseCase getDroneBucketByDroneUseCase) {
        this.getDroneBySerialNumberUseCase = getDroneBySerialNumberUseCase;
        this.getDroneBatteryLevelBySerialNumberUseCase = getDroneBatteryLevelBySerialNumberUseCase;
        this.getDronesByStateUseCase = getDronesByStateUseCase;
        this.getDroneBucketByDroneUseCase = getDroneBucketByDroneUseCase;
    }

    Drone getDroneBySerialNumber(final String serialNumber) {
        return getDroneBySerialNumberUseCase.execute(serialNumber);
    }

    Double getDroneBatteryLevelBySerialNumber(final String serialNumber) throws NotFoundException, NullPointerException {
        return getDroneBatteryLevelBySerialNumberUseCase.execute(serialNumber);
    }

    List<Drone> getDronesByState(final String droneState) throws NullPointerException {
        return getDronesByStateUseCase.execute(DroneState.getKey(droneState));
    }

    DroneBucket getDroneBucketByDrone(final Long droneId) {
        return getDroneBucketByDroneUseCase.execute(new Drone(droneId));
    }
}
