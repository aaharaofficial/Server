package com.atman.aahara.Offer.RecipeOffer;

import com.atman.aahara.Offer.RecipeOffer.Dto.RecipeOfferRequestDto;
import com.atman.aahara.Offer.RecipeOffer.Dto.RecipeOfferResponseDto;
import com.atman.aahara.Recipe.Base.Recipe;
import com.atman.aahara.Recipe.Base.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/offer/recipe")
@RequiredArgsConstructor
@Slf4j
public class RecipeOfferController {

    private final RecipeOfferMapper recipeOfferMapper;
    private final RecipeOfferLogic recipeOfferService;
    private final RecipeService recipeService;

    @PostMapping
    @Operation(summary = "Create a new recipe offer")
    public ResponseEntity<RecipeOfferResponseDto> createRecipeOffer(
            @Valid @RequestBody RecipeOfferRequestDto recipeOfferRequestDto) {
        List<Recipe> recipesForIds = recipeService.getRecipesForIds(recipeOfferRequestDto.getRecipes());
        RecipeOffer recipeOffer = recipeOfferMapper.RequestToEntity(recipeOfferRequestDto);
        recipeOffer.setRecipes(recipesForIds);
        RecipeOffer savedOffer = recipeOfferService.saveRecipe(recipeOffer);
        RecipeOfferResponseDto response = recipeOfferMapper.EntityToResponse(savedOffer);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing recipe offer")
    public ResponseEntity<RecipeOfferResponseDto> updateRecipeOffer(
            @PathVariable UUID id,
            @Valid @RequestBody RecipeOfferRequestDto recipeOfferRequestDto) {

        RecipeOffer existingOffer = recipeOfferService.getOffer(id);
        // Map incoming DTO to existing entity
        recipeOfferMapper.RequestToEntity(recipeOfferRequestDto); // Or implement a mapExisting method in Mapper
        RecipeOffer updatedOffer = recipeOfferService.saveRecipe(existingOffer);
        RecipeOfferResponseDto response = recipeOfferMapper.EntityToResponse(updatedOffer);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a recipe offer by ID")
    public ResponseEntity<RecipeOfferResponseDto> getOfferById(@PathVariable UUID id) {
        RecipeOffer offer = recipeOfferService.getOffer(id);
        RecipeOfferResponseDto response = recipeOfferMapper.EntityToResponse(offer);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all recipe offers")
    public ResponseEntity<List<RecipeOfferResponseDto>> getAllOffer() {
        List<RecipeOffer> offers = recipeOfferService.getAllOffer();
        List<RecipeOfferResponseDto> response = recipeOfferMapper.EntityToResponse(offers);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a recipe offer by ID")
    public ResponseEntity<Void> deleteOffer(@PathVariable UUID id) {
        recipeOfferService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }
}
