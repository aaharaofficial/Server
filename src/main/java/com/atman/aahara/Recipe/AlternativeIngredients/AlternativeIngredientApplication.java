package com.atman.aahara.Recipe.AlternativeIngredients;

import com.atman.aahara.Recipe.AlternativeIngredients.Dto.AltIngredientRequestDto;
import com.atman.aahara.Recipe.Mappers.AlternativeIngredientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlternativeIngredientApplication {

    private final AlternativeIngredientService alternativeIngredientService;
    private final AlternativeIngredientMapper alternativeIngredientMapper;

    public AlternativeIngredient createIngredient(AltIngredientRequestDto ingredientRequestDto){
        AlternativeIngredient ingredient = alternativeIngredientMapper.requestToEntity(ingredientRequestDto);
        return alternativeIngredientService.saveAlternativeIngredient(ingredient);
    }

    public AlternativeIngredient updateIngredient(UUID ingredientId, AltIngredientRequestDto ingredientRequestDto){
        AlternativeIngredient ingredient = alternativeIngredientService.getAlternativeIngredient(ingredientId);
        AlternativeIngredient mapped = alternativeIngredientMapper.mapExisting(ingredientRequestDto, ingredient);
        return alternativeIngredientService.saveAlternativeIngredient(mapped);
    }

}
