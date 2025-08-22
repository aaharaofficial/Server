package com.atman.aahara.Customer.Credentials.Dto;

import com.atman.aahara.Customer.Enum.Gender;
import com.atman.aahara.Customer.Enum.RelationShipStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerCredentialRequest {
    @NotNull(message = "name must not be null ")
    private String name;
    @NotNull(message = "mobileNumber must not be null ")
    private String mobileNumber;
    @NotNull(message = "email must not be null ")
    private String email;
    @NotNull(message = "age must not be null ")
    private LocalDate birthDate;
    @NotNull(message = "gender must not be null ")
    private Gender gender;
    @NotNull(message = "relationshipStatus must not be null ")
    private RelationShipStatus relationShipStatus;
}
