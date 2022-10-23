package com.musalasoft.drones.infrastructure.rest.api.drone;

import com.musalasoft.drones.domain.entity.drone.DroneModel;
import com.musalasoft.drones.domain.entity.drone.DroneState;
import com.musalasoft.drones.domain.usecase.drone.GetDroneBatteryLevelBySerialNumberUseCase;
import com.musalasoft.drones.domain.usecase.drone.GetDroneBySerialNumberUseCase;
import com.musalasoft.drones.domain.usecase.drone.GetDronesByState;
import com.musalasoft.drones.domain.usecase.drone.RegisterDroneUseCase;
import com.musalasoft.drones.domain.usecase.exception.NotFoundException;
import com.musalasoft.drones.infrastructure.database.drone.JPADroneRepository;
import com.musalasoft.drones.infrastructure.external.drone.DroneConnector;
import com.musalasoft.drones.infrastructure.repository.drone.DroneRepository;
import com.musalasoft.drones.infrastructure.rest.api.drone.dto.DroneResponseDTO;
import com.musalasoft.drones.infrastructure.rest.api.drone.dto.RegisterDroneRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DroneResourceTest {
    private DroneResource droneResource;

    @Autowired
    private JPADroneRepository jpaDroneRepository;

    @BeforeEach
    void setUp() {
        DroneRepository droneRepository = new DroneRepository(jpaDroneRepository);
        DroneConnector droneApi = new DroneConnector();

        RegisterDroneUseCase registerDroneUseCase = new RegisterDroneUseCase(droneRepository);
        GetDroneBySerialNumberUseCase getDroneBySerialNumberUseCase = new GetDroneBySerialNumberUseCase(droneRepository);
        GetDroneBatteryLevelBySerialNumberUseCase getDroneBatteryLevelBySerialNumberUseCase = new GetDroneBatteryLevelBySerialNumberUseCase(droneRepository, droneApi);
        GetDronesByState getDronesByState = new GetDronesByState(droneRepository);

        RegisterDroneController registerDroneController = new RegisterDroneController(registerDroneUseCase);
        GetDroneController getDroneController = new GetDroneController(getDroneBySerialNumberUseCase, getDroneBatteryLevelBySerialNumberUseCase, getDronesByState);

        this.droneResource = new DroneResource(registerDroneController, getDroneController);
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

    @Test
    @Order(2)
    void getDroneBySerialNumber() {
        final String serialNumber = "DRN-BY-SRL";
        final RegisterDroneRequestDTO registerDroneRequestDTO = new RegisterDroneRequestDTO(
                serialNumber,
                DroneModel.HEAVYWEIGHT.getValue(),
                DroneState.IDLE.getValue(),
                true
        );

        droneResource.registerDrone(registerDroneRequestDTO);
        final DroneResponseDTO responseDTO = droneResource.getDroneBySerialNumber(serialNumber).getBody();

        assert responseDTO != null;
        assertThat(responseDTO.serialNumber()).isEqualTo(serialNumber);
    }

    @Test
    @Order(3)
    void getDroneBatteryLevelBySerialNumber() {
        final String serialNumber = "DRN-BTR-BY-SRL";
        final RegisterDroneRequestDTO registerDroneRequestDTO = new RegisterDroneRequestDTO(
                serialNumber,
                DroneModel.HEAVYWEIGHT.getValue(),
                DroneState.IDLE.getValue(),
                true
        );

        droneResource.registerDrone(registerDroneRequestDTO);
        final Double responseDTO = droneResource.getDroneBatteryLevelBySerialNumber(serialNumber).getBody();

        assert responseDTO != null;
        assertThat(responseDTO).isEqualTo(0.0);
    }

    @Test
    @Order(4)
    void getNotExistsDroneBatteryLevelBySerialNumberThrowsNotFoundException() {
        assertThrows(NotFoundException.class, () -> droneResource.getDroneBatteryLevelBySerialNumber("NOT-EXISTS-DRONE"));
    }
}
