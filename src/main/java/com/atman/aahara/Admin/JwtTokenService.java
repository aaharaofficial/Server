package com.atman.aahara.Admin;

import com.atman.aahara.Admin.Dto.TokenResponse;
import com.atman.aahara.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtTokenService {

    private final JwtService jwtService;

    public TokenResponse generateTokens(String email, String role) {
        String accessToken = jwtService.generateToken(email, role);
        String refreshToken = jwtService.generateRefreshToken(email);
        return new TokenResponse(accessToken, refreshToken, email);
    }
}
