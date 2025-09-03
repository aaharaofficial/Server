package com.atman.aahara.Recipe.AlternativeIngredients;

import java.util.List;
import java.util.UUID;

public interface AlternativeIngredientService {

    AlternativeIngredient saveAlternativeIngredient(AlternativeIngredient alternativeIngredient);

    AlternativeIngredient getAlternativeIngredient(UUID ingredientId);

    List<AlternativeIngredient> getAlternativeIngredientForIngredient(UUID ingredientId);

    void deleteAlternativeIngredient(UUID id);

    void deleteAlternativeIngredient(AlternativeIngredient ingredient);
}
