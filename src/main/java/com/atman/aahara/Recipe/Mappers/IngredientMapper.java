package com.atman.aahara.Recipe.Mappers;

import com.atman.aahara.Assets.Image.Image;
import com.atman.aahara.Recipe.Ingredients.Dto.*;
import com.atman.aahara.Recipe.Ingredients.Ingredient;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AlternativeIngredientMapper.class})
public interface IngredientMapper {

    /* -------------------- Request → Entity -------------------- */
    @Named("requestToEntity")
    @Mapping(target = "instruction", ignore = true)
    @Mapping(target = "inventory", ignore = true)
    Ingredient requestToEntity(IngredientRequestDto dto);

    @Named("requestToEntityWithAlt")
    @Mapping(target = "instruction", ignore = true)
    @Mapping(target = "inventory", ignore = true)
    Ingredient requestToEntityWithAlternative(IngredientRequestWithAlternativeDto dto);

    @IterableMapping(qualifiedByName = "requestToEntity")
    List<Ingredient> requestToEntity(List<IngredientRequestDto> dtos);

    @IterableMapping(qualifiedByName = "requestToEntityWithAlt")
    List<Ingredient> requestToEntityWithAlternative(List<IngredientRequestWithAlternativeDto> dtos);

    /* -------------------- Entity → Response -------------------- */
    @Named("entityToResponse")
    @Mapping(target = "inventoryId", source = "inventory.id")
    @Mapping(target = "inventoryName", source = "inventory.name")
    @Mapping(target = "inventoryImage", source = "inventory.image", qualifiedByName = "mapImage")
    @Mapping(target = "inventoryPricePerQuantity", source = "inventory.rawPrice")
    @Mapping(target = "inventoryUnit", source = "inventory.unit")
    @Mapping(target = "instructionId", source = "instruction.id")
    IngredientResponseDto entityToResponse(Ingredient ingredient);

    @Named("entityToResponseWithAlt")
    @InheritConfiguration(name = "entityToResponse")
    @Mapping(target = "inventoryImage", source = "inventory.image", qualifiedByName = "mapImage")
    IngredientResponseWithAlternativeDto entityToResponseWithAlternative(Ingredient ingredient);

    @IterableMapping(qualifiedByName = "entityToResponse")
    @Mapping(target = "inventoryImage", source = "inventory.image", qualifiedByName = "mapImage")
    List<IngredientResponseDto> entityToResponse(List<Ingredient> ingredients);

    @IterableMapping(qualifiedByName = "entityToResponseWithAlt")
    @Mapping(target = "inventoryImage", source = "inventory.image", qualifiedByName = "mapImage")
    List<IngredientResponseWithAlternativeDto> entityToResponseWithAlternative(List<Ingredient> ingredients);

    /* -------------------- Update Existing -------------------- */
    Ingredient mapExisting(IngredientRequestDto dto, @MappingTarget Ingredient ingredient);

    /* -------------------- Helpers -------------------- */
    @Named("mapImage")
    default String mapImage(Image image) {
        if (image == null) return null;
        return image.isEncoded() ? image.getEncodedImage() : null;
    }
}
