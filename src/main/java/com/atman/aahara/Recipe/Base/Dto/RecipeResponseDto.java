package com.atman.aahara.Recipe.Base.Dto;


import com.atman.aahara.Family.Enum.CusineType;
import com.atman.aahara.Recipe.Enum.FlavourType;
import com.atman.aahara.Recipe.Enum.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RecipeResponseDto {
    private UUID id;
    private String name;
    private String description;
    private MealType mealType;
    private CusineType cusineType;
    private FlavourType flavourType;
    private String video;
    private String thumbnail;
}
