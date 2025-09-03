package com.atman.aahara.Recipe.Instruction.Dto;

import com.atman.aahara.Recipe.Enum.CookingProcess;
import com.atman.aahara.Recipe.Ingredients.Dto.IngredientResponseWithAlternativeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InstructionResponseWithIngredientsDto {
    private Integer step;
    private String command;
    private CookingProcess cookingProcess;
    private Duration duration;
    private UUID recipeId;
    private List<IngredientResponseWithAlternativeDto> ingredients;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
