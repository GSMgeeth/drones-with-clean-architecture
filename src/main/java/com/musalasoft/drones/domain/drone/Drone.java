package com.musalasoft.drones.domain.drone;

import com.musalasoft.drones.domain.exception.InvalidClassAttributeException;
import com.musalasoft.drones.domain.exception.InvalidIdentityException;
import com.musalasoft.drones.domain.identity.Identity;
import com.musalasoft.drones.domain.identity.IdentityValidation;

public class Drone {
    private Identity identity;
    private String serialNumber;
    private DroneModel droneModel;
    private double weightLimit;
    private DroneState droneState;
    private boolean active;

    public Drone() {
        setDroneInitialValues();
    }

    public Drone(String serialNumber) throws InvalidClassAttributeException {
        setSerialNumber(serialNumber);
        setDroneInitialValues();
    }

    public Drone(Identity identity, String serialNumber) throws InvalidClassAttributeException, InvalidIdentityException, NullPointerException {
        setIdentity(identity);
        setSerialNumber(serialNumber);
        setDroneInitialValues();
    }

    public Drone(Long id, String serialNumber) throws InvalidClassAttributeException, InvalidIdentityException {
        setIdentity(id);
        setSerialNumber(serialNumber);
        setDroneInitialValues();
    }

    public Drone(Long id, String serialNumber, DroneModel droneModel, DroneState droneState) throws InvalidClassAttributeException, InvalidIdentityException {
        setIdentity(id);
        setSerialNumber(serialNumber);
        setDroneModel(droneModel);
        setDroneState(droneState);
        setDroneInitialValues();
    }

    public Drone(Long id, String serialNumber, DroneModel droneModel, DroneState droneState, boolean active) throws InvalidClassAttributeException, InvalidIdentityException {
        setIdentity(id);
        setSerialNumber(serialNumber);
        setDroneModel(droneModel);
        setDroneState(droneState);
        setActive(active);
        setDroneInitialValues();
    }

    private void setDroneInitialValues() {
        setWeightLimit(0.0);
        setActive(true);
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) throws InvalidClassAttributeException {
        this.serialNumber = DroneValidation.validateSerialNumber(serialNumber);
    }

    public DroneModel getDroneModel() {
        return droneModel;
    }

    public void setDroneModel(DroneModel droneModel) {
        this.droneModel = droneModel;

        if (droneModel != null) {
            setWeightLimit(DroneModel.getMaxWeightLimit(droneModel));
        }
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    private void setWeightLimit(double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public DroneState getDroneState() {
        return droneState;
    }

    public void setDroneState(DroneState droneState) {
        this.droneState = droneState;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
