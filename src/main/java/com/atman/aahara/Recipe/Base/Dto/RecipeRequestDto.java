package com.atman.aahara.Recipe.Base.Dto;

import com.atman.aahara.Family.Enum.CusineType;
import com.atman.aahara.Recipe.Enum.FlavourType;
import com.atman.aahara.Recipe.Enum.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RecipeRequestDto {
    private String name;
    private String description;
    private MealType mealType;
    private CusineType cusineType;
    private FlavourType flavourType;
    private MultipartFile video;
    private MultipartFile thumbnail;
    private boolean isThumbnailChanged;
    private boolean isVideoChanged;
}
