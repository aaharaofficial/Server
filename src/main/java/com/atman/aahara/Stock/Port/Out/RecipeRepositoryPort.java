package com.atman.aahara.Stock.Port.Out;

import com.atman.aahara.Stock.Domain.Model.Recipe;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecipeRepositoryPort {
    Recipe save(Recipe recipe);
    Optional<Recipe> findById(UUID id);
    List<Recipe> findAll();
    void deleteById(UUID id);
}
