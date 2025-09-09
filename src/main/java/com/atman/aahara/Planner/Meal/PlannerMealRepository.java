package com.atman.aahara.Planner.Meal;

import com.atman.aahara.Recipe.Enum.MealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlannerMealRepository extends JpaRepository<PlannerMeal, UUID> {
    List<PlannerMeal> findByMealType(MealType mealType);

    List<PlannerMeal> findByMealTypeAndPlanner_Id(MealType mealType, UUID plannerId);


}
