package com.atman.aahara.Recipe.Instruction.Dto;

import com.atman.aahara.Recipe.Enum.CookingProcess;
import com.atman.aahara.Recipe.Ingredients.Dto.IngredientRequestWithAlternativeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InstructionRequestWithIngredientsDto {
    private Integer step;
    private String command;
    private CookingProcess cookingProcess;
    private Duration duration;
    private UUID recipeId;
    private List<IngredientRequestWithAlternativeDto> ingredients;
}
