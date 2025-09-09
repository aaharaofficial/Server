package com.atman.aahara.Stock.Domain.Model;

import com.atman.aahara.Stock.Domain.Value.Money;
import com.atman.aahara.Stock.Shared.Enum.ChopSize;
import com.atman.aahara.Stock.Shared.Enum.ChopStyle;
import com.atman.aahara.Stock.Shared.Enum.GrindStyle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    private UUID id;
    private Inventory inventory;
    private BigDecimal quantity;
    private ChopStyle chopStyle;
    private ChopSize chopSize;
    private GrindStyle grindStyle;
    private boolean peeled;

    public Money calculatePrice() {
        Money basePrice = inventory.getPricePerUnit().multiply(quantity);

        Money processingFee = Money.ZERO;
        if (chopStyle != null) processingFee = processingFee.add(inventory.getChopFee());
        if (grindStyle != null) processingFee = processingFee.add(inventory.getGrindFee());
        if (peeled) processingFee = processingFee.add(inventory.getPeelFee());

        return basePrice.add(processingFee);
    }

    // Business methods for modifying ingredient
    public void updateQuantity(BigDecimal newQuantity) {
        if (newQuantity.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Quantity must be positive");
        this.quantity = newQuantity;
    }
}
