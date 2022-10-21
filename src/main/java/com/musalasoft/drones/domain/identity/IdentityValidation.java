package com.musalasoft.drones.domain.identity;

import com.musalasoft.drones.domain.Validation;
import com.musalasoft.drones.domain.exception.InvalidClassAttributeException;
import com.musalasoft.drones.domain.exception.InvalidIdentityException;

import java.util.Objects;

public class IdentityValidation implements Validation {

    public static Long validateIdentity(final Long identity) throws InvalidIdentityException {
        if (identity == null || identity <= 0) {
            throw new InvalidIdentityException("Invalid drone identity.");
        }

        return identity;
    }

    @Override
    public <T> void validate(T object) throws InvalidClassAttributeException {
        final Identity identity = (Identity) Objects.requireNonNull(object, "Expected not null Identity, provided null.");

        validateIdentity(identity.getId());
    }
}
