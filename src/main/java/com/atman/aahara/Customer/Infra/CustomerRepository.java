package com.atman.aahara.Customer.Infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    Optional<CustomerEntity> findByMobileNumber(String mobileNumber);

    List<CustomerEntity> findAllByFamily_id(UUID familyId);
}
