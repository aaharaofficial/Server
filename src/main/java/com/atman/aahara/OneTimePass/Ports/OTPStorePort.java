package com.atman.aahara.OneTimePass.Ports;


import com.atman.aahara.OneTimePass.Domain.OTPSession;

public interface OTPStorePort {
    OTPSession save(OTPSession session);
    OTPSession find(String identifier);
    void delete(String identifier);
}
