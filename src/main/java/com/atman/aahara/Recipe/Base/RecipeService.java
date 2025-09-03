package com.atman.aahara.Recipe.Base;

import java.util.List;
import java.util.UUID;

public interface RecipeService {

    Recipe getRecipe(UUID id);

    void deleteRecipe(UUID id);

    void deleteRecipe(Recipe recipe);

    Recipe saveRecipe(Recipe recipe);

    List<Recipe> getAllRecipe();

    List<Recipe>  getRecipesForIds(List<UUID> ids);
}
