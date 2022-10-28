package com.company.drones.domain.entity.medication;

import com.company.drones.domain.entity.exception.InvalidClassAttributeException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MedicationTest {

    @Test
    void setName() {
        final String name = "medication_name";

        final Medication medication = new Medication();

        assertDoesNotThrow(() -> medication.setName(name));
    }

    @Test
    void setCode() {
        final String code = "MD_002";

        final Medication medication = new Medication();

        assertDoesNotThrow(() -> medication.setCode(code));
    }

    @Test
    void setInvalidCodeThrowsInvalidClassAttributeException() {
        final String code = "MD-002";

        final Medication medication = new Medication();

        assertThrows(InvalidClassAttributeException.class, () -> medication.setCode(code));
    }

    @Test
    void setInvalidNameThrowsInvalidClassAttributeException() {
        final String name = "medication name";

        final Medication medication = new Medication();

        assertThrows(InvalidClassAttributeException.class, () -> medication.setName(name));
    }
}
