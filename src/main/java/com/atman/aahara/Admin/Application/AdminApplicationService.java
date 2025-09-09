package com.atman.aahara.Admin.Application;


import com.atman.aahara.Admin.Application.DTO.TokenResponse;
import com.atman.aahara.Admin.Domain.Admin;
import com.atman.aahara.Admin.Ports.AdminAuthUseCase;
import com.atman.aahara.Admin.Ports.AdminStorePort;
import com.atman.aahara.Admin.Ports.AdminUseCase;
import com.atman.aahara.Security.TokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminApplicationService implements AdminUseCase, AdminAuthUseCase {

    private final AdminStorePort adminStorePort;
    private final TokenGenerator tokenGenerator;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Admin getAdmin(UUID id) {
        return adminStorePort.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return adminStorePort.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin not found with email: " + email));
    }

    //  ------------------------------------------------  Authentication ------------------------------------------


    @Override
    public TokenResponse login(String email, String password) {
        Admin admin = getAdminByEmail(email);

        if (!admin.verifyPassword(password,passwordEncoder)) {  // domain encapsulates password logic
            throw new RuntimeException("Invalid email or password");
        }

        String accessToken = tokenGenerator.generateAccessToken(email, "ADMIN");
        String refreshToken = tokenGenerator.generateRefreshToken(email);

        return generateTokenResponse(email, accessToken, refreshToken);
    }

    @Override
    public TokenResponse refreshToken(String refreshToken) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof Admin admin)) {         // <- cast to Admin
            throw new RuntimeException("No authenticated admin found in SecurityContext");
        }

        String newAccessToken = tokenGenerator.generateAccessToken(admin.getEmail(), "ADMIN");
        String newRefreshToken = tokenGenerator.generateRefreshToken(admin.getEmail());

        return generateTokenResponse(admin.getEmail(), newAccessToken, newRefreshToken);
    }

    //  ------------------------------------------------  private Methods ------------------------------------------

    //   <--- Build Token Response
    private static TokenResponse generateTokenResponse(String email, String accessToken, String refreshToken) {
        return TokenResponse.builder()
                .email(email)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
    
    
}
