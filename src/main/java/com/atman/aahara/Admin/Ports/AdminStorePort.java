package com.atman.aahara.Admin.Ports;

import com.atman.aahara.Admin.Domain.Admin;

import java.util.Optional;
import java.util.UUID;

public interface AdminStorePort {
    Admin save(Admin admin);
    Optional<Admin> findByEmail(String email);
    Optional<Admin> findById(UUID id);
}
