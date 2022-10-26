package com.musalasoft.drones.infrastructure.rest.api.drone.dto;

import com.musalasoft.drones.domain.entity.drone_bucket.DroneBucket;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public record DroneBucketResponseDTO(String serialNumber, double loadedWeight, List<DroneBucketItemResponseDTO> items)
        implements Serializable {

    public static DroneBucketResponseDTO from(final DroneBucket droneBucket) {
        if (droneBucket == null) {
            return null;
        }

        AtomicReference<String> serialNumber = new AtomicReference<>("");

        Optional.ofNullable(droneBucket.getDrone()).ifPresent(drone -> serialNumber.set(drone.getSerialNumber()));

        return new DroneBucketResponseDTO(
                serialNumber.get(),
                droneBucket.getTotalWeight(),
                Optional.ofNullable(droneBucket.getDroneBucketItems()).orElse(Collections.emptyList()).stream()
                        .map(DroneBucketItemResponseDTO::from).toList());
    }
}
