package com.atman.aahara.Stock.Application.Service;

import com.atman.aahara.Stock.Domain.Model.Recipe;
import com.atman.aahara.Stock.Port.In.RecipeUseCasePort;
import com.atman.aahara.Stock.Port.Out.RecipeRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecipeApplicationService implements RecipeUseCasePort {

    private final RecipeRepositoryPort recipeRepositoryPort;

    @Override
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepositoryPort.save(recipe);
    }

    @Override
    public Recipe getRecipe(UUID id) {
        return recipeRepositoryPort.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found: " + id));
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepositoryPort.findAll();
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        if (recipeRepositoryPort.findById(recipe.getId()).isEmpty()) {
            throw new IllegalArgumentException("Recipe not found:  " + recipe.getId());
        }
        return recipeRepositoryPort.save(recipe);
    }

    @Override
    public void deleteRecipe(UUID id) {
        recipeRepositoryPort.deleteById(id);
    }
}
