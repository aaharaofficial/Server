package com.atman.aahara.Session.Infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerSessionRepository extends JpaRepository<CustomerSessionEntity, UUID> {
    Optional<CustomerSessionEntity> findByRefreshToken(String refreshToken);
    List<CustomerSessionEntity> findByMobileNumber(String mobileNumber);
}
