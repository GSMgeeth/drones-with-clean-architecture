package com.musalasoft.drones.domain.entity;

import com.musalasoft.drones.domain.entity.exception.InvalidClassAttributeException;

public interface Validation {

    <T> void validate(final T object) throws InvalidClassAttributeException;
}
