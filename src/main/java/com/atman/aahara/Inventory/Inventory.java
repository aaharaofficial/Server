package com.atman.aahara.Inventory;

import com.atman.aahara.Assets.Image.Image;
import com.atman.aahara.Global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Data
@Table(name = "inventory")
public class Inventory extends BaseEntity {
    private String name;
    private BigDecimal rawPrice;
    private BigDecimal totalPrice;
    private String description;
    @OneToOne()
    @JoinColumn(name = "image_id")
    private Image image;
}
