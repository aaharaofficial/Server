package com.atman.aahara.Recipe.Base;

import com.atman.aahara.Assets.Image.Image;
import com.atman.aahara.Assets.Video.Video;
import com.atman.aahara.Family.Enum.CusineType;
import com.atman.aahara.Global.BaseEntity;
import com.atman.aahara.Offer.Offer;
import com.atman.aahara.Offer.RecipeOffer.RecipeOffer;
import com.atman.aahara.Recipe.Enum.FlavourType;
import com.atman.aahara.Recipe.Enum.MealType;
import com.atman.aahara.Recipe.Instruction.Instruction;
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
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Data
@Table(name = "recipe")
@EntityListeners(RecipeEntityListener.class)
public class Recipe extends BaseEntity {
    private String name;
    private String description;
    private BigDecimal rawPrice;
    private BigDecimal totalPrice;
    private MealType mealType;
    private CusineType cusineType;
    private FlavourType flavourType;
    private BigDecimal useCount;
    private BigDecimal likeCount;
    private BigDecimal preProcessingFee;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    @JsonBackReference
    private RecipeOffer offer;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @OrderBy("step ASC")
    private List<Instruction> instructions;

    @OneToOne
    @JoinColumn(name = "video_id")
    @JsonManagedReference
    private Video video;

    @OneToOne
    @JoinColumn(name = "image_id")
    @JsonManagedReference
    private Image image;
}
