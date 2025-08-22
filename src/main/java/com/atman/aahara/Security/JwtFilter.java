package com.atman.aahara.Security;


import com.atman.aahara.Admin.AdminService;
import com.atman.aahara.Customer.Auth.CustomerAuthService;
import com.atman.aahara.Customer.Base.CustomerService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AdminService adminService;
    private final CustomerService customerService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String subject = jwtService.extractSubject(token);
        String role = jwtService.extractRole(token);

        if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null
                && jwtService.isTokenValid(token, subject)) {

            Object principal = null;
            List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

            try {
                switch (role) {
                    case "CUSTOMER" -> principal = customerService.getCustomerByMobileNumber(subject);
                    case "ADMIN" -> principal = adminService.getAdminByEmail(subject);
                    default -> log.warn("Unknown role: {}", role);
                }
            } catch (Exception e) {
                log.error("Failed to load user for subject {}", subject, e);
            }

            if (principal != null) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(principal, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

}
