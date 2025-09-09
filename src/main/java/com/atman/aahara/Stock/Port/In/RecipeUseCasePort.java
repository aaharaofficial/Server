package com.atman.aahara.Stock.Port.In;

import com.atman.aahara.Stock.Domain.Model.Recipe;

import java.util.List;
import java.util.UUID;

public interface RecipeUseCasePort {
    Recipe createRecipe(Recipe recipe);
    Recipe getRecipe(UUID id);
    List<Recipe> getAllRecipes();
    Recipe updateRecipe(Recipe recipe);
    void deleteRecipe(UUID id);

}
