package com.company.drones.infrastructure.database.drone_bucket;

import com.company.drones.domain.entity.exception.InvalidIdentityException;
import com.company.drones.domain.entity.medication.Medication;
import com.company.drones.infrastructure.database.drone.DroneORM;
import com.company.drones.domain.entity.drone_bucket.DroneBucket;
import com.company.drones.domain.entity.drone_bucket.DroneBucketItem;
import com.company.drones.domain.entity.drone_bucket.DroneBucketItemValidation;
import com.company.drones.domain.entity.drone_bucket.DroneBucketValidation;
import com.company.drones.domain.entity.exception.InvalidClassAttributeException;
import com.company.drones.infrastructure.database.identity.IdentityEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "drone_bucket_item")
public class DroneBucketItemORM {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "drone_orm_id")
    private DroneORM droneORM;

    public DroneBucketItemORM() {
    }

    public DroneBucketItemORM(Long id) {
        setId(id);
    }

    public DroneBucketItemORM(Long id, DroneORM droneORM) {
        this(id);
        setDroneORM(droneORM);
    }

    public DroneBucketItemORM(Long id, double weight, String type, DroneORM droneORM) {
        this(id, droneORM);
        setWeight(weight);
        setType(type);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = DroneBucketItemValidation.validateWeight(weight);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DroneORM getDroneORM() {
        return droneORM;
    }

    public void setDroneORM(DroneORM droneORM) throws NullPointerException {
        this.droneORM = Objects.requireNonNull(droneORM);
    }

    public static List<DroneBucketItemORM> from(final DroneBucket droneBucket) throws InvalidClassAttributeException, InvalidIdentityException, NullPointerException {
        new DroneBucketValidation().validate(droneBucket);

        if (droneBucket.getDroneBucketItems().isEmpty()) {
            throw new InvalidClassAttributeException("Drone bucket items cannot be empty for drone bucket item ORM.");
        }

        return droneBucket.getDroneBucketItems().stream()
                .map(droneBucketItem -> new DroneBucketItemORM(
                        IdentityEntity.from(droneBucketItem.getIdentity()),
                        droneBucketItem.getWeight(),
                        droneBucketItem.getClass().getName(),
                        new DroneORM(droneBucket.getDrone().getIdentity().getId())))
                .toList();
    }

    public static DroneBucket to(final List<DroneBucketItemORM> bucketItemORMS) throws InvalidClassAttributeException, InvalidIdentityException {
        final DroneBucket droneBucket = new DroneBucket();

        if (bucketItemORMS.isEmpty()) {
            return droneBucket;
        }

        droneBucket.setDrone(bucketItemORMS.get(0).droneORM.toDrone());

        // Medication specific mapper. This needs to be more generic for DroneBucketItem types.
        droneBucket.addItemsToDroneBucket(
                bucketItemORMS.stream()
                        .filter(droneBucketItemORM -> droneBucketItemORM.type.equals(Medication.class.getName()))
                        .map(droneBucketItemORM ->
                                (DroneBucketItem) new Medication(droneBucketItemORM.getId(), droneBucketItemORM.getWeight()))
                        .toList());

        return droneBucket;
    }

    public static DroneBucket to(final DroneBucketItemORM bucketItemORM) throws InvalidClassAttributeException, InvalidIdentityException {
        final DroneBucket droneBucket = new DroneBucket();
        droneBucket.setDrone(bucketItemORM.droneORM.toDrone());

        // Medication specific mapper. This needs to be more generic for DroneBucketItem types.
        if (bucketItemORM.type.equals(Medication.class.getName())) {
            droneBucket.addItemToDroneBucket(new Medication(bucketItemORM.getId(), bucketItemORM.getWeight()));
        }

        return droneBucket;
    }

    public DroneBucket toDroneBucket() throws InvalidClassAttributeException, InvalidIdentityException, EnumConstantNotPresentException {
        return to(this);
    }
}
