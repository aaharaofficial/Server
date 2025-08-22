package com.atman.aahara.Customer.Credentials.Dto;

import com.atman.aahara.Customer.Enum.Gender;
import com.atman.aahara.Customer.Enum.RelationShipStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerCredentialResponse {
    private UUID id;
    private String name;
    private String email;
    private String mobileNumber;
    private LocalDate birthDate;
    private Float age;
    private Gender gender;
    private RelationShipStatus relationShipStatus;
}
