package com.atman.aahara.Offer.RecipeOffer.Dto;

import com.atman.aahara.Offer.Enum.OfferType;
import com.atman.aahara.Recipe.Base.Dto.RecipeResponseWithOfferDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class RecipeOfferResponseDto {

    private String name;

    private String description;

    private OfferType offerType;

    private BigDecimal discountValue;

    private BigDecimal currentUseCount;

    private BigDecimal maxUseCount;

    private BigDecimal minOrderValue;

    private BigDecimal maxOrderValue;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean active;

    private List<RecipeResponseWithOfferDto> recipes;
}
