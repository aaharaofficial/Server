package com.atman.aahara.Recipe.Ingredients;

import com.atman.aahara.Inventory.Inventory;
import com.atman.aahara.Inventory.InventoryService;
import com.atman.aahara.Recipe.Mappers.IngredientMapper;
import com.atman.aahara.Recipe.Ingredients.Dto.IngredientRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IngredientApplication {

    private final IngredientService ingredientService;
    private final IngredientMapper ingredientMapper;
    private final InventoryService inventoryService;

    public Ingredient createIngredient(IngredientRequestDto ingredientRequestDto){

        Ingredient ingredient = ingredientMapper.requestToEntity(ingredientRequestDto);
        updateInventoryField(ingredientRequestDto, ingredient);
        return ingredientService.saveIngredient(ingredient);
    }

    private void updateInventoryField(IngredientRequestDto ingredientRequestDto, Ingredient ingredient) {
        Inventory inventory = inventoryService.getInventory(ingredientRequestDto.getInventoryId());
        ingredient.setInventory(inventory);;
    }

    public Ingredient updateIngredient(UUID ingredientId,IngredientRequestDto ingredientRequestDto){
        Ingredient ingredient = ingredientService.getIngredient(ingredientId);
        Ingredient mapped = ingredientMapper.mapExisting(ingredientRequestDto, ingredient);
        updateInventoryField(ingredientRequestDto, mapped);
        return ingredientService.saveIngredient(mapped);
    }

}
