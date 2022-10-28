package com.company.drones.domain.usecase.drone;

import com.company.drones.domain.usecase.IDroneAPI;
import com.company.drones.domain.repository.drone.IDroneRepository;
import com.company.drones.domain.usecase.IUseCase;
import com.company.drones.domain.usecase.exception.DroneConnectivityException;

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

public class LogDroneStatusUseCase implements IUseCase<Void, Void> {
    private static final Logger logger = Logger.getLogger("Log Drone Status Use Case Logger");

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private final IDroneAPI droneApi;
    private final IDroneRepository droneRepository;

    public LogDroneStatusUseCase(IDroneAPI droneApi, IDroneRepository droneRepository) {
        this.droneApi = droneApi;
        this.droneRepository = droneRepository;
    }

    @Override
    public Void execute(final Void unused) {
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

        return unused;
    }

    private void droneBatteryLevelLogger() {
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

    private void droneLoadedWeightLogger() {
        droneRepository.getAllActiveDrones().parallelStream().forEach(drone -> {
            try {
                final double loadedWeightReceived = droneApi.getLoadedWeightInGrams(drone.getSerialNumber());
                logger.log(Level.INFO, () ->
                        format("Drone %s : loaded weight is %s (max %s)",
                                drone.getSerialNumber(), loadedWeightReceived, drone.getWeightLimit()));

                if (loadedWeightReceived > drone.getWeightLimit()) {
                    logger.log(Level.WARNING, () ->
                            format("Drone %s : exceeded weight limit. Please unload the extra weight.",
                                    drone.getSerialNumber()));
                }
            } catch (DroneConnectivityException e) {
                logger.log(Level.WARNING, () ->
                        format("Drone %s connectivity failure. %s", drone.getSerialNumber(), e.getMessage()));
            }
        });
    }

    private void stopScheduler(final ScheduledFuture<?> scheduler, final long cancelIn, final TimeUnit timeUnit) {
        this.scheduler.schedule(() -> scheduler.cancel(false), cancelIn, timeUnit);
    }
}
