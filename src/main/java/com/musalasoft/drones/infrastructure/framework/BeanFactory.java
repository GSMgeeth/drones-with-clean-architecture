package com.musalasoft.drones.infrastructure.framework;

import com.musalasoft.drones.domain.drone.DroneDynamicService;
import com.musalasoft.drones.domain.drone.IDroneAPI;
import com.musalasoft.drones.domain.drone.IDroneRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanFactory {

    @Bean
    public DroneDynamicService droneDynamicService(IDroneAPI droneApi, IDroneRepository droneRepository) {
        final DroneDynamicService droneDynamicService = new DroneDynamicService(droneApi, droneRepository);
        droneDynamicService.startDroneDynamicStatusUpdater();

        return droneDynamicService;
    }
}
