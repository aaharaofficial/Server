package com.atman.aahara.Recipe.Ingredients;


import com.atman.aahara.Recipe.Base.Recipe;
import com.atman.aahara.Recipe.Base.RecipeService;
import com.atman.aahara.Recipe.Calculations.PriceCalculator;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IngredientEntityListenerHelper {

    private static PriceCalculator priceCalculator;
    private static IngredientService ingredientService;
    private static RecipeService recipeService;

    public static void recalculateRecipePrice(Ingredient ingredient) {
        UUID recipeId = ingredient.getInstruction().getRecipe().getId();
        List<Ingredient> allIngredientByRecipeId = ingredientService.getAllIngredientByRecipeId(recipeId);
        BigDecimal bigDecimal = priceCalculator.calculateListOfIngredientPrice(allIngredientByRecipeId);
        Recipe recipe = recipeService.getRecipe(recipeId);
        recipe.setRawPrice(bigDecimal);
        recipeService.saveRecipe(recipe);
    }
}
