package com.musalasoft.drones.infrastructure.repository.drone;

import com.musalasoft.drones.domain.drone.Drone;
import com.musalasoft.drones.domain.drone.DroneModel;
import com.musalasoft.drones.domain.drone.DroneState;
import com.musalasoft.drones.domain.drone.DroneValidation;
import com.musalasoft.drones.domain.exception.InvalidClassAttributeException;
import com.musalasoft.drones.domain.exception.InvalidIdentityException;
import com.musalasoft.drones.domain.identity.IdentityValidation;
import com.musalasoft.drones.infrastructure.repository.identity.IdentityEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
public class DroneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "serial_number", unique = true, nullable = false)
    private String serialNumber;

    @Column(name = "drone_model")
    private String droneModel;

    @Column(name = "drone_state")
    private String droneState;

    public DroneEntity() {
    }

    public DroneEntity(Long id, String serialNumber) {
        setId(id);
        setSerialNumber(serialNumber);
    }

    public DroneEntity(Long id, String serialNumber, DroneModel droneModel) {
        setId(id);
        setSerialNumber(serialNumber);
        setDroneModel(droneModel);
    }

    public DroneEntity(Long id, String serialNumber, DroneModel droneModel, DroneState droneState) {
        setId(id);
        setSerialNumber(serialNumber);
        setDroneModel(droneModel);
        setDroneState(droneState);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) throws InvalidIdentityException {
        this.id = IdentityValidation.validateIdentity(id);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) throws InvalidClassAttributeException {
        this.serialNumber = DroneValidation.validateSerialNumber(serialNumber);
    }

    public String getDroneModel() {
        return droneModel;
    }

    public void setDroneModel(DroneModel droneModel) {
        this.droneModel = droneModel.getValue();
    }

    public String getDroneState() {
        return droneState;
    }

    public void setDroneState(DroneState droneState) {
        this.droneState = droneState.getValue();
    }

    public static DroneEntity from(final Drone drone) throws NullPointerException {
        new DroneValidation().validate(drone);

        return new DroneEntity(
                IdentityEntity.from(drone.getIdentity()),
                drone.getSerialNumber(),
                drone.getDroneModel(),
                drone.getDroneState());
    }
}
