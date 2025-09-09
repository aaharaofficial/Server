package com.atman.aahara.Admin.Adapter;


import com.atman.aahara.Admin.Domain.Admin;
import com.atman.aahara.Admin.Infra.AdminEntity;
import com.atman.aahara.Admin.Infra.AdminRepository;
import com.atman.aahara.Admin.Ports.AdminStorePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AdminStoreAdapter implements AdminStorePort {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Override
    public Admin save(Admin admin) {
        AdminEntity entity = adminMapper.toEntity(admin);
        AdminEntity savedEntity = adminRepository.save(entity);
        return adminMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email)
                .map(adminMapper::toDomain);
    }

    @Override
    public Optional<Admin> findById(UUID id){
        return adminRepository.findById(id).map(adminMapper::toDomain);
    }
}
