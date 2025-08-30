package com.atman.aahara.Inventory;

import com.atman.aahara.Assets.FileStorageService;
import com.atman.aahara.Assets.Image.Image;
import com.atman.aahara.Assets.Image.ImageLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class InventoryImageService {

    private final FileStorageService fileStorageService;
    private final ImageLogic imageLogic;

    public Image saveImage(MultipartFile file) throws IOException {
        String key = fileStorageService.uploadFile(file, "inventory/raw");
        return imageLogic.createImage(key);
    }

    public void deleteImage(Image image) {
        if (image != null) {
            fileStorageService.deleteFile(image.getEncodedImage());
            fileStorageService.deleteFile(image.getRawImage());
        }
    }

    public Image replaceImage(Image oldImage, MultipartFile newFile) throws IOException {
        deleteImage(oldImage);
        return saveImage(newFile);
    }
}
