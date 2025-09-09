package com.atman.aahara.Assets.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Image {
    private UUID id;
    private String rawImageKey;
    private String encodedImageKey;
    private boolean encoded;
    private boolean rawImageUploaded;

    public void markEncoded(String encodedKey) {
        this.encoded = true;
        this.encodedImageKey = encodedKey;
    }

    public void markRawUploaded(String rawKey) {
        this.rawImageUploaded = true;
        this.rawImageKey = rawKey;
    }

}
