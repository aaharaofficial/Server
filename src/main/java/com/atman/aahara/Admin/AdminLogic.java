package com.atman.aahara.Admin;

import com.atman.aahara.Admin.Dto.TokenResponse;
import com.atman.aahara.Exception.InvalidCredentialsException;
import com.atman.aahara.Security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
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
        return adminRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));
    }


    @Override
    public TokenResponse refreshToken(String token) {
        String subject = jwtService.extractSubject(token);
        String accessToken = jwtService.generateToken(subject, "ADMIN");
        String refreshToken = jwtService.generateRefreshToken(subject);
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .email(subject)
                .build();
    }

    @Override
    public TokenResponse login(String email, String password) {
        Admin adminByEmail = getAdminByEmail(email);
        boolean matches = passwordEncoder.matches(password, adminByEmail.getPassword());
        if (!matches) {
            throw new BadCredentialsException("Invalid email or password" );
        }
        String accessToken = jwtService.generateToken(adminByEmail.getEmail(), "ADMIN");
        String refreshToken = jwtService.generateRefreshToken(adminByEmail.getEmail());
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .email(email)
                .build();
    }
}
