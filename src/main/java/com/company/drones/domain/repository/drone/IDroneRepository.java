package com.company.drones.domain.repository.drone;

import com.company.drones.domain.entity.drone.Drone;
import com.company.drones.domain.entity.identity.Identity;
import com.company.drones.domain.entity.drone.DroneState;

import java.util.List;

public interface IDroneRepository {

    /**
     * Gets a drone by identity.
     *
     * @param identity Unique identity of the drone.
     * @return Drone if found by the identity. If not found, returns null.
     */
    Drone getDroneByIdentity(final Identity identity);

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
