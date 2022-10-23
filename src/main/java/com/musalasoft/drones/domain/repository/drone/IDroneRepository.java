package com.musalasoft.drones.domain.repository.drone;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.drone.DroneState;

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
     * Gets a list of drones by state.
     *
     * @param droneState state of the drone.
     * @return List of Drones with the required state.
     */
    List<Drone> getDronesByState(final DroneState droneState);

    /**
     * Gets all active drones.
     *
     * @return List of all active drones.
     */
    List<Drone> getAllActiveDrones();

    /**
     * Persists a drone in a data store.
     *
     * @param drone The drone object to be saved.
     * @return Saved version of the drone object.
     */
    Drone persistDrone(final Drone drone);
}
