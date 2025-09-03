package com.atman.aahara.Offer.RecipeOffer;

import com.atman.aahara.Exception.ResourceNotFoundException;
import com.atman.aahara.Offer.RecipeOffer.RecipeOffer;
import com.atman.aahara.Offer.RecipeOffer.RecipeOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecipeOfferLogic {

    private final RecipeOfferRepository recipeOfferRepository;

    public RecipeOffer saveRecipe(RecipeOffer RecipeOffer) {
        return recipeOfferRepository.save(RecipeOffer);
    }

    public RecipeOffer getOffer(UUID id) {
        return recipeOfferRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("offer not found" + id));
    }

    public List<RecipeOffer> getAllOffer() {
        return recipeOfferRepository.findAll();
    }


    public void deleteOffer(RecipeOffer offer) {
        recipeOfferRepository.delete(offer);
    }

    public void deleteOffer(UUID offerId) {
        if (recipeOfferRepository.existsById(offerId)) {
            recipeOfferRepository.deleteById(offerId);
        }
    }
}
