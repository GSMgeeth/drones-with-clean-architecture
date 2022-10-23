package com.musalasoft.drones.infrastructure.repository.identity;

import com.musalasoft.drones.domain.entity.identity.Identity;
import com.musalasoft.drones.domain.entity.identity.IdentityValidation;

public class IdentityEntity {

    private IdentityEntity() {
    }

    public static Long from(final Identity identity) throws NullPointerException {
        new IdentityValidation().validate(identity);

        return identity.getId();
    }
}
