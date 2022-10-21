package com.musalasoft.drones.domain.drone;

public enum DroneModel {
    LIGHTWEIGHT,
    MIDDLEWEIGHT,
    CRUISERWEIGHT,
    HEAVYWEIGHT;

    public static double getMaxWeightLimit(final DroneModel droneModel) {
        switch (droneModel) {
            case HEAVYWEIGHT -> {
                return 500;
            }
            case CRUISERWEIGHT -> {
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
