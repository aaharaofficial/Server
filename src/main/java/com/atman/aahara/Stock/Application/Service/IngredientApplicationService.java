package com.atman.aahara.Stock.Application.Service;

import com.atman.aahara.Stock.Domain.Model.Ingredient;
import com.atman.aahara.Stock.Port.In.IngredientUseCasePort;
import com.atman.aahara.Stock.Port.Out.IngredientRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IngredientApplicationService implements IngredientUseCasePort {

    private final IngredientRepositoryPort ingredientRepositoryPort;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return ingredientRepositoryPort.save(ingredient);
    }

    @Override
    public void removeIngredient(UUID ingredientID) {
        if (ingredientRepositoryPort.findById(ingredientID).isEmpty()) {
            throw new IllegalArgumentException("Ingredient not found: " + ingredientID);
        }
        ingredientRepositoryPort.deleteById(ingredientID);
    }

    @Override
    public Ingredient updateIngredient(UUID ingredientID, Ingredient ingredient) {
        if (ingredientRepositoryPort.findById(ingredientID).isEmpty()) {
            throw new IllegalArgumentException("Cannot update non-existing ingredient: " + ingredientID);
        }
        ingredient.setId(ingredientID); // ensure correct ID is used
        return ingredientRepositoryPort.save(ingredient);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepositoryPort.findAll();
    }

    @Override
    public Ingredient getIngredientByID(UUID ingredientID) {
        return ingredientRepositoryPort.findById(ingredientID)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient not found: " + ingredientID));
    }
}
