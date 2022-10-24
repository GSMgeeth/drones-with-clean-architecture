package com.musalasoft.drones.domain.repository.drone_bucket;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.drone_bucket.DroneBucket;

public interface IDroneBucketRepository {

    /**
     * Gets a drone bucket of a drone.
     *
     * @param drone Drone of the drone bucket.
     * @return DroneBucket of the drone.
     */
    DroneBucket getDroneBucketByDrone(final Drone drone);

    /**
     * Persist drone bucket items of a drone bucket.
     *
     * @param droneBucket DroneBucket with the items to be persisted.
     * @return DroneBucket with the persisted items.
     */
    DroneBucket persistDroneBucketItems(final DroneBucket droneBucket);

    /**
     * Persists a drone bucket in a data store.
     *
     * @param drone The drone bucket object to be saved.
     * @return Saved version of the drone bucket object.
     */
    DroneBucket persistDroneBucket(final DroneBucket drone);
}
