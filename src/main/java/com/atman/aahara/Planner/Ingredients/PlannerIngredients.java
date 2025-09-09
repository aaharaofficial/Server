package com.atman.aahara.Planner.Ingredients;

import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Planner.Meal.PlannerMeal;
import com.atman.aahara.Recipe.Enum.ChopStyle;
import com.atman.aahara.Recipe.Enum.ChopSize;
import com.atman.aahara.Recipe.Enum.GrindStyle;
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
@Table(name = "planner_Ingredient")
public class PlannerIngredients extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private PlannerMeal meal;

    @ManyToOne()
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
    private BigDecimal quantity;
    private BigDecimal totalPrice;
    private ChopStyle chopStyle;
    private ChopSize chopSize;
    private boolean isPeeled;
    private GrindStyle grindStyle;
}
