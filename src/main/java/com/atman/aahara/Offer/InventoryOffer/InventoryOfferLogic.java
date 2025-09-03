package com.atman.aahara.Offer.InventoryOffer;

import com.atman.aahara.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryOfferLogic implements InventoryOfferService {

    private final InventoryOfferRepository inventoryOfferRepository;

    @Override
    public InventoryOffer saveRecipe(InventoryOffer inventoryOffer) {
        return inventoryOfferRepository.save(inventoryOffer);
    }

    @Override
    public InventoryOffer getOffer(UUID id) {
        return inventoryOfferRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("offer not found" + id));
    }

    @Override
    public List<InventoryOffer> getAllOffer() {
        return inventoryOfferRepository.findAll();
    }


    @Override
    public void deleteOffer(InventoryOffer offer) {
        inventoryOfferRepository.delete(offer);
    }

    @Override
    public void deleteOffer(UUID offerId) {
        if (inventoryOfferRepository.existsById(offerId)) {
            inventoryOfferRepository.deleteById(offerId);
        }
    }
}
