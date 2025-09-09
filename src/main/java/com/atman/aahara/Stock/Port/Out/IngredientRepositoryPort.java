package com.atman.aahara.Stock.Port.Out;

import com.atman.aahara.Stock.Domain.Model.Ingredient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientRepositoryPort { 
    Ingredient save(Ingredient Ingredient);
    Optional<Ingredient> findById(UUID id);
    List<Ingredient> findAll();
    void deleteById(UUID id);
    void delete(Ingredient Ingredient);
}
