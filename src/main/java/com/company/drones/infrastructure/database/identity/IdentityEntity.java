package com.company.drones.infrastructure.database.identity;

import com.company.drones.domain.entity.identity.Identity;

public class IdentityEntity {

    private IdentityEntity() {
    }

    public static Long from(final Identity identity) {
        if (identity == null) {
            return null;
        }

        return identity.getId();
    }
}
