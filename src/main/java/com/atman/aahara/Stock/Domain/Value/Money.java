package com.atman.aahara.Stock.Domain.Value;

import java.math.BigDecimal;



public record Money(BigDecimal amount) {

    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public Money multiply(BigDecimal factor) {
        return new Money(this.amount.multiply(factor));
    }
}
