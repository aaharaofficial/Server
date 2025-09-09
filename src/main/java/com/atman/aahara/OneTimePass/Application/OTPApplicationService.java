package com.atman.aahara.OneTimePass.Application;


import com.atman.aahara.OneTimePass.Domain.OTPGenerator;
import com.atman.aahara.OneTimePass.Domain.OTPSession;
import com.atman.aahara.OneTimePass.Ports.OTPStorePort;
import com.atman.aahara.OneTimePass.Ports.OTPUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OTPApplicationService implements OTPUseCase {
    private final OTPGenerator otpGenerator;
    private final OTPStorePort otpStore;

    @Override
    public OTPSession createSession(String identifier) {
        String otp = otpGenerator.generateOTP();
        OTPSession session = OTPSession.create(identifier, otp);
        return otpStore.save(session);
    }

    @Override
    public boolean validate(String identifier, String providedOtp) {
        OTPSession session = otpStore.find(identifier);
        if (session == null) throw new IllegalStateException("OTP session not found or expired");
        session.validateOtp(providedOtp);
        otpStore.delete(identifier);
        return true;
    }

    @Override
    public void reset(String identifier) {
        otpStore.delete(identifier);
    }

    @Override
    public Optional<OTPSession> getSession(String identifier) {
        return Optional.ofNullable(otpStore.find(identifier));
    }
}
