package com.atman.aahara.Planner.Items;

import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Planner.Enum.DeliverySession;
import com.atman.aahara.Planner.Planner.Planner;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Data
@Table(name = "plannerItem")
public class PlannerItem extends BaseEntity {
    @ManyToOne()
    @JoinColumn(name = "planner_id")
    @JsonBackReference
    private Planner planner;
    private String name;
    @ManyToOne()
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
    private BigDecimal pricePerQuantity;
    private String image;
    private String unit;
    private BigDecimal quantity;
    private BigDecimal totalPrice;
    private DeliverySession deliverySession;
}
