
package com.atman.aahara.Offer.InventoryOffer;


import com.atman.aahara.Inventory.Inventory;
import com.atman.aahara.Offer.Offer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@SuperBuilder
@Table(name = "inventory_offer")
public class InventoryOffer extends Offer {

    @OneToMany(mappedBy = "offer")
    @JsonBackReference
    private List<Inventory> inventories;
}
