package com.atman.aahara.Customer.Domain;


import com.atman.aahara.Customer.Enum.FamilyRole;
import com.atman.aahara.Customer.Enum.Gender;
import com.atman.aahara.Customer.Enum.MaritalStatus;
import com.atman.aahara.Planner.Family.Family;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Customer {
    private UUID id;
    private String name;
    private Gender gender;
    private LocalDate birthDate;
    private Float age;
    private String email;
    private String mobileNumber;
    private MaritalStatus maritalStatus;
    private FamilyRole familyRole;
    private Family family;

    // Domain logic: age calculation
    public void calculateAge() {
        if (birthDate != null) {
            this.age = (float) Period.between(birthDate, LocalDate.now()).getYears();
        }
    }

    // Example of additional domain rule
    public boolean isAdult() {
        calculateAge();
        return age != null && age >= 18;
    }
}
