package com.atman.aahara.Recipe.Ingredients;

import com.atman.aahara.Recipe.Mappers.IngredientMapper;
import com.atman.aahara.Recipe.Ingredients.Dto.IngredientRequestDto;
import com.atman.aahara.Recipe.Ingredients.Dto.IngredientResponseDto;
import com.atman.aahara.Recipe.Ingredients.Dto.IngredientResponseWithAlternativeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
@Tag(name = "Ingredients", description = "Manage Ingredients")
public class IngredientController {

    private final IngredientApplication ingredientApplication;
    private final IngredientMapper ingredientMapper;
    private final IngredientService ingredientService;

    @PostMapping
    @Operation(summary = "Create a new ingredient")
    public ResponseEntity<IngredientResponseDto> createIngredient(@Valid @RequestBody IngredientRequestDto dto) {
        Ingredient ingredient = ingredientApplication.createIngredient(dto);
        return ResponseEntity.ok(ingredientMapper.entityToResponse(ingredient));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an ingredient by ID")
    public ResponseEntity<IngredientResponseDto> updateIngredient(@PathVariable UUID id, @Valid @RequestBody IngredientRequestDto dto) {
        Ingredient ingredient = ingredientApplication.updateIngredient(id, dto);
        IngredientResponseDto response = ingredientMapper.entityToResponse(ingredient);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an ingredient with alternatives by ID")
    public ResponseEntity<IngredientResponseWithAlternativeDto> getIngredient(@PathVariable UUID id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        IngredientResponseWithAlternativeDto ingredientResponseWithAlternativeDto = ingredientMapper.entityToResponseWithAlternative(ingredient);
        return ResponseEntity.ok(ingredientResponseWithAlternativeDto);
    }

    @GetMapping("/recipe/{recipeId}")
    @Operation(summary = "Get all ingredients for a recipe")
    public ResponseEntity<List<IngredientResponseDto>> getIngredientForRecipe(@PathVariable UUID recipeId) {
        List<Ingredient> ingredient = ingredientService.getAllIngredientByRecipeId(recipeId);
        List<IngredientResponseDto> allIngredients = ingredientMapper.entityToResponse(ingredient);
        return ResponseEntity.ok(allIngredients);
    }

    @GetMapping("/instruction/{instructionId}")
    @Operation(summary = "Get all ingredients for an instruction")
    public ResponseEntity<List<IngredientResponseWithAlternativeDto>> getIngredientForInstructions(@PathVariable UUID instructionId) {
        List<Ingredient> ingredient = ingredientService.getAllIngredientByInstructionId(instructionId);
        List<IngredientResponseWithAlternativeDto> allIngredients = ingredientMapper.entityToResponseWithAlternative(ingredient);
        return ResponseEntity.ok(allIngredients);
    }

    @GetMapping
    @Operation(summary = "Get all ingredients")
    public ResponseEntity<List<IngredientResponseDto>> getAllIngredients() {
        List<Ingredient> allIngredient = ingredientService.getAllIngredient();
        List<IngredientResponseDto> ingredientResponse = ingredientMapper.entityToResponse(allIngredient);
        return ResponseEntity.ok(ingredientResponse);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an ingredient by ID")
    public ResponseEntity<Void> deleteIngredients(@PathVariable UUID id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }
}
