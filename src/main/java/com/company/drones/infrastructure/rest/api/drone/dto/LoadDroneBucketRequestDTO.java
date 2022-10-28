package com.company.drones.infrastructure.rest.api.drone.dto;

import com.company.drones.domain.entity.drone.Drone;
import com.company.drones.domain.entity.exception.InvalidIdentityException;
import com.company.drones.domain.entity.drone_bucket.DroneBucket;
import com.company.drones.domain.entity.exception.InvalidClassAttributeException;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public record LoadDroneBucketRequestDTO(String serialNumber, List<LoadDroneBucketItemRequestDTO> itemList)
        implements Serializable {

    private static DroneBucket to(final LoadDroneBucketRequestDTO requestDTO) throws InvalidClassAttributeException, InvalidIdentityException {
        final Drone drone = new Drone(requestDTO.serialNumber);

        return new DroneBucket(
                drone, requestDTO.itemList.stream()
                .filter(item -> !Objects.isNull(item))
                .map(LoadDroneBucketItemRequestDTO::toDroneBucketItem).toList());
    }

    public DroneBucket toDroneBucket() throws InvalidClassAttributeException, InvalidIdentityException {
        return to(this);
    }
}
