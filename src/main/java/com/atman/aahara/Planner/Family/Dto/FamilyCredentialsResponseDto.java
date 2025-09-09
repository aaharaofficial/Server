package com.atman.aahara.Planner.Family.Dto;


import com.atman.aahara.Planner.Enum.CusineType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FamilyCredentialsResponseDto {
    private UUID id;
    private String name;
    private Integer servings;
    private CusineType cusineType;
    private List<FamilyCustomerInfoDto> familyMembers;
    private FamilyCustomerInfoDto creator;
}
