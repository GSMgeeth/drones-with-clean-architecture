package com.musalasoft.drones.infrastructure.database.drone;

import com.musalasoft.drones.domain.entity.drone.Drone;
import com.musalasoft.drones.domain.entity.drone.DroneModel;
import com.musalasoft.drones.domain.entity.drone.DroneState;
import com.musalasoft.drones.domain.entity.drone.DroneValidation;
import com.musalasoft.drones.domain.entity.exception.InvalidClassAttributeException;
import com.musalasoft.drones.domain.entity.exception.InvalidIdentityException;
import com.musalasoft.drones.infrastructure.database.identity.IdentityEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "drone")
public class DroneORM {
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

    @Column(name = "active", nullable = false)
    private boolean active;

    public DroneORM() {
    }

    public DroneORM(Long id, String serialNumber) {
        setId(id);
        setSerialNumber(serialNumber);
    }

    public DroneORM(Long id, String serialNumber, DroneModel droneModel) {
        setId(id);
        setSerialNumber(serialNumber);
        setDroneModel(droneModel);
    }

    public DroneORM(Long id, String serialNumber, DroneModel droneModel, DroneState droneState) {
        setId(id);
        setSerialNumber(serialNumber);
        setDroneModel(droneModel);
        setDroneState(droneState);
    }

    public DroneORM(Long id, String serialNumber, DroneModel droneModel, DroneState droneState, boolean active) {
        setId(id);
        setSerialNumber(serialNumber);
        setDroneModel(droneModel);
        setDroneState(droneState);
        setActive(active);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setDroneModel(String droneModel) {
        this.droneModel = droneModel;
    }

    public String getDroneState() {
        return droneState;
    }

    public void setDroneState(DroneState droneState) {
        this.droneState = droneState.getValue();
    }

    public void setDroneState(String droneState) {
        this.droneState = droneState;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static DroneORM from(final Drone drone) throws InvalidClassAttributeException, InvalidIdentityException, NullPointerException {
        new DroneValidation().validate(drone);

        return new DroneORM(
                IdentityEntity.from(drone.getIdentity()),
                drone.getSerialNumber(),
                drone.getDroneModel(),
                drone.getDroneState(),
                drone.isActive());
    }

    public static Drone to(final DroneORM droneORM) throws InvalidClassAttributeException, InvalidIdentityException, EnumConstantNotPresentException {
        return new Drone(
                droneORM.getId(),
                droneORM.getSerialNumber(),
                DroneModel.getKey(droneORM.getDroneModel()),
                DroneState.getKey(droneORM.getDroneState()),
                droneORM.isActive());
    }

    public Drone toDrone() throws InvalidClassAttributeException, InvalidIdentityException, EnumConstantNotPresentException {
        return to(this);
    }
}
