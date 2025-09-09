package com.atman.aahara.Planner.Ingredients;


import com.atman.aahara.Recipe.Ingredients.Ingredient;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlannerIngredientsMapper {

    @Named("ingredientToPlannerIngredient")
    @Mapping(target = "meal",ignore = true)
    PlannerIngredients ingredientToPlannerIngredient(Ingredient ingredient);

    @IterableMapping(qualifiedByName = "ingredientToPlannerIngredient")
    List<PlannerIngredients> ingredientToPlannerIngredient(List<Ingredient> ingredient);


}
