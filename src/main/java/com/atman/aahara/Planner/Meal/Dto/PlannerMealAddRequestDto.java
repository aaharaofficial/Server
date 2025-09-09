package com.atman.aahara.Planner.Meal.Dto;


import com.atman.aahara.Recipe.Enum.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PlannerMealAddRequestDto {
    private UUID plannerId;
    private UUID recipeId;
    private BigDecimal servings;
    private MealType mealType;
    private LocalDate date;
}
