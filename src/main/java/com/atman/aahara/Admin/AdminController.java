package com.atman.aahara.Admin;

import com.atman.aahara.Admin.Dto.AdminRequest;
import com.atman.aahara.Admin.Dto.AdminResponse;
import com.atman.aahara.Admin.Dto.TokenResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/admin")
@Slf4j
public class AdminController implements AdminApi {

    private final AdminService adminService;
    private final Environment env;

    private boolean isSecure() {
        return !env.acceptsProfiles("dev");
    }

    private void addRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(isSecure())
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Strict")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    @PostMapping("/logout")
    @Override
    public ResponseEntity<?> adminLogout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(isSecure())
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
        return ResponseEntity.ok("Logged out successfully");
    }

    @PostMapping("/refresh-token")
    @Override
    public ResponseEntity<AdminResponse> adminRefreshToken(
            @CookieValue(name = "refreshToken", required = false) String token,
            HttpServletResponse response) {

        if (token == null || token.isEmpty()) {
            throw new CredentialsExpiredException("Token not found or expired");
        }
        TokenResponse tokenResponse = adminService.refreshToken(token);
        addRefreshTokenCookie(response, tokenResponse.getRefreshToken());

        AdminResponse res = new AdminResponse(tokenResponse.getAccessToken(), tokenResponse.getEmail());
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<AdminResponse> adminLogin(@RequestBody AdminRequest adminRequest,
                                                    HttpServletResponse response) {
        TokenResponse tokenResponse = adminService.login(adminRequest.getEmail(), adminRequest.getPassword());
        addRefreshTokenCookie(response, tokenResponse.getRefreshToken());

        AdminResponse res = new AdminResponse(tokenResponse.getAccessToken(), tokenResponse.getEmail());
        return ResponseEntity.ok(res);
    }
}
