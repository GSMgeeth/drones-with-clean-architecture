package com.musalasoft.drones.domain.usecase.medication;

import com.musalasoft.drones.domain.entity.medication.Medication;
import com.musalasoft.drones.domain.repository.medication.IMedicationRepository;
import com.musalasoft.drones.domain.usecase.IUseCase;

public class GetMedicationByCodeUseCase implements IUseCase<Medication, String> {
    private final IMedicationRepository medicationRepository;

    public GetMedicationByCodeUseCase(IMedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    public Medication execute(final String code) {
        return medicationRepository.getMedicationByCode(code);
    }
}
