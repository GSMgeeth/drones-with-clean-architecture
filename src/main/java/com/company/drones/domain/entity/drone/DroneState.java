package com.company.drones.domain.entity.drone;

import java.util.Objects;

public enum DroneState {
    IDLE(DroneStateValue.IDLE_VALUE),
    LOADING(DroneStateValue.LOADING_VALUE),
    LOADED(DroneStateValue.LOADED_VALUE),
    DELIVERING(DroneStateValue.DELIVERING_VALUE),
    DELIVERED(DroneStateValue.DELIVERED_VALUE),
    RETURNING(DroneStateValue.RETURNING_VALUE);

    private final String value;

    DroneState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DroneState getKey(final String value) throws EnumConstantNotPresentException, NullPointerException {
        final String nonNullValue = Objects.requireNonNull(value);
        final String titleCasedValue = nonNullValue.substring(0, 1).toUpperCase().concat(nonNullValue.substring(1));

        switch (titleCasedValue) {
            case DroneStateValue.IDLE_VALUE -> {
                return IDLE;
            }
            case DroneStateValue.LOADING_VALUE -> {
                return LOADING;
            }
            case DroneStateValue.LOADED_VALUE -> {
                return LOADED;
            }
            case DroneStateValue.DELIVERING_VALUE -> {
                return DELIVERING;
            }
            case DroneStateValue.DELIVERED_VALUE -> {
                return DELIVERED;
            }
            case DroneStateValue.RETURNING_VALUE -> {
                return RETURNING;
            }
            default -> throw new EnumConstantNotPresentException(DroneState.class, nonNullValue);
        }
    }

    private static class DroneStateValue {
        private static final String IDLE_VALUE = "Idle";
        private static final String LOADING_VALUE = "Loading";
        private static final String LOADED_VALUE = "Loaded";
        private static final String DELIVERING_VALUE = "Delivering";
        private static final String DELIVERED_VALUE = "Delivered";
        private static final String RETURNING_VALUE = "Returning";
    }
}
