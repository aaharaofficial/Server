package com.atman.aahara.Stock.Infra.Persistance;


import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Stock.Domain.Model.Inventory;
import com.atman.aahara.Stock.Shared.Enum.ChopSize;
import com.atman.aahara.Stock.Shared.Enum.ChopStyle;
import com.atman.aahara.Stock.Shared.Enum.GrindStyle;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "inventory")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class IngredientEntity extends BaseEntity {
    @ManyToOne()
    @JoinColumn(name = "inventory")
    @JsonBackReference
    private InventoryEntity inventory;

    @ManyToOne()
    @JoinColumn(name = "instruction")
    @JsonBackReference
    private StepInstructionEntity stepInstruction;
    private ChopStyle chopStyle;
    private ChopSize chopSize;
    private GrindStyle grindStyle;
    private Float quantity;
    private boolean isPeeled;
    private String description;
}
