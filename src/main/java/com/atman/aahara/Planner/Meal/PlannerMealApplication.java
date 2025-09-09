package com.atman.aahara.Planner.Meal;

import com.atman.aahara.Planner.Ingredients.PlannerIngredients;
import com.atman.aahara.Planner.Ingredients.PlannerIngredientsApplication;
import com.atman.aahara.Planner.Meal.Dto.PlannerMealAddRequestDto;
import com.atman.aahara.Planner.Planner.PlannerService;
import com.atman.aahara.Recipe.Base.Recipe;
import com.atman.aahara.Recipe.Base.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlannerMealApplication {

    private final PlannerMealService plannerMealService;
    private final RecipeService recipeService;
    private final PlannerIngredientsApplication plannerIngredientsApplication;

    public PlannerMeal AddMealToPlanner(PlannerMealAddRequestDto plannerMealAddRequestDto){
        Recipe recipe = recipeService.getRecipe(plannerMealAddRequestDto.getRecipeId());
        List<PlannerIngredients> allIngredientsForPlannerUsingRecipeId = plannerIngredientsApplication.getAllIngredientsForPlannerUsingRecipeId(recipe.getId());
        PlannerMeal buildedPlannerMeal = PlannerMeal.builder()
                .recipe(recipe)
                .ingredients(allIngredientsForPlannerUsingRecipeId)
                .servings(plannerMealAddRequestDto.getServings())
                .mealType(plannerMealAddRequestDto.getMealType())
                .price(recipe.getTotalPrice())
                .build();
        return plannerMealService.savePlannerMeal(buildedPlannerMeal);
    }
}
