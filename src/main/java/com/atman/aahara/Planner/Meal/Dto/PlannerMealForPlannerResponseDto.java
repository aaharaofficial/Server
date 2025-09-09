package com.atman.aahara.Planner.Meal.Dto;

import com.atman.aahara.Planner.Enum.DeliverySession;
import com.atman.aahara.Recipe.Enum.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PlannerMealForPlannerResponseDto {
    private UUID id;
    private String name;
    private BigDecimal price;
    private MealType mealType;
    private Integer servings;
    private DeliverySession deliverySession;
}
