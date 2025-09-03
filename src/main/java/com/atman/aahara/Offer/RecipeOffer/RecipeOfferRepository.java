package com.atman.aahara.Offer.RecipeOffer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecipeOfferRepository extends JpaRepository<RecipeOffer, UUID> {
}
