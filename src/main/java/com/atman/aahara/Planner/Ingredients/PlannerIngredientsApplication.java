package com.atman.aahara.Planner.Ingredients;


import com.atman.aahara.Recipe.Ingredients.Ingredient;
import com.atman.aahara.Recipe.Ingredients.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlannerIngredientsApplication {

    private final IngredientService ingredientService;
    private final PlannerIngredientsMapper plannerIngredientsMapper;

    public List<PlannerIngredients> getAllIngredientsForPlannerUsingRecipeId(UUID recipeId) {
        List<Ingredient> allIngredientByRecipeId = ingredientService.getAllIngredientByRecipeId(recipeId);
        return plannerIngredientsMapper.ingredientToPlannerIngredient(allIngredientByRecipeId);
    }
}
