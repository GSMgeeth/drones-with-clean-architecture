package com.musalasoft.drones.domain.drone;

import java.util.List;

public interface IDroneRepository {

    /**
     * Gets a drone by serial number.
     *
     * @param serialNumber Unique serial number of the drone.
     * @return Drone if found by the serial number. If not found, returns null.
     */
    Drone getDroneBySerialNumber(final String serialNumber);

    /**
     * Gets all active drones.
     *
     * @return List of all active drones.
     */
    List<Drone> getAllActiveDrones();

    /**
     * Saves a drone.
     *
     * @param drone The drone object to be saved.
     * @return Saved version of the drone object.
     */
    Drone save(final Drone drone);
}
