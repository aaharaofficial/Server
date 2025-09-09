package com.atman.aahara.Assets.Port;


import com.atman.aahara.Assets.Domain.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface ImageUseCase {
    Image saveImage(MultipartFile file, String folder) throws IOException;
    Image getImage(UUID id);
    Image replaceImage(UUID id, MultipartFile newFile, String folder) throws IOException;
    Image updateEncodeImage(UUID id, String encodedKey);
    Image updateRawImage(UUID id, String rawImageKey);
    void deleteImage(UUID id);
}
