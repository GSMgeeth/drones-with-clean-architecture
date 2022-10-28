package com.company.drones.domain.usecase.drone;

import com.company.drones.domain.entity.drone.Drone;
import com.company.drones.domain.entity.drone.DroneState;
import com.company.drones.domain.repository.drone.IDroneRepository;
import com.company.drones.domain.usecase.IUseCase;

import java.util.List;

public class GetDronesByStateUseCase implements IUseCase<List<Drone>, DroneState> {
    private final IDroneRepository droneRepository;

    public GetDronesByStateUseCase(IDroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public List<Drone> execute(final DroneState droneState) {
        return droneRepository.getDronesByState(droneState);
    }
}
