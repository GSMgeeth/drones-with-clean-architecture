package com.musalasoft.drones.infrastructure.rest.api.drone;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.usecase.drone.RegisterDroneUseCase;
import com.musalasoft.drones.domain.usecase.exception.AlreadyExistsException;

import java.util.Objects;

public class DroneController {
    private final RegisterDroneUseCase registerDroneUseCase;

    public DroneController(RegisterDroneUseCase registerDroneUseCase) {
        this.registerDroneUseCase = registerDroneUseCase;
    }

    Drone registerDrone(final Drone drone) throws NullPointerException, AlreadyExistsException {
        return registerDroneUseCase.execute(Objects.requireNonNull(drone));
    }
}
