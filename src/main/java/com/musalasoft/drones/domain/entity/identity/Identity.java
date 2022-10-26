package com.musalasoft.drones.domain.entity.identity;

import com.musalasoft.drones.domain.entity.exception.InvalidIdentityException;

/**
 * TODO : Identity class should implement an interface.
 */
public class Identity {
    private Long id;

    public Identity(Long id) throws InvalidIdentityException {
        setId(id);
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) throws InvalidIdentityException {
        this.id = IdentityValidation.validateIdentity(id);
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
