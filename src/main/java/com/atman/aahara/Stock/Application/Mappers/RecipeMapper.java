package com.atman.aahara.Stock.Application.Mappers;

import com.atman.aahara.Stock.Domain.Model.Recipe;
import com.atman.aahara.Stock.Infra.Persistance.RecipeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    Recipe toDomain(RecipeEntity recipeEntity);
    RecipeEntity toEntity(Recipe recipe);
}
