package com.atman.aahara.Inventory;

import com.atman.aahara.Assets.Image.Image;
import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Inventory.Enum.Unit;
import com.atman.aahara.Offer.InventoryOffer.InventoryOffer;
import com.atman.aahara.Offer.Offer;
import com.atman.aahara.Recipe.AlternativeIngredients.AlternativeIngredient;
import com.atman.aahara.Recipe.Ingredients.Ingredient;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

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
    private Unit unit;
    private BigDecimal totalPrice;
    private String description;
    @OneToOne()
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(mappedBy = "inventory")
    private List<Ingredient> ingredients;

    @OneToMany(mappedBy = "inventory")
    private List<AlternativeIngredient> alternativeIngredients;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    @JsonBackReference
    private InventoryOffer offer;
}
