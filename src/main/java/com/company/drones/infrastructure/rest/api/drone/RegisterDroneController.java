package com.company.drones.infrastructure.rest.api.drone;

import com.company.drones.domain.entity.drone.Drone;
import com.company.drones.domain.usecase.drone.RegisterDroneUseCase;
import com.company.drones.domain.usecase.exception.AlreadyExistsException;

import java.util.Objects;

public class RegisterDroneController {
    private final RegisterDroneUseCase registerDroneUseCase;

    public RegisterDroneController(RegisterDroneUseCase registerDroneUseCase) {
        this.registerDroneUseCase = registerDroneUseCase;
    }

    Drone registerDrone(final Drone drone) throws NullPointerException, AlreadyExistsException {
        return registerDroneUseCase.execute(Objects.requireNonNull(drone));
    }
}
