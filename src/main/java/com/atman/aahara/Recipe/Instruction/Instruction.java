package com.atman.aahara.Recipe.Instruction;

import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Recipe.Enum.CookingProcess;
import com.atman.aahara.Recipe.Ingredients.Ingredient;
import com.atman.aahara.Recipe.Base.Recipe;
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
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Data
@Table(name = "instruction",  uniqueConstraints = {
        @UniqueConstraint(columnNames = {"recipe_id", "step"})
})
public class Instruction extends BaseEntity {
    private Integer step;
    private String command;
    private CookingProcess cookingProcess;
    private Duration duration;
    @OneToMany(mappedBy = "instruction",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Ingredient> ingredients;
    @ManyToOne()
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private Recipe recipe;
}
