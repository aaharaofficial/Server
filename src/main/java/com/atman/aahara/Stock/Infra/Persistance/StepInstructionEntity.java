package com.atman.aahara.Stock.Infra.Persistance;


import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Stock.Domain.Model.Ingredient;
import com.atman.aahara.Stock.Domain.Model.Recipe;
import com.atman.aahara.Stock.Shared.Enum.CookingProcess;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Duration;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "recipe")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class StepInstructionEntity extends BaseEntity {
    private Integer step;
    private String command;

    @Enumerated(EnumType.STRING)
    private CookingProcess cookingProcess;
    private Duration duration;

    @OneToMany(mappedBy = "instruction",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<IngredientEntity> ingredients;

    @ManyToOne()
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private RecipeEntity recipe;
}
