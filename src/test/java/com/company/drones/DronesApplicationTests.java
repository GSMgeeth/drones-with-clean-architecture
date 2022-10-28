package com.company.drones;

import com.company.drones.domain.entity.drone.Drone;
import com.company.drones.domain.usecase.drone.GetDroneBySerialNumberUseCase;
import com.company.drones.domain.usecase.drone.RegisterDroneUseCase;
import com.company.drones.domain.usecase.drone.UpdateDroneStateUseCase;
import com.company.drones.domain.usecase.exception.AlreadyExistsException;
import com.company.drones.infrastructure.external.drone.DroneConnector;
import com.company.drones.domain.entity.drone.DroneModel;
import com.company.drones.domain.entity.drone.DroneState;
import com.company.drones.domain.usecase.exception.NotFoundException;
import com.company.drones.infrastructure.database.drone.JPADroneRepository;
import com.company.drones.infrastructure.repository.drone.DroneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DronesApplicationTests {
    private RegisterDroneUseCase registerDroneUseCase;
    private GetDroneBySerialNumberUseCase getDroneBySerialNumberUseCase;
    private UpdateDroneStateUseCase updateDroneStateUseCase;

    @Autowired
    private JPADroneRepository jpaDroneRepository;

    @BeforeEach
    void setUp() {
        DroneRepository droneRepository = new DroneRepository(jpaDroneRepository);
        DroneConnector droneApi = new DroneConnector();

        this.registerDroneUseCase = new RegisterDroneUseCase(droneRepository);
        this.getDroneBySerialNumberUseCase = new GetDroneBySerialNumberUseCase(droneRepository);
        this.updateDroneStateUseCase = new UpdateDroneStateUseCase(droneRepository, droneApi);
    }

    @Test
    @Order(1)
    void executeRegisterDrone() {
        final String serialNumber = "DRN-001";

        final Drone d = registerDroneUseCase
                .execute(new Drone(1L, serialNumber, DroneModel.HEAVYWEIGHT, DroneState.IDLE, true));

        assertThat(d.getSerialNumber()).isEqualTo(serialNumber);
    }

    @Test
    @Order(2)
    void executeRegisterDuplicateDroneThrowsAlreadyExistsException() {
        final String serialNumber = "DRN-001";

        final Drone d = new Drone(1L, serialNumber);

        assertThrows(AlreadyExistsException.class, () -> registerDroneUseCase.execute(d));
    }

    @Test
    @Order(3)
    void executeGetDroneBySerialNumber() {
        final String serialNumber = "DRN-001";

        final Drone d = getDroneBySerialNumberUseCase.execute(serialNumber);

        assertThat(d.getSerialNumber()).isEqualTo(serialNumber);
    }

    @Test
    @Order(4)
    void executeUpdateDroneState() {
        final String serialNumber = "DRN-001";

        final Drone d = getDroneBySerialNumberUseCase.execute(serialNumber);
        d.setDroneState(DroneState.LOADING);

        updateDroneStateUseCase.execute(d);

        assertEquals(DroneState.LOADING, getDroneBySerialNumberUseCase.execute(serialNumber).getDroneState());
    }

    @Test
    @Order(5)
    void executeUpdateNotExistsDroneStateThrowsNotFoundException() {
        final String serialNumber = "DRN-001";

        final Drone d = getDroneBySerialNumberUseCase.execute(serialNumber);
        d.setDroneState(DroneState.LOADING);
        d.setSerialNumber("Not exists");

        assertThrows(NotFoundException.class, () -> updateDroneStateUseCase.execute(d));
    }
}
