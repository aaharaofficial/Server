package com.atman.aahara.Assets.Port;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.function.Consumer;

public interface FileStoragePort {
    void uploadFile(MultipartFile file, UUID resourceId, String folder, Consumer<String> onSuccess) throws IOException;

    void deleteFile(String key, Runnable onSuccess);

    String getFileUrl(String key);

}
