package com.atman.aahara.Assets.Image;

import com.atman.aahara.Assets.FileStorageService;
import com.atman.aahara.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageLogic implements ImageService {

    private final ImageRepository imageRepository;
    private final FileStorageService fileStorageService;

    /**
     * Create a new Image entity with raw image key.
     */
    @Override
    public Image saveImage(MultipartFile file, String folder) throws IOException {
        String rawImageKey = fileStorageService.uploadFile(file, folder);
        Image image = Image.builder()
                .rawImage(rawImageKey)
                .isEncoded(false)
                .build();
        return imageRepository.save(image);
    }

    /**
     * Fetch image by ID. Throws exception if not found.
     */
    @Override
    public Image getImage(UUID id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found: " + id));
    }


    /**
     * Update image by new Image. Throws exception if not found.
     */
    @Override
    public Image replaceimage(Image oldImage, MultipartFile newImage, String folder) throws IOException {
        deleteImage(oldImage);
        return saveImage(newImage, folder);
    }

    /**
     * Encode an existing image (set encoded key and mark as encoded)
     */
    @Override
    public Image encodeImage(UUID id, String encodedImageKey) {
        Image image = getImage(id);
        image.setEncodedImage(encodedImageKey);
        image.setEncoded(true);
        return imageRepository.save(image);
    }


    /**
     * Delete image by ID.
     */
    @Override
    public void deleteImage(UUID id) {
        Image image = getImage(id);
        deleteImage(image);
    }

    /**
     * Delete image by image.
     */
    @Override
    public void deleteImage(Image image) {
        if (image.getRawImage() != null) {
            fileStorageService.deleteFile(image.getRawImage());
        }
        if (image.isEncoded() && image.getEncodedImage() != null) {
            fileStorageService.deleteFile(image.getEncodedImage());
        }
        imageRepository.delete(image);
    }
}
