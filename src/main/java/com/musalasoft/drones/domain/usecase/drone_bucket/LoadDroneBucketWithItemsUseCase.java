package com.musalasoft.drones.domain.usecase.drone_bucket;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.drone_bucket.DroneBucket;
import com.musalasoft.drones.domain.repository.drone.IDroneRepository;
import com.musalasoft.drones.domain.repository.drone_bucket.IDroneBucketRepository;
import com.musalasoft.drones.domain.usecase.IUseCase;
import com.musalasoft.drones.domain.usecase.exception.NotFoundException;
import com.musalasoft.drones.domain.usecase.exception.OperationPreconditionsFailedException;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class LoadDroneBucketWithItemsUseCase implements IUseCase<DroneBucket, DroneBucket> {
    private static final Logger logger = Logger.getLogger(LoadDroneBucketWithItemsUseCase.class.getName());

    private final IDroneBucketRepository droneBucketRepository;
    private final IDroneRepository droneRepository;

    public LoadDroneBucketWithItemsUseCase(IDroneBucketRepository droneBucketRepository, IDroneRepository droneRepository) {
        this.droneBucketRepository = droneBucketRepository;
        this.droneRepository = droneRepository;
    }

    @Override
    public DroneBucket execute(final DroneBucket droneBucket) throws NotFoundException, OperationPreconditionsFailedException, NullPointerException {
        final DroneBucket droneBucketNonNull = Objects.requireNonNull(droneBucket);
        final Drone droneNonNull = Objects.requireNonNull(droneBucketNonNull.getDrone());

        final Drone existingDrone = droneRepository.getDroneBySerialNumber(droneNonNull.getSerialNumber());

        if (existingDrone == null) {
            final String message = format("Drone by the serial number '%s' doesn't exists.", droneNonNull.getSerialNumber());

            logger.log(Level.WARNING, () -> message);
            throw new NotFoundException(message);
        }

        if (droneBucketNonNull.getTotalWeight() > droneNonNull.getWeightLimit()) {
            final String message = format("Drone %s weight limit (%s) exceeds with the load weight (%s).",
                    existingDrone.getSerialNumber(), existingDrone.getWeightLimit(), droneBucketNonNull.getTotalWeight());

            logger.log(Level.WARNING, () -> message);
            throw new OperationPreconditionsFailedException(message);
        }

        droneBucketNonNull.getDrone().setIdentity(existingDrone.getIdentity());

        return droneBucketRepository.persistDroneBucket(droneBucketNonNull);
    }
}
