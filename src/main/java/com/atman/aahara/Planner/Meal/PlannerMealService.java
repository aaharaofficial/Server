package com.atman.aahara.Planner.Meal;

import com.atman.aahara.Recipe.Enum.MealType;

import java.util.List;
import java.util.UUID;

public interface PlannerMealService {
    // Save or update
    PlannerMeal savePlannerMeal(PlannerMeal plannerMeal);

    // Find by ID
    PlannerMeal findById(UUID id);

    // Find all
    List<PlannerMeal> findAll();

    // Delete by ID
    void deleteById(UUID id);

    // Delete entity directly
    void delete(PlannerMeal plannerMeal);

    // Find by MealType
    List<PlannerMeal> findByMealType(MealType mealType);

    // Find by MealType and PlannerId
    List<PlannerMeal> findByMealTypeAndPlannerId(MealType mealType, UUID plannerId);
}
