package com.company.drones.infrastructure.database.drone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JPADroneRepository extends JpaRepository<DroneORM, Long> {

    Optional<DroneORM> findOneBySerialNumber(String serialNumber);

    List<DroneORM> findAllByActiveIsTrueAndDroneState(String droneState);

    List<DroneORM> findAllByActiveIsTrue();
}
