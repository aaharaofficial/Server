package com.atman.aahara.Recipe.Base;

import com.atman.aahara.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecipeLogic implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Override
    public Recipe getRecipe(UUID id) {
        return recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe Not Found" + id));
    }

    @Override
    public void deleteRecipe(UUID id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        recipeRepository.delete(recipe);
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> getAllRecipe(){
        return recipeRepository.findAll();
    }

    @Override
    public List<Recipe>  getRecipesForIds(List<UUID> ids){
        return recipeRepository.findAllByIdIn(ids);
    }

}
