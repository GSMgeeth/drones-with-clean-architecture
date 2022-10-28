package com.company.drones.infrastructure.rest.api.drone;

import com.company.drones.domain.entity.drone.Drone;
import com.company.drones.domain.entity.drone.DroneState;
import com.company.drones.domain.usecase.drone.GetDroneBySerialNumberUseCase;
import com.company.drones.domain.usecase.drone.GetDronesByStateUseCase;
import com.company.drones.domain.usecase.exception.NotFoundException;
import com.company.drones.domain.usecase.drone.GetDroneBatteryLevelBySerialNumberUseCase;

import java.util.List;

public class GetDroneController {
    private final GetDroneBySerialNumberUseCase getDroneBySerialNumberUseCase;
    private final GetDroneBatteryLevelBySerialNumberUseCase getDroneBatteryLevelBySerialNumberUseCase;
    private final GetDronesByStateUseCase getDronesByStateUseCase;

    public GetDroneController(GetDroneBySerialNumberUseCase getDroneBySerialNumberUseCase, GetDroneBatteryLevelBySerialNumberUseCase getDroneBatteryLevelBySerialNumberUseCase, GetDronesByStateUseCase getDronesByStateUseCase) {
        this.getDroneBySerialNumberUseCase = getDroneBySerialNumberUseCase;
        this.getDroneBatteryLevelBySerialNumberUseCase = getDroneBatteryLevelBySerialNumberUseCase;
        this.getDronesByStateUseCase = getDronesByStateUseCase;
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
}
