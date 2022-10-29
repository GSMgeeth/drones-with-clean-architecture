package com.company.drones.infrastructure.rest.api.drone;

import com.company.drones.domain.entity.drone.DroneModel;
import com.company.drones.domain.entity.drone.DroneState;
import com.company.drones.domain.usecase.drone.GetDroneBatteryLevelBySerialNumberUseCase;
import com.company.drones.domain.usecase.drone.GetDroneBySerialNumberUseCase;
import com.company.drones.domain.usecase.drone.GetDronesByStateUseCase;
import com.company.drones.domain.usecase.drone.RegisterDroneUseCase;
import com.company.drones.domain.usecase.drone.UpdateDroneStateUseCase;
import com.company.drones.domain.usecase.drone_bucket.GetDroneBucketByDroneUseCase;
import com.company.drones.domain.usecase.drone_bucket.LoadDroneBucketWithItemsUseCase;
import com.company.drones.domain.usecase.exception.NotFoundException;
import com.company.drones.infrastructure.database.drone.JPADroneRepository;
import com.company.drones.infrastructure.database.drone_bucket.JPADroneBucketRepository;
import com.company.drones.infrastructure.external.drone.DroneConnector;
import com.company.drones.infrastructure.repository.drone.DroneRepository;
import com.company.drones.infrastructure.repository.drone_bucket.DroneBucketRepository;
import com.company.drones.infrastructure.rest.api.drone.dto.DroneResponseDTO;
import com.company.drones.infrastructure.rest.api.drone.dto.RegisterDroneRequestDTO;
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

    @Autowired
    private JPADroneBucketRepository jpaDroneBucketRepository;

    @BeforeEach
    void setUp() {
        DroneRepository droneRepository = new DroneRepository(jpaDroneRepository);
        DroneBucketRepository droneBucketRepository = new DroneBucketRepository(jpaDroneBucketRepository);
        DroneConnector droneApi = new DroneConnector();

        RegisterDroneUseCase registerDroneUseCase = new RegisterDroneUseCase(droneRepository);
        GetDroneBySerialNumberUseCase getDroneBySerialNumberUseCase = new GetDroneBySerialNumberUseCase(droneRepository);
        GetDroneBatteryLevelBySerialNumberUseCase getDroneBatteryLevelBySerialNumberUseCase =
                new GetDroneBatteryLevelBySerialNumberUseCase(droneRepository, droneApi);
        GetDronesByStateUseCase getDronesByStateUseCase = new GetDronesByStateUseCase(droneRepository);
        UpdateDroneStateUseCase updateDroneStateUseCase = new UpdateDroneStateUseCase(droneRepository, droneApi);
        GetDroneBucketByDroneUseCase getDroneBucketByDroneUseCase = new GetDroneBucketByDroneUseCase(droneBucketRepository);
        LoadDroneBucketWithItemsUseCase loadDroneBucketWithItemsUseCase =
                new LoadDroneBucketWithItemsUseCase(droneBucketRepository, droneRepository);

        RegisterDroneController registerDroneController = new RegisterDroneController(registerDroneUseCase);
        GetDroneController getDroneController = new GetDroneController(
                getDroneBySerialNumberUseCase, getDroneBatteryLevelBySerialNumberUseCase, getDronesByStateUseCase);
        LoadDroneController loadDroneController = new LoadDroneController(
                getDroneBucketByDroneUseCase, loadDroneBucketWithItemsUseCase, updateDroneStateUseCase);

        this.droneResource = new DroneResource(registerDroneController, getDroneController, loadDroneController);
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
        assertThat(responseDTO).isEqualTo(75.0);
    }

    @Test
    @Order(4)
    void getNotExistsDroneBatteryLevelBySerialNumberThrowsNotFoundException() {
        assertThrows(NotFoundException.class, () -> droneResource.getDroneBatteryLevelBySerialNumber("NOT-EXISTS-DRONE"));
    }
}
