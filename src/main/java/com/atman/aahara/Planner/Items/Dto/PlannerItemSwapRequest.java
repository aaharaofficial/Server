package com.atman.aahara.Planner.Items.Dto;

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
public class PlannerItemSwapRequest {
    private UUID id;
    private LocalDate toDate;
    private UUID familyId;
}
