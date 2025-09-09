package com.atman.aahara.Stock.Port.In;

import com.atman.aahara.Stock.Domain.Model.Ingredient;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface IngredientUseCasePort {

    Ingredient addIngredient(Ingredient ingredient);

    void removeIngredient(UUID ingredientID);

    Ingredient updateIngredient(UUID ingredientID, Ingredient ingredient);

    List<Ingredient> getAllIngredients();

    Ingredient getIngredientByID(UUID ingredientID);
}
