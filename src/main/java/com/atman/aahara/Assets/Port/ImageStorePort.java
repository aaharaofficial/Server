package com.atman.aahara.Assets.Port;

import com.atman.aahara.Assets.Domain.Image;

import java.util.Optional;
import java.util.UUID;

public interface ImageStorePort {
    Image save(Image image);
    Optional<Image> findById(UUID id);
    void delete(Image image);
}
