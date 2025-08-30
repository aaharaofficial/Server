package com.atman.aahara.OneTimePass;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.CredentialsExpiredException;
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
        int currentAttempt = 1;

        if (existingSession != null) {
            currentAttempt = existingSession.getCurrentAttempt() + 1;

            if (currentAttempt > MAX_ATTEMPTS) {
                throw new CredentialsExpiredException(
                        "Max OTP attempts reached. Try again after " + OTP_SESSION_EXPIRY.toMinutes() + " minutes"
                );
            }
        }

        OneTimePass session = new OneTimePass(
                identifier,
                otpCode,
                Instant.now().plus(OTP_EXPIRY),
                currentAttempt,
                OTP_EXPIRY.getSeconds()
        );

        return oneTimePassRepository.save(session);
    }

    @Override
    public OneTimePass getSession(String identifier) {
        return oneTimePassRepository.findById(identifier).orElse(null);
    }

    @Override
    public void reset(String identifier) {
        oneTimePassRepository.deleteById(identifier);
    }

    /**
     * Validates the OTP for the given identifier.
     *
     * @param identifier User/session identifier
     * @param otpCode    OTP code entered
     * @return true if valid
     */
    public boolean validateOtp(String identifier, String otpCode) {
        OneTimePass session = getSession(identifier);
        if (session == null) return false;

        if (session.getValidTo().isBefore(Instant.now())) {
            reset(identifier);
            throw new CredentialsExpiredException("OTP expired");
        }

        if (!session.getOtpCode().equals(otpCode)) {
            return false;
        }

        reset(identifier); // OTP correct → reset session
        return true;
    }
}
