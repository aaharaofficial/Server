package com.atman.aahara.Customer.Base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByMobileNumber(String mobileNumber);
    boolean existsByMobileNumber(String mobileNumber);
}
