package com.atman.aahara.Recipe.AlternativeIngredients;

import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Inventory.Inventory;
import com.atman.aahara.Recipe.Enum.ChoppingSize;
import com.atman.aahara.Recipe.Enum.ChoppingStyle;
import com.atman.aahara.Recipe.Enum.GrindingNature;
import com.atman.aahara.Recipe.Ingredients.Ingredient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "alternative_Ingredient")
public class AlternativeIngredient extends BaseEntity {
    @ManyToOne()
    @JoinColumn(name = "inventory")
    @JsonBackReference
    private Inventory inventory;

    @ManyToOne()
    @JoinColumn(name = "ingredient")
    @JsonBackReference
    private Ingredient ingredient;
    private BigDecimal pricePerQuantity;
    private ChoppingStyle choppingStyle;
    private ChoppingSize choppingSize;
    private GrindingNature grindingNature;
    private Float quantity;
    private BigDecimal totalPrice;
    private String description;
}
