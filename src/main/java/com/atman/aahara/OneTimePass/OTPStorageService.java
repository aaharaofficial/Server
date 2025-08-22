package com.atman.aahara.OneTimePass;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface OTPStorageService {
    OneTimePass saveSession(String identifier, String otpCode);

    OneTimePass getSession(String identifier) ;

    void reset(String identifier);
}
