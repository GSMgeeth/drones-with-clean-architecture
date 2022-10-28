package com.company.drones.domain.entity;

import com.company.drones.domain.entity.exception.InvalidClassAttributeException;

public interface IValidation {

    <T> void validate(final T object) throws InvalidClassAttributeException;
}
