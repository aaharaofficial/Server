package com.atman.aahara.Assets.Application;

import com.atman.aahara.Assets.Domain.Image;
import com.atman.aahara.Assets.Port.FileStoragePort;
import com.atman.aahara.Assets.Port.ImageStorePort;
import com.atman.aahara.Assets.Port.ImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ImageApplicationService implements ImageUseCase {

    private final ImageStorePort imageStorePort;
    private final FileStoragePort fileStoragePort;

    @Override
    public Image saveImage(MultipartFile file, String folder) throws IOException {
        Image image = Image.builder()
                .encoded(false)
                .build();
        Image savedImage = imageStorePort.save(image);
        fileStoragePort.uploadFile(file, savedImage.getId(), folder, s3Key -> {
            savedImage.markRawUploaded(s3Key);
            imageStorePort.save(savedImage);
        });
        return savedImage;
    }

    @Override
    public Image getImage(UUID id) {
        return imageStorePort.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found: " + id));
    }

    @Override
    public Image replaceImage(UUID id, MultipartFile newFile, String folder) throws IOException {
        deleteImage(id);
        return saveImage(newFile, folder);
    }

    @Override
    public Image updateEncodeImage(UUID id, String encodedKey) {
        Image image = getImage(id);
        image.markEncoded(encodedKey);
        return imageStorePort.save(image);
    }

    @Override
    public Image updateRawImage(UUID id, String rawImageKey) {
        Image image = getImage(id);
        image.markRawUploaded(rawImageKey);
        return imageStorePort.save(image);
    }

    @Override
    public void deleteImage(UUID id) {
        Image image = getImage(id);

        // Delete raw Image first, then DB
        if (image.isRawImageUploaded()) {
            fileStoragePort.deleteFile(image.getRawImageKey(), () -> {
                imageStorePort.delete(image);
            });
        }
        // Delete encoded Image if exists
        else if (image.isEncoded()) {
            fileStoragePort.deleteFile(image.getEncodedImageKey(), () -> imageStorePort.delete(image));
        }
        // If no S3 files, just delete DB
        else {
            imageStorePort.delete(image);
        }
    }
}
