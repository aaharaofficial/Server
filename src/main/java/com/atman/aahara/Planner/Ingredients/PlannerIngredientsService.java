package com.atman.aahara.Planner.Ingredients;

import java.util.List;
import java.util.UUID;

public interface PlannerIngredientsService {


    PlannerIngredients save(PlannerIngredients ingredient);


    void delete(PlannerIngredients ingredient);


    void deleteById(UUID id);


    PlannerIngredients findById(UUID id);


    List<PlannerIngredients> findAll();


    List<PlannerIngredients> findAllByMealId(UUID mealId);


    List<PlannerIngredients> findAllByInventoryId(UUID inventoryId);
}
