package com.atman.aahara.Session.Domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CustomerSession {

    private UUID id;
    private String mobileNumber;
    private String refreshToken;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
    private String ipAddress;
    private String userAgent;
    private String latitude;
    private String longitude;
    private boolean valid;
    private LocalDateTime lastRefreshedAt;
    private LocalDateTime loggedOutAt;

    // Domain logic
    public boolean isValid() {
        return valid && LocalDateTime.now().isBefore(expiresAt);
    }

    public void refresh(String newRefreshToken, LocalDateTime newExpiry) {
        this.refreshToken = newRefreshToken;
        this.expiresAt = newExpiry;
        this.valid = true;
        this.lastRefreshedAt = LocalDateTime.now();
    }

    public void invalidate() {
        this.valid = false;
        this.loggedOutAt = LocalDateTime.now();
    }
}
