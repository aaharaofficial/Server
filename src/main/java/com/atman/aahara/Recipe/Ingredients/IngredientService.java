package com.atman.aahara.Recipe.Ingredients;

import java.util.List;
import java.util.UUID;

public interface IngredientService {
    Ingredient saveIngredient(Ingredient ingredient);

    Ingredient getIngredient(UUID ingredientId);

    List<Ingredient> getAllIngredient();

    List<Ingredient> getAllIngredientByInstructionId(UUID instructionId);

    List<Ingredient> getAllIngredientByRecipeId(UUID recipeId);

    void deleteIngredient(UUID id);

    void deleteIngredient(Ingredient ingredient);
}
