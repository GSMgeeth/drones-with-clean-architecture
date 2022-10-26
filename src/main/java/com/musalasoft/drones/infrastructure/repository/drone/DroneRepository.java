package com.musalasoft.drones.infrastructure.repository.drone;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.drone.DroneState;
import com.musalasoft.drones.domain.entity.exception.InvalidClassAttributeException;
import com.musalasoft.drones.domain.entity.exception.InvalidIdentityException;
import com.musalasoft.drones.domain.repository.drone.IDroneRepository;
import com.musalasoft.drones.infrastructure.database.drone.DroneORM;
import com.musalasoft.drones.infrastructure.database.drone.JPADroneRepository;

import java.util.List;
import java.util.Objects;

public class DroneRepository implements IDroneRepository {
    private final JPADroneRepository jpaDroneRepository;

    public DroneRepository(JPADroneRepository jpaDroneRepository) {
        this.jpaDroneRepository = jpaDroneRepository;
    }

    @Override
    public Drone getDroneBySerialNumber(final String serialNumber) throws InvalidClassAttributeException, InvalidIdentityException, EnumConstantNotPresentException {
        return jpaDroneRepository
                .findOneBySerialNumber(serialNumber)
                .map(DroneORM::toDrone)
                .orElse(null);
    }

    @Override
    public List<Drone> getDronesByState(final DroneState droneState) throws NullPointerException {
        return jpaDroneRepository
                .findAllByActiveIsTrueAndDroneState(Objects.requireNonNull(
                        droneState, "Expected not null drone state. provided null.").getValue()).stream()
                .map(DroneORM::toDrone)
                .toList();
    }

    @Override
    public List<Drone> getAllActiveDrones() throws InvalidClassAttributeException, InvalidIdentityException, EnumConstantNotPresentException {
        return jpaDroneRepository
                .findAllByActiveIsTrue().stream()
                .map(DroneORM::toDrone)
                .toList();
    }

    @Override
    public Drone persistDrone(final Drone drone) throws InvalidClassAttributeException, InvalidIdentityException, EnumConstantNotPresentException, NullPointerException {
        return jpaDroneRepository
                .save(DroneORM.from(drone))
                .toDrone();
    }
}
