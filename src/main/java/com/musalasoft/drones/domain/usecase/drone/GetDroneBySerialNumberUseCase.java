package com.musalasoft.drones.domain.usecase.drone;

import com.musalasoft.drones.domain.usecase.IUseCase;
import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.drone.IDroneRepository;

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
