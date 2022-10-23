package com.musalasoft.drones.infrastructure.rest.api.drone;

import com.musalasoft.drones.domain.entity.drone.DroneModel;
import com.musalasoft.drones.domain.entity.drone.DroneState;
import com.musalasoft.drones.domain.usecase.drone.RegisterDroneUseCase;
import com.musalasoft.drones.infrastructure.database.drone.JPADroneRepository;
import com.musalasoft.drones.infrastructure.repository.drone.DroneRepository;
import com.musalasoft.drones.infrastructure.rest.api.drone.dto.DroneResponseDTO;
import com.musalasoft.drones.infrastructure.rest.api.drone.dto.RegisterDroneRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DroneResourceTest {
    private DroneResource droneResource;

    @Autowired
    private JPADroneRepository jpaDroneRepository;

    @BeforeEach
    void setUp() {
        DroneRepository droneRepository = new DroneRepository(jpaDroneRepository);

        RegisterDroneUseCase registerDroneUseCase = new RegisterDroneUseCase(droneRepository);
        DroneController droneController = new DroneController(registerDroneUseCase);
        this.droneResource = new DroneResource(droneController);
    }

    @Test
    @Order(1)
    void registerDrone() {
        final String serialNumber = "DRN-002";
        final RegisterDroneRequestDTO registerDroneRequestDTO = new RegisterDroneRequestDTO(
                serialNumber,
                DroneModel.HEAVYWEIGHT.getValue(),
                DroneState.IDLE.getValue(),
                true
        );

        final DroneResponseDTO responseDTO = droneResource.registerDrone(registerDroneRequestDTO).getBody();

        assert responseDTO != null;
        assertThat(responseDTO.serialNumber()).isEqualTo(serialNumber);
    }
}
