package com.company.drones.domain.repository.medication;

import com.company.drones.domain.entity.medication.Medication;

public interface IMedicationRepository {

    /**
     * Gets a medication by code.
     *
     * @param code Unique code of the medication.
     * @return Medication if found by the code. If not found, returns null.
     */
    Medication getMedicationByCode(final String code);

    /**
     * Persists a medication in a data store.
     *
     * @param drone The medication object to be saved.
     * @return Saved version of the medication object.
     */
    Medication persistMedication(final Medication drone);
}
