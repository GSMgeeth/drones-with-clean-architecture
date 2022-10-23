package com.musalasoft.drones.domain.usecase;

import com.musalasoft.drones.domain.usecase.exception.DroneConnectivityException;

import java.util.logging.Level;
import java.util.logging.Logger;

public interface IDroneAPI {
    Logger LOGGER = Logger.getLogger("IDrone API Logger");

    /**
     * Get battery level as percentage from the drone by drone serial number.
     * <p>
     * <strong>Drone itself has to use battery API to get the battery level and that is not a responsibility of this method.<strong/>
     * </p>
     * <p>
     * This method should use the Drone SDK to connect to the drone by serial number and get the battery level.
     * If the drone does return battery level as a percentage, method should return the value as it is.
     * If the drone doesn't return battery level as a percentage, method should return the calculated percentage.
     * </p>
     *
     * @param serialNumber Unique serial number of the drone
     * @return Drone battery level as a percentage. If failed to contact drone, throws <strong>DroneConnectivityException<strong/>.
     */
    default double getBatteryLevelAsPercentage(final String serialNumber) throws DroneConnectivityException {
        LOGGER.log(Level.WARNING, () -> "IDroneAPI method getBatteryLevelAsPercentage has not been implemented. " +
                "Running default implementation.");
        return 0.0;
    }

    /**
     * Get current loaded weight in <i>grams<i/> from the drone by drone serial number.
     * <p>
     * This method should use the Drone SDK to connect to the drone by serial number and get the loaded weight.
     * If the drone does return loaded weight in <i>grams<i/>, method should return the value as it is.
     * If the drone doesn't return loaded weight in <i>grams<i/>, method should return the converted weight.
     * </p>
     *
     * @param serialNumber Unique serial number of the drone
     * @return Drone loaded weight in <i>grams<i/>. If failed to contact drone, throws <strong>DroneConnectivityException<strong/>.
     */
    default double getLoadedWeightInGrams(final String serialNumber) throws DroneConnectivityException {
        LOGGER.log(Level.WARNING, () -> "IDroneAPI method getLoadedWeightInGrams has not been implemented. " +
                "Running default implementation.");
        return 0.0;
    }
}
