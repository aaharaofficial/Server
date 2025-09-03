package com.atman.aahara.Recipe.Ingredients;


import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;


public class IngredientEntityListener {
    @PostPersist
    @PostRemove
    @PostUpdate
    public void recalculateRecipePrice(Ingredient ingredient) {
        IngredientEntityListenerHelper.recalculateRecipePrice(ingredient);
    }
}
