package com.atman.aahara.Stock.Infra.Persistance;

import com.atman.aahara.Assets.Infra.ImageEntity;
import com.atman.aahara.Assets.Infra.VideoEntity;
import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Stock.Domain.Model.StepInstruction;
import com.atman.aahara.Stock.Shared.Enum.CusineType;
import com.atman.aahara.Stock.Shared.Enum.FlavourType;
import com.atman.aahara.Stock.Shared.Enum.MealType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "recipe")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class RecipeEntity extends BaseEntity {
    private String name;
    private String description;
    private BigDecimal rawPrice;
    private BigDecimal totalPrice;
    @Enumerated(EnumType.STRING)
    private MealType mealType;
    @Enumerated(EnumType.STRING)
    private CusineType cusineType;
    @Enumerated(EnumType.STRING)
    private FlavourType flavourType;

    private BigDecimal useCount;
    private BigDecimal likeCount;
    private BigDecimal preProcessingFee;


    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @OrderBy("step ASC")
    private List<StepInstruction> stepInstructions;

    @OneToOne
    @JoinColumn(name = "video_id")
    @JsonManagedReference
    private VideoEntity video;

    @OneToOne
    @JoinColumn(name = "thumbnail_id")
    @JsonManagedReference
    private ImageEntity thumbnail;
}
