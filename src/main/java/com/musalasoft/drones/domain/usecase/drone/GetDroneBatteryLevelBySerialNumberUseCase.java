package com.musalasoft.drones.domain.usecase.drone;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.repository.drone.IDroneRepository;
import com.musalasoft.drones.domain.usecase.IDroneAPI;
import com.musalasoft.drones.domain.usecase.IUseCase;
import com.musalasoft.drones.domain.usecase.exception.NotFoundException;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class GetDroneBatteryLevelBySerialNumberUseCase implements IUseCase<Double, String> {
    private static final Logger logger = Logger.getLogger("Get Drone Battery Level By Serial Number Use Case Logger");

    private final IDroneRepository droneRepository;
    private final IDroneAPI droneApi;

    public GetDroneBatteryLevelBySerialNumberUseCase(IDroneRepository droneRepository, IDroneAPI droneApi) {
        this.droneRepository = droneRepository;
        this.droneApi = droneApi;
    }

    @Override
    public Double execute(final String serialNumber) {
        final Drone existingDrone = droneRepository.getDroneBySerialNumber(Objects.requireNonNull(serialNumber));

        if (existingDrone == null) {
            final String message = format("Drone by the serial number '%s' doesn't exists.", serialNumber);

            logger.log(Level.WARNING, () -> message);
            throw new NotFoundException(message);
        }

        return droneApi.getBatteryLevelAsPercentage(serialNumber);
    }
}
