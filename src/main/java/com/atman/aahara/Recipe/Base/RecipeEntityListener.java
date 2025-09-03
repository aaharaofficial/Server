package com.atman.aahara.Recipe.Base;

import com.atman.aahara.Recipe.Ingredients.Ingredient;
import com.atman.aahara.Recipe.Ingredients.IngredientEntityListenerHelper;
import jakarta.persistence.*;

public class RecipeEntityListener {

    @PreUpdate
    @PrePersist
    public void recalculateRecipePrice(Recipe recipe) {
        RecipeEntityListenerHelper.recalculateTotalPriceForRecipe(recipe);
    }
}
