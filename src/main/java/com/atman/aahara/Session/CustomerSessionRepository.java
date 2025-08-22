package com.atman.aahara.Session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerSessionRepository extends JpaRepository<CustomerSession, UUID> {
    Optional<CustomerSession> findByRefreshToken(String refreshToken);
    List<CustomerSession> findByMobileNumber(String mobileNumber);
}
