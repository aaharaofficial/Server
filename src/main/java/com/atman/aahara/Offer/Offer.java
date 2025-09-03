package com.atman.aahara.Offer;

import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Offer.Enum.OfferType;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@MappedSuperclass
public abstract class Offer extends BaseEntity {

    private String name;
    private String description;
    private OfferType offerType;

    private BigDecimal discountValue;
    private BigDecimal currentUseCount;
    private BigDecimal maxUseCount;
    private BigDecimal remainingStock;

    private BigDecimal minOrderValue;
    private BigDecimal maxOrderValue;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private boolean active;
}
