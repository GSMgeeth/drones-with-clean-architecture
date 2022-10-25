package com.musalasoft.drones.infrastructure.database.drone_bucket;

import com.musalasoft.drones.infrastructure.database.drone.DroneORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPADroneBucketRepository extends JpaRepository<DroneBucketItemORM, Long> {

    List<DroneBucketItemORM> findAllByDroneORM(DroneORM droneORM);
}
