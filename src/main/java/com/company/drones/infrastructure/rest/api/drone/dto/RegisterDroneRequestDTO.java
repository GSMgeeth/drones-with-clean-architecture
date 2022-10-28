package com.company.drones.infrastructure.rest.api.drone.dto;

import com.company.drones.domain.entity.drone.Drone;
import com.company.drones.domain.entity.drone.DroneModel;
import com.company.drones.domain.entity.drone.DroneState;
import com.company.drones.domain.entity.exception.InvalidClassAttributeException;
import com.company.drones.domain.entity.exception.InvalidIdentityException;

import java.io.Serializable;

public record RegisterDroneRequestDTO(String serialNumber, String droneModel, String droneState, boolean active)
        implements Serializable {

    private static Drone to(final RegisterDroneRequestDTO registerDroneRequestDTO) throws InvalidClassAttributeException, InvalidIdentityException, EnumConstantNotPresentException {
        return new Drone(
                registerDroneRequestDTO.serialNumber,
                DroneModel.getKey(registerDroneRequestDTO.droneModel),
                DroneState.getKey(registerDroneRequestDTO.droneState),
                registerDroneRequestDTO.active);
    }

    public Drone toDrone() throws InvalidClassAttributeException, InvalidIdentityException, EnumConstantNotPresentException {
        return to(this);
    }
}
