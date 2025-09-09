package com.atman.aahara.Admin.Adapter;

import com.atman.aahara.Admin.Application.DTO.TokenResponse;
import com.atman.aahara.Admin.Ports.TokenGeneratorPort;
import com.atman.aahara.Security.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TokenAdapter implements TokenGeneratorPort {

    private final TokenGenerator tokenGenerator; // from Security module

    @Override
    public TokenResponse generateTokens(String email, String role) {
        String accessToken = tokenGenerator.generateAccessToken(email, role);
        String refreshToken = tokenGenerator.generateRefreshToken(email);
        return new TokenResponse(accessToken, refreshToken, email);
    }
}
