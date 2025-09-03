package com.atman.aahara.Recipe.Instruction;

import com.atman.aahara.Recipe.Mappers.InstructionMapper;
import com.atman.aahara.Recipe.Instruction.Dto.InstructionRequestDto;
import com.atman.aahara.Recipe.Instruction.Dto.InstructionRequestWithIngredientsDto;
import com.atman.aahara.Recipe.Instruction.Dto.InstructionResponseWithIngredientsDto;
import com.atman.aahara.Recipe.Instruction.Dto.InstructionsResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/instruction")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Instruction API")
public class InstructionController {

    private final InstructionApplication instructionApplication;
    private final InstructionService instructionService;
    private final InstructionMapper instructionMapper;

    @PostMapping
    @Operation(summary = "Add a new instruction to a recipe")
    public ResponseEntity<InstructionResponseWithIngredientsDto> addInstruction(
            @Valid @RequestBody InstructionRequestWithIngredientsDto instructionRequestWithIngredientsDto) {
        Instruction instruction = instructionApplication.addInstruction(instructionRequestWithIngredientsDto);
        InstructionResponseWithIngredientsDto instructionResponseWithIngredientsDto = instructionMapper.entityToResponseWithIngredients(instruction);
        return ResponseEntity.ok(instructionResponseWithIngredientsDto);
    }

    @PutMapping("/{instructionId}")
    @Operation(summary = "Update an existing instruction")
    public ResponseEntity<InstructionsResponseDto> updateInstruction(
            @PathVariable UUID instructionId,
            @Valid @RequestBody InstructionRequestDto instructionRequestDto) {
        Instruction updated = instructionApplication.updateInstruction(instructionId, instructionRequestDto);
        InstructionsResponseDto instructionsResponseDto = instructionMapper.entityToResponse(updated);
        return ResponseEntity.ok(instructionsResponseDto);
    }

    @DeleteMapping("/{instructionId}")
    @Operation(summary = "Delete an instruction")
    public ResponseEntity<Void> deleteInstruction(@PathVariable UUID instructionId) {
        instructionApplication.removeInstruction(instructionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{instructionId}")
    @Operation(summary = "Get instruction by ID")
    public ResponseEntity<InstructionResponseWithIngredientsDto> getInstruction(@PathVariable UUID instructionId) {
        Instruction instruction = instructionService.getInstruction(instructionId);
        InstructionResponseWithIngredientsDto instructionResponseWithIngredientsDto = instructionMapper.entityToResponseWithIngredients(instruction);
        return ResponseEntity.ok(instructionResponseWithIngredientsDto);
    }

    @GetMapping("/recipe/{recipeId}")
    @Operation(summary = "Get all instructions for a recipe")
    public ResponseEntity<List<InstructionResponseWithIngredientsDto>> getAllForRecipe(@PathVariable UUID recipeId) {
        List<Instruction> instructions = instructionService.getAllInstructionForRecipe(recipeId);
        List<InstructionResponseWithIngredientsDto> instructionResponseWithIngredientsDtos = instructionMapper.entityToResponseWithIngredients(instructions);
        return ResponseEntity.ok(instructionResponseWithIngredientsDtos);
    }

    @GetMapping
    @Operation(summary = "Get all instructions")
    public ResponseEntity<List<InstructionsResponseDto>> getAllInstructions() {
        List<Instruction> instructions = instructionService.getAllInstruction();
        List<InstructionsResponseDto> instructionsResponseDtos = instructionMapper.entityToResponse(instructions);
        return ResponseEntity.ok(instructionsResponseDtos);
    }
}
