package com.musalasoft.drones.infrastructure.repository.medication;

import com.musalasoft.drones.domain.entity.exception.InvalidClassAttributeException;
import com.musalasoft.drones.domain.entity.exception.InvalidIdentityException;
import com.musalasoft.drones.domain.entity.medication.IMedicationRepository;
import com.musalasoft.drones.domain.entity.medication.Medication;
import org.springframework.stereotype.Component;

@Component
public class MedicationRepository implements IMedicationRepository {
    private final JPAMedicationRepository jpaMedicationRepository;

    public MedicationRepository(JPAMedicationRepository jpaMedicationRepository) {
        this.jpaMedicationRepository = jpaMedicationRepository;
    }

    @Override
    public Medication getMedicationByCode(final String code) throws InvalidClassAttributeException, InvalidIdentityException {
        return jpaMedicationRepository
                .findOneByCode(code)
                .map(MedicationORM::toMedication)
                .orElse(null);
    }

    @Override
    public Medication persistMedication(final Medication drone) throws InvalidClassAttributeException, InvalidIdentityException, NullPointerException {
        return jpaMedicationRepository
                .save(MedicationORM.from(drone))
                .toMedication();
    }
}
