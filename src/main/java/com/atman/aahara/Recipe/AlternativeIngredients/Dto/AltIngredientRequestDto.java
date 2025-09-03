package com.atman.aahara.Recipe.AlternativeIngredients.Dto;

import com.atman.aahara.Recipe.Enum.ChoppingSize;
import com.atman.aahara.Recipe.Enum.ChoppingStyle;
import com.atman.aahara.Recipe.Enum.GrindingNature;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AltIngredientRequestDto {
    private UUID ingredientId;
    private UUID inventoryId;
    private ChoppingStyle choppingStyle;
    private ChoppingSize choppingSize;
    private GrindingNature grindingNature;
    private boolean isPeeled;
    private Float quantity;
    private String description;
}
