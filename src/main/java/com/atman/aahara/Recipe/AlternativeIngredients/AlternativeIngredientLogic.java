package com.atman.aahara.Recipe.AlternativeIngredients;

import com.atman.aahara.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlternativeIngredientLogic implements AlternativeIngredientService {

    private final AlternativeIngredientRepository alternativeIngredientRepository;

    @Override
    public AlternativeIngredient saveAlternativeIngredient(AlternativeIngredient alternativeIngredient){
        return alternativeIngredientRepository.save(alternativeIngredient);
    }


    @Override
    public AlternativeIngredient getAlternativeIngredient(UUID ingredientId){
        return alternativeIngredientRepository.findById(ingredientId).orElseThrow(() -> new ResourceNotFoundException("AlternativeIngredient not found " + ingredientId));
    }

    @Override
    public List<AlternativeIngredient> getAlternativeIngredientForIngredient(UUID ingredientId){
        return alternativeIngredientRepository.findAllByIngredientId(ingredientId);
    }

    @Override
    public void deleteAlternativeIngredient(UUID id){
        if(alternativeIngredientRepository.existsById(id)){
            alternativeIngredientRepository.deleteById(id);
        }
    }

    @Override
    public void deleteAlternativeIngredient(AlternativeIngredient ingredient){
        alternativeIngredientRepository.delete(ingredient);
    }
}
