package com.atman.aahara.Recipe.Mappers;

import com.atman.aahara.Recipe.Instruction.Dto.*;
import com.atman.aahara.Recipe.Instruction.Instruction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IngredientMapper.class})
public interface InstructionMapper {

    /* -------------------- Request → Entity -------------------- */

    @Mapping(target = "recipe", ignore = true)
    Instruction requestWithIngredientsToEntity(InstructionRequestWithIngredientsDto dto);

    @Mapping(target = "recipe", ignore = true)
    Instruction requestToEntity(InstructionRequestDto dto);

    /* -------------------- Entity → Response -------------------- */

    @Mapping(target = "recipeId", source = "recipe.id")
    InstructionResponseWithIngredientsDto entityToResponseWithIngredients(Instruction instruction);


    @Mapping(target = "recipeId", source = "recipe.id")
    List<InstructionResponseWithIngredientsDto> entityToResponseWithIngredients(List<Instruction> instruction);

    @Mapping(target = "recipeId", source = "recipe.id")
    InstructionsResponseDto entityToResponse(Instruction instruction);

    @Mapping(target = "recipeId", source = "recipe.id")
    List<InstructionsResponseDto> entityToResponse(List<Instruction> instruction);

    /* -------------------- Update Existing -------------------- */
    Instruction mapExisting(InstructionRequestDto dto, @MappingTarget Instruction instruction);
}
