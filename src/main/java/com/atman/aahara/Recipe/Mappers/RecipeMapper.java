package com.atman.aahara.Recipe.Mappers;

import com.atman.aahara.Assets.Image.Image;
import com.atman.aahara.Assets.Video.Video;
import com.atman.aahara.Recipe.Base.Dto.*;
import com.atman.aahara.Recipe.Base.Recipe;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {InstructionMapper.class}
)
public interface RecipeMapper {

    /* -------------------- Request → Entity -------------------- */
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "video", ignore = true)
    @Mapping(target = "offer", ignore = true)
    Recipe requestToEntityWithInstruction(RecipeRequestWithInstructionsDto dto);

    @Mapping(target = "image", ignore = true)
    @Mapping(target = "video", ignore = true)
    @Mapping(target = "offer", ignore = true)
    Recipe requestToEntity(RecipeRequestDto dto);

    /* -------------------- Entity → Response -------------------- */
    @Named("entityToResponseWithInstructions")
    @Mapping(target = "thumbnail", source = "image", qualifiedByName = "RecipeMapImage")
    @Mapping(target = "video", source = "video", qualifiedByName = "RecipeMapVideo")
    @Mapping(target = "offerId", source = "offer.id")
    @Mapping(target = "offerName", source = "offer.name")
    @Mapping(target = "offerDiscountValue", source = "offer.discountValue")
    @Mapping(target = "offerEndDate", source = "offer.endDate")
    @Mapping(target = "offerStock", source = "offer.remainingStock")
    RecipeResponseWithInstructionDto entityToResponseWithInstructions(Recipe recipe);

    @Named("entityToResponseWithOffer")
    @InheritConfiguration(name = "entityToResponseWithInstructions")
    @Mapping(target = "thumbnail", source = "image", qualifiedByName = "RecipeMapImage")
    @Mapping(target = "video", source = "video", qualifiedByName = "RecipeMapVideo")
    RecipeResponseWithOfferDto entityToResponseWithOffer(Recipe recipe);

    @IterableMapping(qualifiedByName = "entityToResponseWithOffer")
    @Mapping(target = "thumbnail", source = "image", qualifiedByName = "RecipeMapImage")
    @Mapping(target = "video", source = "video", qualifiedByName = "RecipeMapVideo")
    List<RecipeResponseWithOfferDto> entityToResponseWithOffer(List<Recipe> recipes);

    @Named("entityToResponseDto")
    @Mapping(target = "thumbnail", source = "image", qualifiedByName = "RecipeMapImage")
    @Mapping(target = "video", source = "video", qualifiedByName = "RecipeMapVideo")
    RecipeResponseDto entityToResponse(Recipe recipe);

    @IterableMapping(qualifiedByName = "entityToResponseDto")
    @Mapping(target = "thumbnail", source = "image", qualifiedByName = "RecipeMapImage")
    @Mapping(target = "video", source = "video", qualifiedByName = "RecipeMapVideo")
    List<RecipeResponseDto> entityToResponse(List<Recipe> recipes);

    /* -------------------- Update Existing -------------------- */
    Recipe mapExisting(RecipeRequestDto dto, @MappingTarget Recipe recipe);

    /* -------------------- Helpers -------------------- */
    @Named("RecipeMapImage")
    default String mapImage(Image image) {
        if (image == null) return null;
        return image.isEncoded() ? image.getEncodedImage() : null;
    }

    @Named("RecipeMapVideo")
    default String mapVideo(Video video) {
        if (video == null) return null;
        return video.isEncoded() ? video.getEncodedVideo() : null;
    }
}
