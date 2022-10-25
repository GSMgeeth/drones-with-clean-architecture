package com.musalasoft.drones.domain.entity.drone_bucket;

import com.musalasoft.drones.domain.entity.identity.Identity;

public interface DroneBucketItem {
    Identity getIdentity();

    double getWeight();
}
