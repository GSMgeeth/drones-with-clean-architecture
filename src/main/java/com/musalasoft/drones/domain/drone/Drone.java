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
    private double batteryCapacity;
    private DroneState droneState;

    public Drone() {
        setWeightLimit(0.0);
        setBatteryCapacity(0.0);
    }

    public Drone(String serialNumber) throws InvalidClassAttributeException {
        setSerialNumber(serialNumber);
        setWeightLimit(0.0);
        setBatteryCapacity(0.0);
    }

    public Drone(Identity identity, String serialNumber) throws InvalidClassAttributeException, InvalidIdentityException {
        setIdentity(identity);
        setSerialNumber(serialNumber);
        setWeightLimit(0.0);
        setBatteryCapacity(0.0);
    }

    public Drone(Long id, String serialNumber) throws InvalidClassAttributeException, InvalidIdentityException {
        setIdentity(id);
        setSerialNumber(serialNumber);
        setWeightLimit(0.0);
        setBatteryCapacity(0.0);
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) throws InvalidIdentityException {
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

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    private void setBatteryCapacity(double batteryCapacity) throws InvalidClassAttributeException {
        this.batteryCapacity = DroneValidation.validateBatteryCapacity(batteryCapacity);
    }

    public DroneState getDroneState() {
        return droneState;
    }

    public void setDroneState(DroneState droneState) {
        this.droneState = droneState;
    }

    // TODO Mark as a scheduler
    void checkBatteryLevelScheduler(final IDroneAPI droneAPI) {
        final double batteryPercentage = droneAPI.getBatteryLevelAsPercentage(getSerialNumber());

        setBatteryCapacity(batteryPercentage);

        // Logs the battery capacity to the console.
    }
}
