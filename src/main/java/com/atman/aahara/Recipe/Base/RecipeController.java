package com.atman.aahara.Recipe.Base;

import com.atman.aahara.Recipe.Base.Dto.*;
import com.atman.aahara.Recipe.Mappers.RecipeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Recipe API")
public class RecipeController {

    private final RecipeApplication recipeApplication;
    private final RecipeMapper recipeMapper;

    @PostMapping
    @Operation(summary = "Create a new recipe")
    public ResponseEntity<RecipeResponseWithInstructionDto> createRecipe(
            @ModelAttribute RecipeRequestWithInstructionsDto recipeRequestWithInstructionsDto) throws IOException {
        Recipe recipe = recipeApplication.createRecipe(recipeRequestWithInstructionsDto);
        return ResponseEntity.ok(recipeMapper.entityToResponseWithInstructions(recipe));
    }

    @PutMapping("/{recipeId}")
    @Operation(summary = "Update recipe details")
    public ResponseEntity<RecipeResponseDto> updateRecipe(
            @PathVariable UUID recipeId,
            @ModelAttribute RecipeRequestDto recipeRequestDto) throws IOException {
        Recipe updated = recipeApplication.updateRecipe(recipeId, recipeRequestDto);
        return ResponseEntity.ok(recipeMapper.entityToResponse(updated));
    }

    @DeleteMapping("/{recipeId}")
    @Operation(summary = "Delete recipe")
    public ResponseEntity<Void> deleteRecipe(@PathVariable UUID recipeId) {
        recipeApplication.deleteRecipe(recipeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{recipeId}")
    @Operation(summary = "Get recipe by ID")
    public ResponseEntity<RecipeResponseWithInstructionDto> getRecipe(@PathVariable UUID recipeId) {
        Recipe recipe = recipeApplication.getRecipe(recipeId);
        return ResponseEntity.ok(recipeMapper.entityToResponseWithInstructions(recipe));
    }

    @GetMapping
    @Operation(summary = "Get all recipes")
    public ResponseEntity<List<RecipeResponseDto>> getAllRecipes() {
        List<Recipe> recipes = recipeApplication.getAllRecipe();
        return ResponseEntity.ok(recipeMapper.entityToResponse(recipes));
    }
}
