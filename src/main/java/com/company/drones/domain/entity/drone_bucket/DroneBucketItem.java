package com.company.drones.domain.entity.drone_bucket;

import com.company.drones.domain.entity.identity.Identity;

public interface DroneBucketItem {
    Identity getIdentity();

    double getWeight();
}
