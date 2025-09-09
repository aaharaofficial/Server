package com.atman.aahara.Customer.Infra;

import com.atman.aahara.Customer.Enum.FamilyRole;
import com.atman.aahara.Customer.Enum.Gender;
import com.atman.aahara.Customer.Enum.MaritalStatus;
import com.atman.aahara.Planner.Family.Family;
import com.atman.aahara.Global.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {

    private String name;
    private Gender gender;
    private LocalDate birthDate;
    private Float age;

    @Column(unique = true)
    private String email;

    @Column(unique = true, nullable = false)
    private String mobileNumber;

    private MaritalStatus maritalStatus;
    private FamilyRole familyRole;

    @ManyToOne
    @JoinColumn(name = "family_id")
    @JsonBackReference
    private Family family;
}
