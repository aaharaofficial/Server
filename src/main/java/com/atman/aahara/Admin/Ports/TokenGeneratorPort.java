package com.atman.aahara.Admin.Ports;

import com.atman.aahara.Admin.Application.DTO.TokenResponse;

public interface TokenGeneratorPort {
    TokenResponse generateTokens(String email, String role);
}
