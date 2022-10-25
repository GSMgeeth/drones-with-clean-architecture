package com.musalasoft.drones.infrastructure.database.identity;

import com.musalasoft.drones.domain.entity.identity.Identity;

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
