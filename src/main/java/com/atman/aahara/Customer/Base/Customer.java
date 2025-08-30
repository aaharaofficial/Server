package com.atman.aahara.Customer.Base;


import com.atman.aahara.Customer.Enum.Gender;
import com.atman.aahara.Customer.Enum.RelationShipStatus;
import com.atman.aahara.Family.Family;
import com.atman.aahara.Global.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.Period;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Data
@Table(name = "customer")
public class Customer extends BaseEntity {

    private String name;

    private Gender gender;

    private LocalDate birthDate;

    private Float age;

    @Column(unique = true)
    private String email;

    @Column(unique = true, nullable = false)
    private String mobileNumber;

    private RelationShipStatus relationShipStatus;

    @ManyToOne()
    @JoinColumn(name = "family_id")
    @JsonBackReference
    private Family family;

    @PrePersist
    @PreUpdate
    public void calculateAge() {
        if (birthDate != null) {
            this.age = (float) Period.between(birthDate, LocalDate.now()).getYears();
        }
    }

}
