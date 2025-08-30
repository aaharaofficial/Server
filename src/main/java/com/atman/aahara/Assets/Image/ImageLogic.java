package com.atman.aahara.Assets.Image;

import com.atman.aahara.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageLogic {

    private final ImageRepository imageRepository;

    /**
     * Create a new Image entity with raw image key.
     */
    public Image createImage(String rawImageKey) {
        Image image = Image.builder()
                .rawImage(rawImageKey)
                .isEncoded(false)
                .build();
        return imageRepository.save(image);
    }

    /**
     * Fetch image by ID. Throws exception if not found.
     */
    public Image getImage(UUID id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found: " + id));
    }

    /**
     * Encode an existing image (set encoded key and mark as encoded)
     */
    public Image encodeImage(UUID id, String encodedImageKey) {
        Image image = getImage(id);
        image.setEncodedImage(encodedImageKey);
        image.setEncoded(true);
        return imageRepository.save(image);
    }

    /**
     * Delete image by ID.
     */
    public void deleteImage(UUID id) {
        if (!imageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Image not found: " + id);
        }
        imageRepository.deleteById(id);
    }
}
