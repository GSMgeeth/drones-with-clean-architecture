package com.company.drones.infrastructure.repository.medication;

import com.company.drones.domain.entity.exception.InvalidIdentityException;
import com.company.drones.domain.entity.exception.InvalidClassAttributeException;
import com.company.drones.domain.repository.medication.IMedicationRepository;
import com.company.drones.domain.entity.medication.Medication;
import com.company.drones.infrastructure.database.medication.JPAMedicationRepository;
import com.company.drones.infrastructure.database.medication.MedicationORM;

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
