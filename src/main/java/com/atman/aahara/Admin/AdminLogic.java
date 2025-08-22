package com.atman.aahara.Admin;

import com.atman.aahara.Exception.AdminNotValidCredentialsException;
import com.atman.aahara.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminLogic implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public Admin saveAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email).orElse(null);
    }

    @Override
    public AdminResponse refreshToken(String token) {
        String subject = jwtService.extractSubject(token);
        String accessToken = jwtService.generateToken(subject, "ADMIN");
        String refreshToken = jwtService.generateRefreshToken(subject);
        return AdminResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AdminResponse login(String email, String password) {
        Admin adminByEmail = getAdminByEmail(email);
        boolean matches = passwordEncoder.matches(password, adminByEmail.getPassword());
        if (!matches) {
            throw new AdminNotValidCredentialsException("credentials not vaid " + email);
        }
        String accessToken = jwtService.generateToken(adminByEmail.getEmail(), "ADMIN");
        String refreshToken = jwtService.generateRefreshToken(adminByEmail.getEmail());
        return AdminResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
