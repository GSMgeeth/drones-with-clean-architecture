package com.musalasoft.drones.domain.usecase.medication;

import com.musalasoft.drones.domain.entity.medication.Medication;
import com.musalasoft.drones.domain.repository.medication.IMedicationRepository;
import com.musalasoft.drones.domain.usecase.IUseCase;
import com.musalasoft.drones.domain.usecase.exception.AlreadyExistsException;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class RegisterMedicationUseCase implements IUseCase<Medication, Medication> {
    private static final Logger logger = Logger.getLogger("Register Medication Use Case Logger");

    private final IMedicationRepository medicationRepository;

    public RegisterMedicationUseCase(IMedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    public Medication execute(final Medication medication) throws AlreadyExistsException, NullPointerException {
        if (medicationRepository.getMedicationByCode(Objects.requireNonNull(medication).getCode()) != null) {
            final String message = format("Medication by the code '%s' already exists.", medication.getCode());

            logger.log(Level.WARNING, () -> message);
            throw new AlreadyExistsException(message);
        }

        return medicationRepository.persistMedication(medication);
    }
}
