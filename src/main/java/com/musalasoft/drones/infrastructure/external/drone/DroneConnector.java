package com.musalasoft.drones.infrastructure.external.drone;

import com.musalasoft.drones.domain.drone.IDroneAPI;
import com.musalasoft.drones.domain.exception.DroneConnectivityException;

public class DroneConnector implements IDroneAPI {

    @Override
    public double getBatteryLevelAsPercentage(final String serialNumber) throws DroneConnectivityException {
        return 0;
    }

    @Override
    public double getLoadedWeightInGrams(final String serialNumber) throws DroneConnectivityException {
        return 0;
    }
}
