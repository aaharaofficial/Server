package com.atman.aahara.Stock.Domain.Model;


import com.atman.aahara.Stock.Domain.Value.Money;
import com.atman.aahara.Stock.Shared.Enum.CookingProcess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StepInstruction {
    private Integer step;
    private String command;
    private CookingProcess cookingProcess;
    private final List<Ingredient> ingredients = new ArrayList<>();

    // Calculate total price for this step
    public Money calculateStepPrice() {
        return ingredients.stream()
                .map(Ingredient::calculatePrice)
                .reduce(Money.ZERO, Money::add);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

}
