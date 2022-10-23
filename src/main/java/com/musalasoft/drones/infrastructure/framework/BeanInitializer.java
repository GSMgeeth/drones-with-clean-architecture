package com.musalasoft.drones.infrastructure.framework;

import com.musalasoft.drones.domain.repository.drone.IDroneRepository;
import com.musalasoft.drones.domain.repository.medication.IMedicationRepository;
import com.musalasoft.drones.domain.usecase.IDroneAPI;
import com.musalasoft.drones.domain.usecase.drone.GetDroneBySerialNumberUseCase;
import com.musalasoft.drones.domain.usecase.drone.LogDroneStatusUseCase;
import com.musalasoft.drones.domain.usecase.drone.RegisterDroneUseCase;
import com.musalasoft.drones.domain.usecase.drone.UpdateDroneStateUseCase;
import com.musalasoft.drones.domain.usecase.medication.RegisterMedicationUseCase;
import com.musalasoft.drones.infrastructure.database.drone.JPADroneRepository;
import com.musalasoft.drones.infrastructure.database.medication.JPAMedicationRepository;
import com.musalasoft.drones.infrastructure.repository.drone.DroneRepository;
import com.musalasoft.drones.infrastructure.repository.medication.MedicationRepository;
import com.musalasoft.drones.infrastructure.rest.api.drone.DroneController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanInitializer {

    @Bean
    public DroneRepository droneRepository(JPADroneRepository jpaDroneRepository) {
        return new DroneRepository(jpaDroneRepository);
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
    public UpdateDroneStateUseCase updateDroneStateUseCase(IDroneRepository droneRepository) {
        return new UpdateDroneStateUseCase(droneRepository);
    }

    @Bean
    public RegisterMedicationUseCase registerMedicationUseCase(IMedicationRepository medicationRepository) {
        return new RegisterMedicationUseCase(medicationRepository);
    }

    @Bean
    public DroneController droneController(RegisterDroneUseCase registerDroneUseCase) {
        return new DroneController(registerDroneUseCase);
    }
}
