package com.musalasoft.drones.infrastructure.repository.drone;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.repository.drone.IDroneRepository;
import com.musalasoft.drones.domain.entity.exception.InvalidClassAttributeException;
import com.musalasoft.drones.domain.entity.exception.InvalidIdentityException;

import java.util.List;

public class DroneRepository implements IDroneRepository {
    private final JPADroneRepository jpaDroneRepository;

    public DroneRepository(JPADroneRepository jpaDroneRepository) {
        this.jpaDroneRepository = jpaDroneRepository;
    }

    @Override
    public Drone getDroneBySerialNumber(final String serialNumber) throws InvalidClassAttributeException, InvalidIdentityException {
        return jpaDroneRepository
                .findOneBySerialNumber(serialNumber)
                .map(DroneORM::toDrone)
                .orElse(null);
    }

    @Override
    public List<Drone> getAllActiveDrones() throws InvalidClassAttributeException, InvalidIdentityException {
        return jpaDroneRepository
                .findAllByActiveIsTrue().stream()
                .map(DroneORM::toDrone)
                .toList();
    }

    @Override
    public Drone persistDrone(final Drone drone) throws InvalidClassAttributeException, InvalidIdentityException, NullPointerException {
        return jpaDroneRepository
                .save(DroneORM.from(drone))
                .toDrone();
    }
}
