package com.atman.aahara.Recipe.Base.Dto;

import com.atman.aahara.Family.Enum.CusineType;
import com.atman.aahara.Offer.Enum.OfferType;
import com.atman.aahara.Recipe.Enum.FlavourType;
import com.atman.aahara.Recipe.Enum.MealType;
import com.atman.aahara.Recipe.Instruction.Dto.InstructionResponseWithIngredientsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RecipeResponseWithInstructionDto {
    private String name;
    private String description;
    private MealType mealType;
    private CusineType cusineType;
    private FlavourType flavourType;
    private String video;
    private String thumbnail;
    private UUID offerId;
    private String offerName;
    private OfferType offerType;
    private BigDecimal offerDiscountValue;
    private LocalDateTime offerEndDate;
    private BigDecimal offerStock;
    private List<InstructionResponseWithIngredientsDto> instructions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
