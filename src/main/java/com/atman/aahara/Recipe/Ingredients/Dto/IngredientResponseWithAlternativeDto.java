package com.atman.aahara.Recipe.Ingredients.Dto;


import com.atman.aahara.Inventory.Enum.Unit;
import com.atman.aahara.Recipe.AlternativeIngredients.Dto.AltIngredientResponseDto;
import com.atman.aahara.Recipe.Enum.ChoppingSize;
import com.atman.aahara.Recipe.Enum.ChoppingStyle;
import com.atman.aahara.Recipe.Enum.GrindingNature;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class IngredientResponseWithAlternativeDto {
    private ChoppingStyle choppingStyle;
    private ChoppingSize choppingSize;
    private GrindingNature grindingNature;
    private Float quantity;
    private boolean isPeeled;
    private String description;
    private UUID inventoryId;
    private String inventoryName;
    private Unit inventoryUnit;
    private String inventoryPricePerQuantity;
    private String inventoryImage;
    private UUID instructionId;
    private List<AltIngredientResponseDto> alternativeIngredients;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
