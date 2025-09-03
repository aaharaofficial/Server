package com.atman.aahara.Offer.InventoryOffer;

import java.util.List;
import java.util.UUID;

public interface InventoryOfferService {
    InventoryOffer saveRecipe(InventoryOffer inventoryOffer);

    InventoryOffer getOffer(UUID id);

    List<InventoryOffer> getAllOffer();

    void deleteOffer(InventoryOffer offer);

    void deleteOffer(UUID offerId);
}
