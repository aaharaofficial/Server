package com.atman.aahara.Planner.Items.Dto;

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
public class PlannerItemUpdateRequest {
    private UUID id;
    private BigDecimal quantity;
    private DeliverySession deliverySession;
}
