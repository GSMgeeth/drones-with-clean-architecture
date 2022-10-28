package com.company.drones.domain.usecase.drone;

import com.company.drones.domain.entity.drone.Drone;
import com.company.drones.domain.usecase.IUseCase;
import com.company.drones.domain.repository.drone.IDroneRepository;

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
