package com.atman.aahara.OneTimePass;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;


@Service
@RequiredArgsConstructor
public class OTPService {

    private static final int OTP_DIGITS = 6;
    private final SecureRandom secureRandom = new SecureRandom();

    public String generateOTP() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < OTP_DIGITS; i++) {
            sb.append(secureRandom.nextInt(10));
        }
        return sb.toString();
    }

    public boolean verify(String existing, String provided) {
        return existing != null && existing.equals(provided);
    }

}
