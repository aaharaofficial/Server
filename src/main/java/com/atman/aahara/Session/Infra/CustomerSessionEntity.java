package com.atman.aahara.Session.Infra;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "customer_session")
public class CustomerSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String mobileNumber;
    private String refreshToken;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
    private String ipAddress;
    private String userAgent;
    private String latitude;
    private String longitude;
    private boolean valid;
    private LocalDateTime lastRefreshedAt;
    private LocalDateTime loggedOutAt;
}
