package com.company.drones.infrastructure.external.drone;

import com.company.drones.domain.usecase.IDroneAPI;
import com.company.drones.domain.usecase.exception.DroneConnectivityException;
import org.springframework.stereotype.Component;

@Component
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
