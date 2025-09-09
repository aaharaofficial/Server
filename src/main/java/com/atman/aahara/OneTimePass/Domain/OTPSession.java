package com.atman.aahara.OneTimePass.Domain;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OTPSession {

    private String identifier;
    private String otpCode;
    private Instant validTo;
    private int currentAttempt;

    // Domain rule: max attempts
    private static final int MAX_ATTEMPTS = 3;
    private static final long OTP_TTL_SECONDS = 30;

    // Factory method for creation
    public static OTPSession create(String identifier, String otpCode) {
        return OTPSession.builder()
                .identifier(identifier)
                .otpCode(otpCode)
                .validTo(Instant.now().plusSeconds(OTP_TTL_SECONDS))
                .currentAttempt(0)
                .build();
    }

    // Domain logic
    public void validateOtp(String providedOtp) {
        if (isExpired()) {
            throw new IllegalStateException("OTP expired. Request a new one.");
        }

        if (!otpCode.equals(providedOtp)) {
            incrementAttempt();
            if (currentAttempt > MAX_ATTEMPTS) {
                throw new IllegalStateException("Maximum OTP attempts exceeded. Try later.");
            }
            throw new IllegalArgumentException("Invalid OTP. Try again.");
        }
    }

    public boolean isExpired() {
        return Instant.now().isAfter(validTo);
    }

    private void incrementAttempt() {
        this.currentAttempt++;
    }
}
