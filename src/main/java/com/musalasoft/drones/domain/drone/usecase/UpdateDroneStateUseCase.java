package com.musalasoft.drones.domain.drone.usecase;

import com.musalasoft.drones.domain.IUseCase;
import com.musalasoft.drones.domain.drone.Drone;
import com.musalasoft.drones.domain.drone.IDroneRepository;
import com.musalasoft.drones.domain.exception.NotFoundException;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class UpdateDroneStateUseCase implements IUseCase<Drone, Drone> {
    private static final Logger logger = Logger.getLogger("Update Drone State Use Case Logger");

    private final IDroneRepository droneRepository;

    public UpdateDroneStateUseCase(IDroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public Drone execute(final Drone drone) throws NotFoundException {
        final Drone existingDrone = droneRepository.getDroneBySerialNumber(drone.getSerialNumber());

        if (existingDrone == null) {
            final String message = format("Drone by the serial number '%s' doesn't exists.", drone.getSerialNumber());

            logger.log(Level.WARNING, () -> message);
            throw new NotFoundException(message);
        }

        existingDrone.setDroneState(drone.getDroneState());

        return droneRepository.persistDrone(existingDrone);
    }
}
