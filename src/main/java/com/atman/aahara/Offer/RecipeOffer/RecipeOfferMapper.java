package com.atman.aahara.Offer.RecipeOffer;

import com.atman.aahara.Offer.RecipeOffer.Dto.RecipeOfferRequestDto;
import com.atman.aahara.Offer.RecipeOffer.Dto.RecipeOfferResponseDto;
import com.atman.aahara.Recipe.Mappers.RecipeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {RecipeMapper.class})
public interface RecipeOfferMapper {

    /* -------------------- Request → Entity -------------------- */

    @Mapping(target = "recipes" , ignore = true)
    RecipeOffer RequestToEntity(RecipeOfferRequestDto recipeOfferRequestDto);

    @Mapping(target = "recipes" , ignore = true)
    List<RecipeOffer> RequestToEntity(List<RecipeOfferRequestDto> recipeOfferRequestDto);

    /* -------------------- Entity → Response -------------------- */

    RecipeOfferResponseDto EntityToResponse(RecipeOffer recipeOffer);

    List<RecipeOfferResponseDto> EntityToResponse(List<RecipeOffer> recipeOffer);



}
