package com.atman.aahara.Admin;

import com.atman.aahara.Admin.Dto.TokenResponse;
import com.atman.aahara.Exception.InvalidCredentialsException;
import com.atman.aahara.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminLogic implements AdminService {


    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
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
    public TokenResponse login(String email, String password) {
        Admin admin = getAdminByEmail(email);
        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }
        return jwtTokenService.generateTokens(email, "ADMIN");
    }

    @Override
    public TokenResponse refreshToken(String token) {
        String email = jwtService.extractSubject(token); // jwtService can be injected here if needed
        return jwtTokenService.generateTokens(email, "ADMIN");
    }
}
