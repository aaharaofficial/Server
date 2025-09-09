package com.atman.aahara.Planner.Items.Dto;

import com.atman.aahara.Planner.Enum.DeliverySession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PlannerItemRequestDto {
    private UUID inventoryId;
    private UUID familyId;
    private LocalDate plannerDate;
    private BigDecimal quantity;
    private DeliverySession deliverySession;
}
