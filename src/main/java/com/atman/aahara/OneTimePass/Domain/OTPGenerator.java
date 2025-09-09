package com.atman.aahara.OneTimePass.Domain;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class OTPGenerator {

    private static final int OTP_DIGITS = 6;
    private final SecureRandom secureRandom = new SecureRandom();

    public String generateOTP() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < OTP_DIGITS; i++) {
            sb.append(secureRandom.nextInt(10));
        }
        return sb.toString();
    }
}
