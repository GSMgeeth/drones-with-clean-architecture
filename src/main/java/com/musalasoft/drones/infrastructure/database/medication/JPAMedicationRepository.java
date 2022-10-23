package com.musalasoft.drones.infrastructure.database.medication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPAMedicationRepository extends JpaRepository<MedicationORM, Long> {

    Optional<MedicationORM> findOneByCode(String code);
}
