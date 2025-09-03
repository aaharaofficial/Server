package com.atman.aahara.Recipe.Ingredients;

import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Inventory.Inventory;
import com.atman.aahara.Recipe.AlternativeIngredients.AlternativeIngredient;
import com.atman.aahara.Recipe.Enum.ChoppingSize;
import com.atman.aahara.Recipe.Enum.ChoppingStyle;
import com.atman.aahara.Recipe.Enum.GrindingNature;
import com.atman.aahara.Recipe.Instruction.Instruction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Data
@Table(name = "ingredient")
@EntityListeners(IngredientEntityListener.class)
public class Ingredient extends BaseEntity {
    @ManyToOne()
    @JoinColumn(name = "inventory")
    @JsonBackReference
    private Inventory inventory;

    @ManyToOne()
    @JoinColumn(name = "instruction")
    @JsonBackReference
    private Instruction instruction;
    private ChoppingStyle choppingStyle;
    private ChoppingSize choppingSize;
    private GrindingNature grindingNature;
    private Float quantity;
    private boolean isPeeled;
    private String description;

    @OneToMany(mappedBy = "ingredient",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AlternativeIngredient> alternativeIngredients;
}
