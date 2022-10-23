package com.musalasoft.drones.domain.usecase.medication;

import com.musalasoft.drones.domain.entity.medication.Medication;
import com.musalasoft.drones.infrastructure.database.medication.JPAMedicationRepository;
import com.musalasoft.drones.infrastructure.repository.medication.MedicationRepository;
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
class GetMedicationByCodeUseCaseTest {
    private GetMedicationByCodeUseCase getMedicationByCodeUseCase;
    private RegisterMedicationUseCase registerMedicationUseCase;

    @Autowired
    private JPAMedicationRepository jpaMedicationRepository;

    @BeforeEach
    void setUp() {
        MedicationRepository medicationRepository = new MedicationRepository(jpaMedicationRepository);
        this.registerMedicationUseCase = new RegisterMedicationUseCase(medicationRepository);
        this.getMedicationByCodeUseCase = new GetMedicationByCodeUseCase(medicationRepository);
    }

    @Test
    @Order(1)
    void executeGetMedicationByCode() {
        final String code = "MD_GET_MED_BY_CODE";

        registerMedicationUseCase
                .execute(new Medication(1L, "medication_name-".concat(code), code, 50, null));

        final Medication d = getMedicationByCodeUseCase.execute(code);

        assertThat(d.getCode()).isEqualTo(code);
    }

    @Test
    @Order(2)
    void executeGetNullCodeMedicationByCode() {
        assertThat(getMedicationByCodeUseCase.execute(null)).isNull();
    }

    @Test
    @Order(3)
    void executeGetNotExistsCodeMedicationByCode() {
        assertThat(getMedicationByCodeUseCase.execute("MD_GET_MED_BY_CODE_NOT_EXISTS")).isNull();
    }
}
