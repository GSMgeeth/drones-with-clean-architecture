package com.musalasoft.drones.infrastructure.rest.api.drone;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.drone.DroneState;
import com.musalasoft.drones.domain.usecase.drone.GetDroneBatteryLevelBySerialNumberUseCase;
import com.musalasoft.drones.domain.usecase.drone.GetDroneBySerialNumberUseCase;
import com.musalasoft.drones.domain.usecase.drone.GetDronesByState;
import com.musalasoft.drones.domain.usecase.exception.NotFoundException;

import java.util.List;

public class GetDroneController {
    private final GetDroneBySerialNumberUseCase getDroneBySerialNumberUseCase;
    private final GetDroneBatteryLevelBySerialNumberUseCase getDroneBatteryLevelBySerialNumberUseCase;
    private final GetDronesByState getDronesByState;

    public GetDroneController(GetDroneBySerialNumberUseCase getDroneBySerialNumberUseCase, GetDroneBatteryLevelBySerialNumberUseCase getDroneBatteryLevelBySerialNumberUseCase, GetDronesByState getDronesByState) {
        this.getDroneBySerialNumberUseCase = getDroneBySerialNumberUseCase;
        this.getDroneBatteryLevelBySerialNumberUseCase = getDroneBatteryLevelBySerialNumberUseCase;
        this.getDronesByState = getDronesByState;
    }

    Drone getDroneBySerialNumber(final String serialNumber) {
        return getDroneBySerialNumberUseCase.execute(serialNumber);
    }

    Double getDroneBatteryLevelBySerialNumber(final String serialNumber) throws NotFoundException, NullPointerException {
        return getDroneBatteryLevelBySerialNumberUseCase.execute(serialNumber);
    }

    List<Drone> getDronesByState(final DroneState droneState) {
        return getDronesByState.execute(droneState);
    }
}
