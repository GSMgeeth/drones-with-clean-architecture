package com.musalasoft.drones.infrastructure.rest.api.drone.dto;

import com.musalasoft.drones.domain.entity.drone_bucket.DroneBucketItem;

import java.io.Serializable;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public record DroneBucketItemResponseDTO(String identifier, String type, double weight) implements Serializable {

    public static DroneBucketItemResponseDTO from(final DroneBucketItem bucketItem) {
        if (bucketItem == null) {
            return null;
        }

        AtomicReference<String> identifier = new AtomicReference<>("");

        Optional.ofNullable(bucketItem.getIdentity()).ifPresent(identity -> identifier.set(identity.toString()));

        return new DroneBucketItemResponseDTO(
                identifier.get(),
                bucketItem.getClass().getName(),
                bucketItem.getWeight());
    }
}
