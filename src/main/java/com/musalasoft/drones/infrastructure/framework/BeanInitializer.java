package com.musalasoft.drones.infrastructure.framework;

import com.musalasoft.drones.domain.entity.drone.DroneDynamicService;
import com.musalasoft.drones.domain.entity.drone.IDroneAPI;
import com.musalasoft.drones.domain.entity.drone.IDroneRepository;
import com.musalasoft.drones.domain.usecase.drone.GetDroneBySerialNumberUseCase;
import com.musalasoft.drones.domain.usecase.drone.RegisterDroneUseCase;
import com.musalasoft.drones.domain.usecase.drone.UpdateDroneStateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanInitializer {

    @Bean
    public DroneDynamicService droneDynamicService(IDroneAPI droneApi, IDroneRepository droneRepository) {
        final DroneDynamicService droneDynamicService = new DroneDynamicService(droneApi, droneRepository);
        droneDynamicService.startDroneDynamicStatusUpdater();

        return droneDynamicService;
    }

    @Bean
    public RegisterDroneUseCase registerDroneUseCase(IDroneRepository droneRepository) {
        return new RegisterDroneUseCase(droneRepository);
    }

    @Bean
    public GetDroneBySerialNumberUseCase getDronesBySerialNumberUseCase(IDroneRepository droneRepository) {
        return new GetDroneBySerialNumberUseCase(droneRepository);
    }

    @Bean
    public UpdateDroneStateUseCase updateDroneStateUseCase(IDroneRepository droneRepository) {
        return new UpdateDroneStateUseCase(droneRepository);
    }
}
