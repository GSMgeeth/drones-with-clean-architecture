package com.musalasoft.drones.infrastructure.repository.identity;

import com.musalasoft.drones.domain.identity.Identity;
import com.musalasoft.drones.domain.identity.IdentityValidation;

public class IdentityEntity {

    private IdentityEntity() {
    }

    public static Long from(final Identity identity) throws NullPointerException {
        new IdentityValidation().validate(identity);

        return identity.getId();
    }
}
