package com.musalasoft.drones.domain.entity.identity;

import com.musalasoft.drones.domain.entity.Validation;
import com.musalasoft.drones.domain.entity.exception.InvalidIdentityException;

import java.util.Objects;

public class IdentityValidation implements Validation {

    public static Long validateIdentity(final Long identity) throws InvalidIdentityException {
        if (identity == null || identity <= 0) {
            throw new InvalidIdentityException("Invalid drone identity.");
        }

        return identity;
    }

    @Override
    public <T> void validate(T object) throws InvalidIdentityException, NullPointerException {
        final Identity identity = (Identity) Objects.requireNonNull(object, "Expected not null Identity, provided null.");

        validateIdentity(identity.getId());
    }
}
