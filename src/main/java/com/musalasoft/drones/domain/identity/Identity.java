package com.musalasoft.drones.domain.identity;

import com.musalasoft.drones.domain.exception.InvalidClassAttributeException;
import com.musalasoft.drones.domain.exception.InvalidIdentityException;

/**
 * TODO : Identity class should implement an interface.
 */
public class Identity {
    private Long id;

    public Identity(Long id) throws InvalidClassAttributeException {
        setId(id);
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) throws InvalidIdentityException {
        this.id = IdentityValidation.validateIdentity(id);
    }
}
