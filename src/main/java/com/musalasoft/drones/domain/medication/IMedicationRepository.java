package com.musalasoft.drones.domain.medication;

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
