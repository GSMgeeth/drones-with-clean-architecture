package com.company.drones.domain.entity.drone;

import java.util.Objects;

public enum DroneModel {
    LIGHTWEIGHT(DroneModelValue.LIGHTWEIGHT_VALUE),
    MIDDLEWEIGHT(DroneModelValue.MIDDLEWEIGHT_VALUE),
    CRUISER_WEIGHT(DroneModelValue.CRUISER_WEIGHT_VALUE),
    HEAVYWEIGHT(DroneModelValue.HEAVYWEIGHT_VALUE);

    private final String value;

    DroneModel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DroneModel getKey(final String value) throws EnumConstantNotPresentException, NullPointerException {
        final String nonNullValue = Objects.requireNonNull(value);
        final String titleCasedValue = nonNullValue.substring(0, 1).toUpperCase().concat(nonNullValue.substring(1));

        switch (titleCasedValue) {
            case DroneModelValue.HEAVYWEIGHT_VALUE -> {
                return HEAVYWEIGHT;
            }
            case DroneModelValue.CRUISER_WEIGHT_VALUE -> {
                return CRUISER_WEIGHT;
            }
            case DroneModelValue.MIDDLEWEIGHT_VALUE -> {
                return MIDDLEWEIGHT;
            }
            case DroneModelValue.LIGHTWEIGHT_VALUE -> {
                return LIGHTWEIGHT;
            }
            default -> throw new EnumConstantNotPresentException(DroneModel.class, nonNullValue);
        }
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

    private static class DroneModelValue {
        private static final String LIGHTWEIGHT_VALUE = "Lightweight";
        private static final String MIDDLEWEIGHT_VALUE = "Middleweight";
        private static final String CRUISER_WEIGHT_VALUE = "Cruise weight";
        private static final String HEAVYWEIGHT_VALUE = "Heavyweight";
    }
}
