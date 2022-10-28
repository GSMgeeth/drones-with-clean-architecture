package com.company.drones.domain.usecase.medication;

import com.company.drones.domain.entity.medication.Medication;
import com.company.drones.domain.repository.medication.IMedicationRepository;
import com.company.drones.domain.usecase.IUseCase;

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
