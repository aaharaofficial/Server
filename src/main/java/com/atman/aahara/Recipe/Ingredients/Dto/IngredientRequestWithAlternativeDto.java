package com.atman.aahara.Recipe.Ingredients.Dto;

import com.atman.aahara.Recipe.AlternativeIngredients.Dto.AltIngredientRequestDto;
import com.atman.aahara.Recipe.Enum.ChoppingSize;
import com.atman.aahara.Recipe.Enum.ChoppingStyle;
import com.atman.aahara.Recipe.Enum.GrindingNature;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class IngredientRequestWithAlternativeDto {
    private ChoppingStyle choppingStyle;
    private ChoppingSize choppingSize;
    private GrindingNature grindingNature;
    private Float quantity;
    private boolean isPeeled;
    private String description;
    private UUID inventoryId;
    private UUID instructionId;
    private List<AltIngredientRequestDto> alternativeIngredients;
}
