package com.atman.aahara.Planner.Items.Dto;

import com.atman.aahara.Offer.InventoryOffer.InventoryOffer;
import com.atman.aahara.Planner.Enum.DeliverySession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PlannerItemResponseDto {
    private UUID id;
    private UUID inventoryId;
    private BigDecimal pricePerQuantity;
    private String image;
    private String unit;
    private BigDecimal quantity;
    private BigDecimal totalPrice;
    private DeliverySession deliverySession;
}
