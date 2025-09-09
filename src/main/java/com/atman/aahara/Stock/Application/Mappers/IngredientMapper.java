package com.atman.aahara.Stock.Application.Mappers;

import com.atman.aahara.Stock.Domain.Model.Ingredient;
import com.atman.aahara.Stock.Infra.Persistance.IngredientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    Ingredient toDomain(IngredientEntity entity);

    IngredientEntity toEntity(Ingredient domain);
}
