package com.atman.aahara.Recipe.Ingredients;

import com.atman.aahara.Exception.ResourceNotFoundException;
import com.atman.aahara.Inventory.Inventory;
import com.atman.aahara.Inventory.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IngredientLogic implements IngredientService {

    private final IngredientRepository ingredientRepository;


    @Override
    public Ingredient saveIngredient(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient getIngredient(UUID ingredientId){
        return ingredientRepository.findById(ingredientId)  .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found " + ingredientId));
    }

    @Override
    public List<Ingredient> getAllIngredient(){
        return ingredientRepository.findAll();
    }

    @Override
    public List<Ingredient> getAllIngredientByInstructionId(UUID instructionId){
        return ingredientRepository.findAllByInstructionId(instructionId);
    }

    @Override
    public List<Ingredient> getAllIngredientByRecipeId(UUID recipeId){
        return ingredientRepository.findAllByInstructionRecipeId(recipeId);
    }

    @Override
    public void deleteIngredient(UUID id){
        if(ingredientRepository.existsById(id)){
            ingredientRepository.deleteById(id);
        }
    }

    @Override
    public void deleteIngredient(Ingredient ingredient){
        ingredientRepository.delete(ingredient);
    }

}
