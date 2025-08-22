package com.atman.aahara.OneTimePass;

import com.atman.aahara.Exception.OtpMaximumLimitException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class OTPStorageLogic implements OTPStorageService {

    private static final Duration OTP_EXPIRY = Duration.ofSeconds(30);       // OTP validity
    private static final Duration OTP_SESSION_EXPIRY = Duration.ofMinutes(30); // Session expiry
    private static final int MAX_ATTEMPTS = 3;

    private final OneTimePassRepository oneTimePassRepository;

    @Override
    public OneTimePass saveSession(String identifier, String otpCode) {
        OneTimePass existingSession = getSession(identifier);

        int attempts = 1;
        Long ttl = OTP_EXPIRY.getSeconds();

        if (existingSession != null) {
            attempts = existingSession.getCurrentAttempt() + 1;

            if (attempts > MAX_ATTEMPTS) {
                ttl = OTP_SESSION_EXPIRY.getSeconds();
                throw new OtpMaximumLimitException("Max OTP attempts reached. Try again later.");
            }
        }

        OneTimePass newSession = new OneTimePass(
                identifier,
                otpCode,
                Instant.now().plus(OTP_EXPIRY),
                attempts,
                ttl
        );

        return oneTimePassRepository.save(newSession);
    }

    @Override
    public OneTimePass getSession(String identifier) {
        return oneTimePassRepository.findById(identifier)
                .orElse(null);
    }

    @Override
    public void reset(String identifier) {
        oneTimePassRepository.deleteById(identifier);
    }
}
