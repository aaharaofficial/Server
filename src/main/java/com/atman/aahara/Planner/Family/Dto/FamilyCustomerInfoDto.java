package com.atman.aahara.Planner.Family.Dto;

import com.atman.aahara.Customer.Enum.FamilyRole;
import com.atman.aahara.Planner.Enum.ActiveStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FamilyCustomerInfoDto {
    private UUID customerId;
    private String customerName;
    private FamilyRole familyRole;
    private ActiveStatus activeStatus;
}
