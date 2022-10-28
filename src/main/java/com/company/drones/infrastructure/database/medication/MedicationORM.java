package com.company.drones.infrastructure.database.medication;

import com.company.drones.domain.entity.exception.InvalidIdentityException;
import com.company.drones.domain.entity.drone_bucket.DroneBucketItemValidation;
import com.company.drones.domain.entity.exception.InvalidClassAttributeException;
import com.company.drones.domain.entity.identity.IdentityValidation;
import com.company.drones.domain.entity.medication.Medication;
import com.company.drones.domain.entity.medication.MedicationValidation;
import com.company.drones.infrastructure.database.identity.IdentityEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "medication")
public class MedicationORM {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "weight")
    private double weight;

    @Column(name = "image_url")
    private String imageURL;

    public MedicationORM() {
    }

    public MedicationORM(Long id, String name, String code, double weight, String imageURL) throws InvalidIdentityException, InvalidClassAttributeException {
        setId(id);
        setName(name);
        setCode(code);
        setWeight(weight);
        setImageURL(imageURL);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) throws InvalidIdentityException {
        this.id = IdentityValidation.validateIdentity(id);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws InvalidClassAttributeException {
        this.code = MedicationValidation.validateCode(code);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidClassAttributeException {
        this.name = MedicationValidation.validateName(name);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) throws InvalidClassAttributeException {
        this.weight = DroneBucketItemValidation.validateWeight(weight);
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public static MedicationORM from(final Medication medication) throws InvalidClassAttributeException, InvalidIdentityException, NullPointerException {
        new MedicationValidation().validate(medication);

        return new MedicationORM(
                IdentityEntity.from(medication.getIdentity()),
                medication.getName(),
                medication.getCode(),
                medication.getWeight(),
                medication.getImageURL());
    }

    public static Medication to(final MedicationORM medicationORM) throws InvalidClassAttributeException, InvalidIdentityException {
        return new Medication(
                medicationORM.getId(),
                medicationORM.getName(),
                medicationORM.getCode(),
                medicationORM.getWeight(),
                medicationORM.getImageURL());
    }

    public Medication toMedication() throws InvalidClassAttributeException, InvalidIdentityException {
        return to(this);
    }
}
