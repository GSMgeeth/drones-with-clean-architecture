package com.musalasoft.drones.domain.usecase.drone;

import com.musalasoft.drones.domain.entity.drone.DroneConnectivityException;
import com.musalasoft.drones.domain.entity.drone.IDroneAPI;
import com.musalasoft.drones.domain.repository.drone.IDroneRepository;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class DroneDynamicService {
    private static final Logger logger = Logger.getLogger("Drone Dynamic Service Logger");

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private final IDroneAPI droneApi;
    private final IDroneRepository droneRepository;

    public DroneDynamicService(IDroneAPI droneApi, IDroneRepository droneRepository) {
        this.droneApi = droneApi;
        this.droneRepository = droneRepository;
    }

    void droneBatteryLevelLogger() {
        droneRepository.getAllActiveDrones().parallelStream().forEach(drone -> {
            try {
                final double batteryPercentageReceived = droneApi.getBatteryLevelAsPercentage(drone.getSerialNumber());
                logger.log(Level.INFO, () ->
                        format("Drone %s : battery level is %s", drone.getSerialNumber(), batteryPercentageReceived));
            } catch (DroneConnectivityException e) {
                logger.log(Level.WARNING, () ->
                        format("Drone %s connectivity failure. %s", drone.getSerialNumber(), e.getMessage()));
            }
        });
    }

    void droneLoadedWeightLogger() {
        droneRepository.getAllActiveDrones().parallelStream().forEach(drone -> {
            try {
                final double loadedWeightReceived = droneApi.getLoadedWeightInGrams(drone.getSerialNumber());
                logger.log(Level.INFO, () ->
                        format("Drone %s : loaded weight is %s (max %s)",
                                drone.getSerialNumber(), loadedWeightReceived, drone.getWeightLimit()));
            } catch (DroneConnectivityException e) {
                logger.log(Level.WARNING, () ->
                        format("Drone %s connectivity failure. %s", drone.getSerialNumber(), e.getMessage()));
            }
        });
    }

    public void startDroneDynamicStatusUpdater() {
        // Starts drone battery level logger and sets to stop in 1 hours after started.
        // If logger needs to be keep running, get the scheduler out of stopScheduler and get rid of the stopScheduler caller below.
        stopScheduler(
                scheduler.scheduleAtFixedRate(this::droneBatteryLevelLogger, 10, 60, SECONDS),
                1, DAYS);

        // Starts drone loaded weight logger and sets to stop in 1 hours after started.
        // If logger needs to be keep running, get the scheduler out of stopScheduler and get rid of the stopScheduler caller below.
        stopScheduler(
                scheduler.scheduleAtFixedRate(this::droneLoadedWeightLogger, 10, 600, SECONDS),
                8, HOURS);
    }

    private void stopScheduler(final ScheduledFuture<?> scheduler, final long cancelIn, final TimeUnit timeUnit) {
        this.scheduler.schedule(() -> scheduler.cancel(false), cancelIn, timeUnit);
    }
}
