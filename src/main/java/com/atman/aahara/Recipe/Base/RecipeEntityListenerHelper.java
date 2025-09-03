package com.atman.aahara.Recipe.Base;

import com.atman.aahara.Offer.Offer;
import com.atman.aahara.Offer.Enum.OfferType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class RecipeEntityListenerHelper {

    public static void recalculateTotalPriceForRecipe(Recipe recipe) {
        if (recipe.getRawPrice() != null) {
            BigDecimal fee = recipe.getPreProcessingFee() != null ? recipe.getPreProcessingFee() : BigDecimal.ZERO;
            BigDecimal total = recipe.getRawPrice().add(fee);
            Offer offer = recipe.getOffer();
            if (offer != null && offer.getOfferType() != null && offer.getDiscountValue() != null) {
                if (offer.getOfferType() == OfferType.FLAT) {
                    total = total.subtract(offer.getDiscountValue());
                } else if (offer.getOfferType() == OfferType.PERCENTAGE) {
                    BigDecimal discount = total.multiply(offer.getDiscountValue()).divide(BigDecimal.valueOf(100));
                    total = total.subtract(discount);
                }
                if (total.compareTo(BigDecimal.ZERO) < 0) {
                    total = BigDecimal.ZERO;
                }
            }
            recipe.setTotalPrice(total);
        }
    }
}
