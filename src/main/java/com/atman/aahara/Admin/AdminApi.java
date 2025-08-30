package com.atman.aahara.Admin;

import com.atman.aahara.Admin.Dto.AdminResponse;
import com.atman.aahara.Admin.Dto.AdminRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AdminApi {
    ResponseEntity<AdminResponse> adminRefreshToken(String token, HttpServletResponse response);
    ResponseEntity<AdminResponse> adminLogin(AdminRequest adminRequest, HttpServletResponse response);
    ResponseEntity<?> adminLogout(HttpServletResponse response);
}
