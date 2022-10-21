package com.musalasoft.drones.domain.drone;

public enum DroneState {
    IDLE("Idle"),
    LOADING("Loading"),
    LOADED("Loaded"),
    DELIVERING("Delivering"),
    DELIVERED("Delivered"),
    RETURNING("Returning");

    private final String value;

    DroneState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
