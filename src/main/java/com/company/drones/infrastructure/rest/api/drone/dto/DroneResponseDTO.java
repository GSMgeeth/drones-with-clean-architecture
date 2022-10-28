package com.company.drones.infrastructure.rest.api.drone.dto;

import com.company.drones.domain.entity.drone.Drone;
import com.company.drones.infrastructure.database.identity.IdentityEntity;

import java.io.Serializable;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public record DroneResponseDTO(Long id, String serialNumber, String droneModel, String droneState, double weightLimit,
                               boolean active) implements Serializable {

    public static DroneResponseDTO from(final Drone drone) {
        if (drone == null) {
            return null;
        }

        AtomicReference<String> model = new AtomicReference<>("");
        AtomicReference<String> state = new AtomicReference<>("");

        Optional.ofNullable(drone.getDroneModel()).ifPresent(dm -> model.set(dm.getValue()));
        Optional.ofNullable(drone.getDroneState()).ifPresent(ds -> state.set(ds.getValue()));

        return new DroneResponseDTO(
                IdentityEntity.from(drone.getIdentity()),
                drone.getSerialNumber(),
                model.get(),
                state.get(),
                drone.getWeightLimit(),
                drone.isActive());
    }
}
