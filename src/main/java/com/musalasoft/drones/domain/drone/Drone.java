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
    private double loadedWeight;
    private double batteryCapacity;
    private DroneState droneState;

    public Drone() {
        setDroneInitialValues();
    }

    public Drone(String serialNumber) throws InvalidClassAttributeException {
        setSerialNumber(serialNumber);
        setDroneInitialValues();
    }

    public Drone(Identity identity, String serialNumber) throws InvalidClassAttributeException, InvalidIdentityException {
        setIdentity(identity);
        setSerialNumber(serialNumber);
        setDroneInitialValues();
    }

    public Drone(Long id, String serialNumber) throws InvalidClassAttributeException, InvalidIdentityException {
        setIdentity(id);
        setSerialNumber(serialNumber);
        setDroneInitialValues();
    }

    private void setDroneInitialValues() {
        setWeightLimit(0.0);
        setLoadedWeight(0.0);
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

    public double getLoadedWeight() {
        return loadedWeight;
    }

    private void setLoadedWeight(double loadedWeight) throws InvalidClassAttributeException {
        this.loadedWeight = DroneValidation.validateLoadedWeight(loadedWeight);
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
