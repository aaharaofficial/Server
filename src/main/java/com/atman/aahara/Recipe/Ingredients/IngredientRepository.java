package com.atman.aahara.Recipe.Ingredients;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
    List<Ingredient> findAllByInstructionId(UUID instructionId);
    List<Ingredient> findAllByInstructionRecipeId(UUID recipeId);
}
