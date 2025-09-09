package com.atman.aahara.Stock.Infra.Persistance;

import com.atman.aahara.Assets.Infra.ImageEntity;
import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Stock.Shared.Enum.Unit;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "inventories")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class InventoryEntity extends  BaseEntity {

    private String name;

    private BigDecimal pricePerQuantity;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @OneToMany(mappedBy = "inventory")
    @JsonManagedReference
    private List<IngredientEntity> ingredients = new ArrayList<>();

}
