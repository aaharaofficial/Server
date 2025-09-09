package com.atman.aahara.Planner.Planner.Dto;

import com.atman.aahara.Planner.Items.Dto.PlannerItemForPlannerResponseDto;
import com.atman.aahara.Planner.Meal.Dto.PlannerMealForPlannerResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PlannerGlobalResponseDto {
    private UUID id;
    private UUID familyId;
    private LocalDate date;
    private DayOfWeek day;
    private List<PlannerMealForPlannerResponseDto> meals;
    private List<PlannerItemForPlannerResponseDto> items;
    private BigDecimal price;
}
