package com.atman.aahara.Offer.RecipeOffer;

import com.atman.aahara.Offer.Offer;
import com.atman.aahara.Recipe.Base.Recipe;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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
@Table(name = "recipe_offers")
public class RecipeOffer extends Offer {

    @OneToMany(mappedBy = "offer")
    @JsonManagedReference
    private List<Recipe> recipes;
}
