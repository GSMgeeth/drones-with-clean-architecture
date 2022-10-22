package com.musalasoft.drones.domain.drone.usecase;

import com.musalasoft.drones.domain.IUseCase;
import com.musalasoft.drones.domain.drone.Drone;
import com.musalasoft.drones.domain.drone.IDroneRepository;

public class GetDroneBySerialNumberUseCase implements IUseCase<Drone, String> {
    private final IDroneRepository droneRepository;

    public GetDroneBySerialNumberUseCase(IDroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public Drone execute(final String serialNumber) {
        return droneRepository.getDroneBySerialNumber(serialNumber);
    }
}