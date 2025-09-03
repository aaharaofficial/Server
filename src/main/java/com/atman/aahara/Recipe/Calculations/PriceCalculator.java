package com.atman.aahara.Recipe.Calculations;


import com.atman.aahara.Recipe.Ingredients.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceCalculator {
    private BigDecimal calculateIngredientPrice(Ingredient ingredient) {
        if (ingredient.getInventory().getRawPrice() == null || ingredient.getQuantity() == null) {
            return BigDecimal.ZERO;
        }
        return ingredient.getInventory().getRawPrice()
                .multiply(BigDecimal.valueOf(ingredient.getQuantity()));
    }

    public BigDecimal calculateListOfIngredientPrice(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(this::calculateIngredientPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
