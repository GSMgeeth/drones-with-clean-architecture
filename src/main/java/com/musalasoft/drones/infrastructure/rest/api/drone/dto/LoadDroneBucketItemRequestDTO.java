package com.musalasoft.drones.infrastructure.rest.api.drone.dto;

import com.musalasoft.drones.domain.entity.drone_bucket.DroneBucketItem;
import com.musalasoft.drones.domain.entity.exception.InvalidClassAttributeException;
import com.musalasoft.drones.domain.entity.exception.InvalidIdentityException;
import com.musalasoft.drones.domain.entity.medication.Medication;

import java.io.Serializable;

public record LoadDroneBucketItemRequestDTO(Long id, String type) implements Serializable {

    private static DroneBucketItem to(final LoadDroneBucketItemRequestDTO requestDTO) throws InvalidClassAttributeException, InvalidIdentityException {
        if (requestDTO.type.equals(Medication.class.getName())) {
            return new Medication(requestDTO.id);
        }

        return null;
    }

    public DroneBucketItem toDroneBucketItem() throws InvalidClassAttributeException, InvalidIdentityException {
        return to(this);
    }
}
