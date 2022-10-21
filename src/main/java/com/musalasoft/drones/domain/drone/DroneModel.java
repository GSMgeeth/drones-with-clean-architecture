package com.musalasoft.drones.domain.drone;

public enum DroneModel {
    LIGHTWEIGHT("Lightweight"),
    MIDDLEWEIGHT("Middleweight"),
    CRUISER_WEIGHT("Cruise weight"),
    HEAVYWEIGHT("Heavyweight");

    private final String value;

    DroneModel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static double getMaxWeightLimit(final DroneModel droneModel) {
        switch (droneModel) {
            case HEAVYWEIGHT -> {
                return 500;
            }
            case CRUISER_WEIGHT -> {
                return 300;
            }
            case MIDDLEWEIGHT -> {
                return 200;
            }
            case LIGHTWEIGHT -> {
                return 100;
            }
            default -> {
                return 0;
            }
        }
    }
}
