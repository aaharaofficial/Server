package com.atman.aahara.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenValidator {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public boolean isValid(String token, String expectedSubject) {
        try {
            Claims claims = parseClaims(token);
            return expectedSubject.equals(claims.getSubject()) && !isExpired(claims);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractSubject(String token) {
        return parseClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return (String) parseClaims(token).get("role");
    }

    private boolean isExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
