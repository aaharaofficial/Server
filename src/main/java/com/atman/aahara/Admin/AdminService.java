package com.atman.aahara.Admin;

public interface AdminService {
    Admin saveAdmin(Admin admin);

    Admin getAdminByEmail(String email);

    AdminResponse refreshToken(String token);

    AdminResponse login(String email, String password);
}
