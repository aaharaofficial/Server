package com.atman.aahara.Assets;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {
    String uploadFile(MultipartFile file, String folder) throws IOException;
    void deleteFile(String key);
    String getFileUrl(String key);
}
