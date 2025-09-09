package com.atman.aahara.Stock.Domain.Model;

import com.atman.aahara.Assets.Domain.Image;
import com.atman.aahara.Stock.Domain.Value.Money;
import com.atman.aahara.Stock.Shared.Enum.Unit;
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
public class Inventory {
    private UUID id;
    private String name;
    private Money pricePerUnit;
    private Money chopFee;
    private Money grindFee;
    private Money peelFee;
    private Unit unit;
    private Image image;
    private String description;

    // Business logic
    public void setPricePerUnit(Money newPrice) {
        if (newPrice.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        this.pricePerUnit = newPrice;
    }

}
