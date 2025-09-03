package com.atman.aahara.Inventory.Dto;


import com.atman.aahara.Offer.Enum.OfferType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class InventoryResponseWithOfferDto {
    private UUID id;
    private String name;
    private BigDecimal rawPrice;
    private BigDecimal totalPrice;
    private String description;
    private String image;
    private UUID offerId;
    private String offerName;
    private OfferType offerType;
    private BigDecimal offerDiscountValue;
    private LocalDateTime offerEndDate;
    private BigDecimal offerStock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
