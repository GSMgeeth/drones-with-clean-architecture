package com.musalasoft.drones.domain.usecase.drone;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.drone.DroneState;
import com.musalasoft.drones.domain.repository.drone.IDroneRepository;
import com.musalasoft.drones.domain.usecase.IUseCase;

import java.util.List;

public class GetDronesByState implements IUseCase<List<Drone>, DroneState> {
    private final IDroneRepository droneRepository;

    public GetDronesByState(IDroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public List<Drone> execute(final DroneState droneState) {
        return droneRepository.getDronesByState(droneState);
    }
}
