package com.musalasoft.drones.domain;

import com.musalasoft.drones.domain.exception.InvalidClassAttributeException;

public interface Validation {

    <T> void validate(T object) throws InvalidClassAttributeException;
}
