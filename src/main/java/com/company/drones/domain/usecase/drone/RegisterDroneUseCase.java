package com.company.drones.domain.usecase.drone;

import com.company.drones.domain.entity.drone.Drone;
import com.company.drones.domain.usecase.exception.AlreadyExistsException;
import com.company.drones.domain.usecase.IUseCase;
import com.company.drones.domain.repository.drone.IDroneRepository;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class RegisterDroneUseCase implements IUseCase<Drone, Drone> {
    private static final Logger logger = Logger.getLogger("Register Drone Use Case Logger");

    private final IDroneRepository droneRepository;

    public RegisterDroneUseCase(IDroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public Drone execute(final Drone drone) throws AlreadyExistsException, NullPointerException {
        if (droneRepository.getDroneBySerialNumber(Objects.requireNonNull(drone).getSerialNumber()) != null) {
            final String message = format("Drone by the serial number '%s' already exists.", drone.getSerialNumber());

            logger.log(Level.WARNING, () -> message);
            throw new AlreadyExistsException(message);
        }

        return droneRepository.persistDrone(drone);
    }
}
