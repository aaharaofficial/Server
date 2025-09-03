package com.atman.aahara.Recipe.AlternativeIngredients;

import com.atman.aahara.Recipe.Ingredients.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlternativeIngredientRepository extends JpaRepository<AlternativeIngredient, UUID> {
    List<AlternativeIngredient> findAllByIngredientId(UUID ingredientId);
}
