package com.musalasoft.drones.infrastructure.framework;

import com.musalasoft.drones.domain.repository.drone.IDroneRepository;
import com.musalasoft.drones.domain.repository.drone_bucket.IDroneBucketRepository;
import com.musalasoft.drones.domain.repository.medication.IMedicationRepository;
import com.musalasoft.drones.domain.usecase.IDroneAPI;
import com.musalasoft.drones.domain.usecase.drone.GetDroneBatteryLevelBySerialNumberUseCase;
import com.musalasoft.drones.domain.usecase.drone.GetDroneBySerialNumberUseCase;
import com.musalasoft.drones.domain.usecase.drone.GetDronesByStateUseCase;
import com.musalasoft.drones.domain.usecase.drone.LogDroneStatusUseCase;
import com.musalasoft.drones.domain.usecase.drone.RegisterDroneUseCase;
import com.musalasoft.drones.domain.usecase.drone.UpdateDroneStateUseCase;
import com.musalasoft.drones.domain.usecase.drone_bucket.GetDroneBucketByDroneUseCase;
import com.musalasoft.drones.domain.usecase.drone_bucket.LoadDroneBucketWithItemsUseCase;
import com.musalasoft.drones.domain.usecase.medication.GetMedicationByCodeUseCase;
import com.musalasoft.drones.domain.usecase.medication.RegisterMedicationUseCase;
import com.musalasoft.drones.infrastructure.database.drone.JPADroneRepository;
import com.musalasoft.drones.infrastructure.database.drone_bucket.JPADroneBucketRepository;
import com.musalasoft.drones.infrastructure.database.medication.JPAMedicationRepository;
import com.musalasoft.drones.infrastructure.repository.drone.DroneRepository;
import com.musalasoft.drones.infrastructure.repository.drone_bucket.DroneBucketRepository;
import com.musalasoft.drones.infrastructure.repository.medication.MedicationRepository;
import com.musalasoft.drones.infrastructure.rest.api.drone.GetDroneController;
import com.musalasoft.drones.infrastructure.rest.api.drone.LoadDroneController;
import com.musalasoft.drones.infrastructure.rest.api.drone.RegisterDroneController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanInitializer {

    @Bean
    public DroneRepository droneRepository(JPADroneRepository jpaDroneRepository) {
        return new DroneRepository(jpaDroneRepository);
    }

    @Bean
    public DroneBucketRepository droneBucketRepository(JPADroneBucketRepository jpaDroneBucketRepository) {
        return new DroneBucketRepository(jpaDroneBucketRepository);
    }

    @Bean
    public MedicationRepository medicationRepository(JPAMedicationRepository jpaMedicationRepository) {
        return new MedicationRepository(jpaMedicationRepository);
    }

    @Bean
    public LogDroneStatusUseCase logDroneStatusUseCase(IDroneAPI droneApi, IDroneRepository droneRepository) {
        final LogDroneStatusUseCase logDroneStatusUseCase = new LogDroneStatusUseCase(droneApi, droneRepository);
        logDroneStatusUseCase.execute(null);

        return logDroneStatusUseCase;
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
    public GetDroneBatteryLevelBySerialNumberUseCase getDroneBatteryLevelBySerialNumberUseCase(IDroneRepository droneRepository, IDroneAPI droneApi) {
        return new GetDroneBatteryLevelBySerialNumberUseCase(droneRepository, droneApi);
    }

    @Bean
    public GetDronesByStateUseCase getDronesByState(IDroneRepository droneRepository) {
        return new GetDronesByStateUseCase(droneRepository);
    }

    @Bean
    public UpdateDroneStateUseCase updateDroneStateUseCase(IDroneRepository droneRepository, IDroneAPI droneApi) {
        return new UpdateDroneStateUseCase(droneRepository, droneApi);
    }

    @Bean
    public GetDroneBucketByDroneUseCase getDroneBucketByDrone(IDroneBucketRepository droneBucketRepository) {
        return new GetDroneBucketByDroneUseCase(droneBucketRepository);
    }

    @Bean
    public LoadDroneBucketWithItemsUseCase loadDroneBucketWithItemsUseCase(IDroneBucketRepository droneBucketRepository, IDroneRepository droneRepository) {
        return new LoadDroneBucketWithItemsUseCase(droneBucketRepository, droneRepository);
    }

    @Bean
    public RegisterMedicationUseCase registerMedicationUseCase(IMedicationRepository medicationRepository) {
        return new RegisterMedicationUseCase(medicationRepository);
    }

    @Bean
    public GetMedicationByCodeUseCase getMedicationByCodeUseCase(IMedicationRepository medicationRepository) {
        return new GetMedicationByCodeUseCase(medicationRepository);
    }

    @Bean
    public RegisterDroneController registerDroneController(RegisterDroneUseCase registerDroneUseCase) {
        return new RegisterDroneController(registerDroneUseCase);
    }

    @Bean
    public GetDroneController getDroneController(
            GetDroneBySerialNumberUseCase getDroneBySerialNumberUseCase,
            GetDroneBatteryLevelBySerialNumberUseCase getDroneBatteryLevelBySerialNumberUseCase,
            GetDronesByStateUseCase getDronesByStateUseCase) {
        return new GetDroneController(getDroneBySerialNumberUseCase, getDroneBatteryLevelBySerialNumberUseCase, getDronesByStateUseCase);
    }

    @Bean
    public LoadDroneController loadDroneController(
            GetDroneBucketByDroneUseCase getDroneBucketByDroneUseCase,
            LoadDroneBucketWithItemsUseCase loadDroneBucketWithItemsUseCase,
            UpdateDroneStateUseCase updateDroneStateUseCase) {
        return new LoadDroneController(getDroneBucketByDroneUseCase, loadDroneBucketWithItemsUseCase, updateDroneStateUseCase);
    }
}
