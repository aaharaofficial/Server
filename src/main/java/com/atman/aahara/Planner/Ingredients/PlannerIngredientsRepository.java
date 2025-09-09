package com.atman.aahara.Planner.Ingredients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlannerIngredientsRepository extends JpaRepository<PlannerIngredients, UUID> {

    List<PlannerIngredients> findByMeal_Id(UUID mealId);

    List<PlannerIngredients> findByInventory_Id(UUID inventoryId);
}
