package com.atman.aahara.Assets.Image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface ImageService {
    Image saveImage(MultipartFile file, String folder) throws IOException;

    Image getImage(UUID id);

    Image replaceimage(Image oldImage, MultipartFile newImage, String folder) throws IOException;

    Image encodeImage(UUID id, String encodedImageKey);

    void deleteImage(UUID id);

    void deleteImage(Image image);
}
