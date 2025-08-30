package com.atman.aahara.Admin;

import com.atman.aahara.Admin.Dto.TokenResponse;

public interface AdminService {
    Admin saveAdmin(Admin admin);

    Admin getAdminByEmail(String email);

    TokenResponse refreshToken(String token);

    TokenResponse login(String email, String password);
}
