package com.atman.aahara.Recipe.Mappers;

import com.atman.aahara.Assets.Image.Image;
import com.atman.aahara.Inventory.InventoryMapper;
import com.atman.aahara.Recipe.AlternativeIngredients.AlternativeIngredient;
import com.atman.aahara.Recipe.AlternativeIngredients.Dto.AltIngredientRequestDto;
import com.atman.aahara.Recipe.AlternativeIngredients.Dto.AltIngredientResponseDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InventoryMapper.class})
public interface AlternativeIngredientMapper {

    /* -------------------- Request → Entity -------------------- */
    @Named("requestToEntity")
    @Mapping(target = "ingredient", ignore = true)
    @Mapping(target = "inventory", ignore = true)
    AlternativeIngredient requestToEntity(AltIngredientRequestDto dto);

    @IterableMapping(qualifiedByName = "requestToEntity")
    List<AlternativeIngredient> requestToEntity(List<AltIngredientRequestDto> dtos);

    /* -------------------- Entity → Response -------------------- */
    @Named("entityToResponse")
    @Mapping(target = "inventoryId", source = "inventory.id")
    @Mapping(target = "inventoryName", source = "inventory.name")
    @Mapping(target = "inventoryImage", source = "inventory.image", qualifiedByName = "altMapImage")
    @Mapping(target = "inventoryPricePerQuantity", source = "inventory.rawPrice")
    @Mapping(target = "ingredientId", source = "ingredient.id")
    AltIngredientResponseDto entityToResponse(AlternativeIngredient alternativeIngredient);

    @IterableMapping(qualifiedByName = "entityToResponse")
    @Mapping(target = "inventoryImage", source = "inventory.image", qualifiedByName = "altMapImage")
    List<AltIngredientResponseDto> entityToResponse(List<AlternativeIngredient> alternativeIngredients);

    /* -------------------- Update Existing -------------------- */
    AlternativeIngredient mapExisting(AltIngredientRequestDto dto,
                                      @MappingTarget AlternativeIngredient alternativeIngredient);

    /* -------------------- Helpers -------------------- */
    @Named("altMapImage")
    default String mapImage(Image image) {
        if (image == null) {
            return null;
        }
        return image.isEncoded() ? image.getEncodedImage() : null;
    }
}
