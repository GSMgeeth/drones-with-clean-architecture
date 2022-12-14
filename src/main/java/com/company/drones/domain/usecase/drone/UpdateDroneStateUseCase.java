package com.company.drones.domain.usecase.drone;

import com.company.drones.domain.entity.drone.Drone;
import com.company.drones.domain.usecase.IDroneAPI;
import com.company.drones.domain.entity.drone.DroneState;
import com.company.drones.domain.repository.drone.IDroneRepository;
import com.company.drones.domain.usecase.IUseCase;
import com.company.drones.domain.usecase.exception.OperationPreconditionsFailedException;
import com.company.drones.domain.usecase.exception.NotFoundException;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class UpdateDroneStateUseCase implements IUseCase<Drone, Drone> {
    private static final Logger logger = Logger.getLogger("Update Drone State Use Case Logger");

    private final IDroneRepository droneRepository;
    private final IDroneAPI droneApi;

    public UpdateDroneStateUseCase(IDroneRepository droneRepository, IDroneAPI droneApi) {
        this.droneRepository = droneRepository;
        this.droneApi = droneApi;
    }

    @Override
    public Drone execute(final Drone drone) throws NotFoundException, OperationPreconditionsFailedException, NullPointerException {
        final Drone existingDrone = droneRepository.getDroneBySerialNumber(Objects.requireNonNull(drone).getSerialNumber());

        if (existingDrone == null) {
            final String message = format("Drone by the serial number '%s' doesn't exists.", drone.getSerialNumber());

            logger.log(Level.WARNING, () -> message);
            throw new NotFoundException(message);
        }

        validateDroneBatteryLevelForState(drone, existingDrone);
        validateDroneLoadedWeightForState(drone, existingDrone);

        existingDrone.setDroneState(drone.getDroneState());

        return droneRepository.persistDrone(existingDrone);
    }

    private void validateDroneLoadedWeightForState(final Drone drone, final Drone existingDrone) {
        final double loadedWeight = droneApi.getLoadedWeightInGrams(existingDrone.getSerialNumber());

        if (drone.getDroneState().equals(DroneState.DELIVERING) && loadedWeight > existingDrone.getWeightLimit()) {
            final String message = format("Drone %s loaded weight is greater than it's weight limit to be in the %s state.",
                    drone.getSerialNumber(), drone.getDroneState());

            logger.log(Level.WARNING, () -> message);
            throw new OperationPreconditionsFailedException(message);
        }
    }

    private void validateDroneBatteryLevelForState(final Drone drone, final Drone existingDrone) {
        final double batteryPercentage = droneApi.getBatteryLevelAsPercentage(existingDrone.getSerialNumber());

        if ((drone.getDroneState().equals(DroneState.LOADING) || drone.getDroneState().equals(DroneState.DELIVERING)) &&
                batteryPercentage < 25) {
            final String message = format("Drone %s battery level is below 25 percent to be in the %s state.",
                    drone.getSerialNumber(), drone.getDroneState());

            logger.log(Level.WARNING, () -> message);
            throw new OperationPreconditionsFailedException(message);
        }
    }
}
