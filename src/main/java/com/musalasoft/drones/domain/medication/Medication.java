package com.musalasoft.drones.domain.medication;

import com.musalasoft.drones.domain.exception.InvalidClassAttributeException;
import com.musalasoft.drones.domain.exception.InvalidIdentityException;
import com.musalasoft.drones.domain.identity.Identity;
import com.musalasoft.drones.domain.identity.IdentityValidation;

public class Medication {
    private Identity identity;
    private String name;
    private String code;
    private double weight;
    private String image;

    public Medication() {
        setMedicationInitialValues();
    }

    public Medication(Identity identity) throws InvalidIdentityException, NullPointerException {
        setIdentity(identity);
    }

    public Medication(Long id) throws InvalidIdentityException {
        setIdentity(id);
    }

    public Medication(Identity identity, String name) throws InvalidIdentityException, InvalidClassAttributeException, NullPointerException {
        setIdentity(identity);
        setName(name);
    }

    public Medication(Identity identity, String name, String code) throws InvalidIdentityException, InvalidClassAttributeException, NullPointerException {
        setIdentity(identity);
        setName(name);
        setCode(code);
    }

    private void setMedicationInitialValues() {
        setWeight(0.0);
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) throws InvalidIdentityException, NullPointerException {
        new IdentityValidation().validate(identity);

        this.identity = identity;
    }

    public void setIdentity(Long id) throws InvalidIdentityException {
        this.identity = new Identity(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidClassAttributeException {
        this.name = MedicationValidation.validateName(name);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws InvalidClassAttributeException {
        this.code = MedicationValidation.validateCode(code);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) throws InvalidClassAttributeException {
        this.weight = MedicationValidation.validateWeight(weight);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
