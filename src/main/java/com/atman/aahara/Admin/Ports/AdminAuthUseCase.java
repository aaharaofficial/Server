package com.atman.aahara.Admin.Ports;

import com.atman.aahara.Admin.Application.DTO.TokenResponse;

public interface AdminAuthUseCase {

    TokenResponse login(String email, String password);

    TokenResponse refreshToken(String refreshToken);

}
