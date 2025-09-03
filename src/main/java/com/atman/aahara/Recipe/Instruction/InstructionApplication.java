package com.atman.aahara.Recipe.Instruction;


import com.atman.aahara.Recipe.Base.Recipe;
import com.atman.aahara.Recipe.Base.RecipeService;
import com.atman.aahara.Recipe.Mappers.InstructionMapper;
import com.atman.aahara.Recipe.Instruction.Dto.InstructionRequestDto;
import com.atman.aahara.Recipe.Instruction.Dto.InstructionRequestWithIngredientsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class InstructionApplication {

    private final RecipeService recipeService;
    private final InstructionMapper instructionMapper;
    private final InstructionService instructionService;

    public Instruction addInstruction(InstructionRequestWithIngredientsDto instructionRequestWithIngredientsDto) {
        Instruction instruction = instructionMapper.requestWithIngredientsToEntity(instructionRequestWithIngredientsDto);
        Recipe recipe = recipeService.getRecipe(instructionRequestWithIngredientsDto.getRecipeId());
        instruction.setRecipe(recipe);
        instructionService.incrementSteps(instructionRequestWithIngredientsDto.getRecipeId(), instruction.getStep());
        return instructionService.saveInstruction(instruction);
    }

    public Instruction updateInstruction(UUID instructionId, InstructionRequestDto instructionRequestDto) {
        Instruction instruction = instructionService.getInstruction(instructionId);
        if (!Objects.equals(instruction.getStep(), instructionRequestDto.getStep())) {
            instructionService.swapInstruction(instruction.getId(), instructionRequestDto.getStep());
        }
        Instruction mappedInstruction = instructionMapper.mapExisting(instructionRequestDto, instruction);
        return instructionService.saveInstruction(mappedInstruction);
    }

    public void removeInstruction(UUID instructionId) {
        Instruction instruction = instructionService.getInstruction(instructionId);
        instructionService.decrementSteps(instruction.getRecipe().getId(), instruction.getStep());
        instructionService.deleteInstruction(instruction);
    }
}
