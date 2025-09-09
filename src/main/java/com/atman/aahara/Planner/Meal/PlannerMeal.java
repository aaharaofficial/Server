package com.atman.aahara.Planner.Meal;

import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Planner.Enum.DeliverySession;
import com.atman.aahara.Planner.Ingredients.PlannerIngredients;
import com.atman.aahara.Planner.Planner.Planner;
import com.atman.aahara.Recipe.Base.Recipe;
import com.atman.aahara.Recipe.Enum.MealType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Data
@Table(name = "planner_meal")
public class PlannerMeal extends BaseEntity {
    @ManyToOne()
    @JoinColumn(name = "planner_id")
    @JsonBackReference
    private Planner planner;
    private String name;
    private BigDecimal price;
    private MealType mealType;
    private BigDecimal servings;
    private DeliverySession deliverySession;

    @ManyToOne()
    @JoinColumn(name = "recipe_id")
    @JsonBackReference
    private Recipe recipe;

    @OneToMany(mappedBy = "meal")
    @JsonManagedReference
    private List<PlannerIngredients> ingredients;
}
