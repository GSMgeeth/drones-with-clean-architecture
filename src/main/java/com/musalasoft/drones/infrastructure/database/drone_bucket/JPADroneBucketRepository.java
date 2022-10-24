package com.musalasoft.drones.infrastructure.database.drone_bucket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPADroneBucketRepository extends JpaRepository<DroneBucketORM, Long> {
}
