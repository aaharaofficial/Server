package com.atman.aahara.Admin;

import org.springframework.http.ResponseEntity;

public interface AdminApi {
    ResponseEntity<AdminResponse> refreshToken(String token);
    ResponseEntity<AdminResponse> login(String email, String password);
}
