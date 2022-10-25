package com.musalasoft.drones.infrastructure.repository.drone_bucket;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.drone.DroneModel;
import com.musalasoft.drones.domain.entity.drone.DroneState;
import com.musalasoft.drones.domain.entity.drone_bucket.DroneBucket;
import com.musalasoft.drones.domain.entity.drone_bucket.DroneBucketItem;
import com.musalasoft.drones.domain.entity.medication.Medication;
import com.musalasoft.drones.infrastructure.database.drone.JPADroneRepository;
import com.musalasoft.drones.infrastructure.database.drone_bucket.JPADroneBucketRepository;
import com.musalasoft.drones.infrastructure.repository.drone.DroneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DroneBucketRepositoryTest {
    private DroneBucketRepository droneBucketRepository;
    private DroneRepository droneRepository;

    @Autowired
    private JPADroneBucketRepository jpaDroneBucketRepository;

    @Autowired
    private JPADroneRepository jpaDroneRepository;

    private final DroneBucket droneBucket = new DroneBucket();

    private final List<DroneBucketItem> medications = Arrays
            .asList(new Medication(10L, 5), new Medication(20L, 7));

    private final Drone drone = new Drone("DRN-BKT", DroneModel.HEAVYWEIGHT, DroneState.LOADING, true);

    private static Long droneId;

    @BeforeEach
    void setUp() {
        droneBucketRepository = new DroneBucketRepository(jpaDroneBucketRepository);
        droneRepository = new DroneRepository(jpaDroneRepository);
    }

    void persistDrone() {
        drone.setIdentity(droneRepository.persistDrone(drone).getIdentity().getId());
        droneId = drone.getIdentity().getId();
    }

    void setUpDroneBucket() {
        droneBucket.setDrone(drone);
        droneBucket.addItemsToDroneBucket(medications);
    }

    @Test
    @Order(1)
    void persistDroneBucket() {
        persistDrone();
        setUpDroneBucket();

        final DroneBucket droneBucketSaved = droneBucketRepository.persistDroneBucket(droneBucket);

        assertThat(droneBucketSaved.getDroneBucketItems())
                .hasSameSizeAs(medications);
        assertThat(droneBucketSaved.getDrone().getIdentity().getId())
                .isEqualTo(droneId);
    }

    @Test
    @Order(2)
    void getDroneBucketByDrone() {
        drone.setIdentity(droneId);
        final DroneBucket droneBucketFetched = droneBucketRepository.getDroneBucketByDrone(drone);

        assertThat(droneBucketFetched.getDroneBucketItems())
                .hasSameSizeAs(medications);
        assertThat(droneBucketFetched.getDrone().getIdentity().getId())
                .isEqualTo(droneId);
    }
}
