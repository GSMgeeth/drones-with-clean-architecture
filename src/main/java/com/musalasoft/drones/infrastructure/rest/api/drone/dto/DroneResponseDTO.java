package com.musalasoft.drones.infrastructure.rest.api.drone.dto;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.exception.InvalidClassAttributeException;
import com.musalasoft.drones.domain.entity.exception.InvalidIdentityException;
import com.musalasoft.drones.infrastructure.database.identity.IdentityEntity;

import java.io.Serializable;

public record DroneResponseDTO(Long id, String serialNumber, String droneModel, String droneState, boolean active)
        implements Serializable {

    public static DroneResponseDTO from(final Drone drone) throws InvalidClassAttributeException, InvalidIdentityException, NullPointerException {
        return new DroneResponseDTO(
                IdentityEntity.from(drone.getIdentity()),
                drone.getSerialNumber(),
                drone.getDroneModel().getValue(),
                drone.getDroneState().getValue(),
                drone.isActive());
    }
}
