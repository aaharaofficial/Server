package com.atman.aahara.Admin;

import com.atman.aahara.Admin.Dto.AdminResponse;
import com.atman.aahara.Admin.Dto.AdminRequest;
import com.atman.aahara.Admin.Dto.TokenResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialExpiredException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController implements AdminApi {

    private final AdminService adminService;
    private final Environment env;

    private boolean isSecure() {
        return !env.acceptsProfiles("dev");
    }

    @Override
    @PostMapping("/logout")
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

    @Override
    @PostMapping("/refresh")
    public ResponseEntity<AdminResponse> adminRefreshToken(
            @CookieValue(name = "refreshToken", required = false) String token,
            HttpServletResponse response) {

        if (token == null || token.isEmpty()) {
            throw  new CredentialsExpiredException("Token not found Credentials Expired ");
        }
        TokenResponse tokenResponse = adminService.refreshToken(token);
        ResponseCookie cookie = ResponseCookie.from("refreshToken", tokenResponse.getRefreshToken())
                .httpOnly(true)
                .secure(isSecure())
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Strict")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
        AdminResponse res = new AdminResponse(tokenResponse.getAccessToken(), tokenResponse.getEmail());
        return ResponseEntity.ok(res);
    }
    @PostMapping("/login")
    @Override
    public ResponseEntity<AdminResponse> adminLogin(@RequestBody AdminRequest adminRequest,
                                                    HttpServletResponse response) {
        TokenResponse loginUserResponse = adminService.login(
                adminRequest.getEmail(),
                adminRequest.getPassword()
        );
        ResponseCookie cookie = ResponseCookie.from("refreshToken", loginUserResponse.getRefreshToken())
                .httpOnly(true)
                .secure(isSecure())
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Strict")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());

        AdminResponse res = new AdminResponse(loginUserResponse.getAccessToken(), loginUserResponse.getEmail());
        return ResponseEntity.ok(res);
    }
}
