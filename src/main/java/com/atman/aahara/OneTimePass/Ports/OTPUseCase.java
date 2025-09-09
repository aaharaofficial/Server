package com.atman.aahara.OneTimePass.Ports;

import com.atman.aahara.OneTimePass.Domain.OTPSession;

import java.util.Optional;

public interface OTPUseCase {
    OTPSession createSession(String identifier);
    boolean validate(String identifier, String providedOtp);
    void reset(String identifier);
    Optional<OTPSession> getSession(String identifier);
}
