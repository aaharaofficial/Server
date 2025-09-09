package com.atman.aahara.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final TokenValidator tokenValidator;
    private final Map<String, UserDetailsProvider> userProviders;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        try {
            if (!tokenValidator.isValid(token, tokenValidator.extractSubject(token))) {
                sendUnauthorized(response, "Invalid or expired token");
                return;
            }

            String subject = tokenValidator.extractSubject(token);
            String role = tokenValidator.extractRole(token);

            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                filterChain.doFilter(request, response);
                return;
            }

            UserDetailsProvider provider = userProviders.get(role);
            if (provider == null) {
                sendUnauthorized(response, "Unknown role");
                return;
            }

            Object principal = provider.loadUser(subject);
            if (principal == null) {
                sendUnauthorized(response, "User not found");
                return;
            }

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(principal, null, List.of(new SimpleGrantedAuthority("ROLE_" + role)));
            SecurityContextHolder.getContext().setAuthentication(authToken);

        } catch (Exception e) {
            log.error("JWT processing failed", e);
            sendUnauthorized(response, "Invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void sendUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), message);
    }
}
