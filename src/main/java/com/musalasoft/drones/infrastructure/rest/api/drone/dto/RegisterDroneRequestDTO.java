package com.musalasoft.drones.infrastructure.rest.api.drone.dto;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.drone.DroneModel;
import com.musalasoft.drones.domain.entity.drone.DroneState;
import com.musalasoft.drones.domain.entity.exception.InvalidClassAttributeException;
import com.musalasoft.drones.domain.entity.exception.InvalidIdentityException;

import java.io.Serializable;

public record RegisterDroneRequestDTO(String serialNumber, String droneModel, String droneState, boolean active)
        implements Serializable {

    public static Drone to(final RegisterDroneRequestDTO registerDroneRequestDTO) throws InvalidClassAttributeException, InvalidIdentityException, EnumConstantNotPresentException {
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
