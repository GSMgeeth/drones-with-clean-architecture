package com.company.drones.domain.usecase.medication;

import com.company.drones.domain.entity.medication.Medication;
import com.company.drones.domain.usecase.exception.AlreadyExistsException;
import com.company.drones.infrastructure.database.medication.JPAMedicationRepository;
import com.company.drones.infrastructure.repository.medication.MedicationRepository;
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
class RegisterMedicationUseCaseTest {
    private RegisterMedicationUseCase registerMedicationUseCase;

    @Autowired
    private JPAMedicationRepository jpaMedicationRepository;

    @BeforeEach
    void setUp() {
        MedicationRepository medicationRepository = new MedicationRepository(jpaMedicationRepository);
        this.registerMedicationUseCase = new RegisterMedicationUseCase(medicationRepository);
    }

    @Test
    @Order(1)
    void executeRegisterMedication() {
        final String code = "MD_001";

        final Medication medication = registerMedicationUseCase
                .execute(new Medication(1L, "medication_name-".concat(code), code, 50, null));

        assertThat(medication.getCode()).isEqualTo(code);
    }

    @Test
    @Order(2)
    void executeRegisterDuplicateMedicationThrowsAlreadyExistsException() {
        final String code = "MD_001";

        final Medication medication = new Medication(1L, "medication_name-".concat(code), code, 50, null);

        assertThrows(AlreadyExistsException.class, () -> registerMedicationUseCase.execute(medication));
    }

    @Test
    @Order(3)
    void executeRegisterNullMedicationThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> registerMedicationUseCase.execute(null));
    }
}
