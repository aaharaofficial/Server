package com.atman.aahara.Planner.Planner.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PlannerResponseDto {
    private UUID familyId;
    private UUID id;
    private LocalDate date;
    private DayOfWeek day;
    private BigDecimal price;
}
